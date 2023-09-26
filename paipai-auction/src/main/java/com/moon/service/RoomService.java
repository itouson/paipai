package com.moon.service;

import com.alibaba.fastjson.JSONObject;
import com.moon.config.RabbitConfig;
import com.moon.entity.RoomItem;
import com.moon.entity.SysUser;
import com.moon.utils.CurrUserUtils;
import com.moon.utils.UUIDUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moon.entity.Room;
import com.moon.dao.RoomMapper;

import java.math.BigDecimal;
import java.util.*;

@Transactional
@Service
public class RoomService {
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private CurrUserUtils currUserUtils;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RoomItemService roomItemService;

    public RoomMapper getRoomMapper() {
        return roomMapper;
    }

    public List<Room> listRoomsByGoodId(int goodId) {
        Room crt = new Room();
        crt.setGoodId(goodId);
        return roomMapper.select(crt);
    }

    public Room selectRoomById(int roomId) {
        Room crt = new Room();
        crt.setId(roomId);
        return roomMapper.selectOne(crt);
    }

    public int updateRoom(int roomId, BigDecimal price) {
        SysUser sysUser = currUserUtils.selectCurrUser("token", SysUser.class);
        Room crt = new Room();
        crt.setId(roomId);
        crt.setCurrentprice(price);
        crt.setOwnerId(sysUser.getId());
        crt.setOwnerAccount(sysUser.getAccount());

        RoomItem roomItem = new RoomItem();
        roomItem.setRoomId(roomId);
        roomItem.setOwnerId(sysUser.getId());
        roomItem.setOwnerAccount(sysUser.getAccount());
        roomItem.setCurrentprice(price);
        roomItem.setDatetime(new Date());
        int r = roomItemService.addRoomItem(roomItem);
        return r != 0 ? roomMapper.updateByPrimaryKeySelective(crt) : r;
    }

    public List<Room> listRoomsByUserId() {
        Room crt = new Room();
        crt.setUpId(currUserUtils.selectCurrUser("token", SysUser.class).getId());
        return roomMapper.select(crt);
    }

    public int startAuction(Room room) {
        SysUser sysUser = currUserUtils.selectCurrUser("token", SysUser.class);
        room.setId(UUIDUtils.getUUIDInOrderId());
        room.setStatus(1);
        room.setUpId(sysUser.getId());
        room.setUpAccount(sysUser.getAccount());
        room.setOwnerId(0);
        room.setCurrentprice(room.getStartprice());
        String str = JSONObject.toJSONString(room);
        rabbitTemplate.convertAndSend(RabbitConfig.DELAY_EXCHANGE_NAME, RabbitConfig.DELAY_ROUTING_KEY, str, msg -> {
            msg.getMessageProperties().setDelay((int) (room.getEndtime().getTime() - new Date().getTime()));
            return msg;
        });
        return roomMapper.insert(room);
    }

    public void recoveryRoom(Room room) {
        roomMapper.updateByPrimaryKeySelective(room);
    }

    public List<Room> listRoomsByOwnerId() {
        SysUser sysUser = currUserUtils.selectCurrUser("token", SysUser.class);
        int ownerId = sysUser.getId();
        List<RoomItem> roomItemList = roomItemService.listRoomItemByOwnerId(ownerId);
        Set<Integer> roomIds = new HashSet<>();
        for (RoomItem roomItem : roomItemList) {
            roomIds.add(roomItem.getRoomId());
        }
        List<Room> roomList = new ArrayList<>();
        for (Integer roomId : roomIds) {
            Room crt = new Room();
            crt.setId(roomId);
            roomList.add(roomMapper.selectOne(crt));
        }
        return roomList;
    }
}