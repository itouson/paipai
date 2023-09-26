package com.moon.listener;

import com.alibaba.fastjson.JSONObject;
import com.moon.config.RabbitConfig;
import com.moon.entity.Room;
import com.moon.entity.TbOrder;
import com.moon.service.RoomService;
import com.moon.service.TbOrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RoomListener {
    @Autowired
    private RoomService roomService;
    @Autowired
    private TbOrderService orderService;

    @RabbitListener(queues = RabbitConfig.DELAY_QUEUE_NAME)
    public void receiveDelayedQueue(Message msg) {
        String str = new String(msg.getBody());
        Room crt = JSONObject.parseObject(str, Room.class);
        Room room = roomService.selectRoomById(crt.getId());
        if (room != null) {
            room.setStatus(0);
            roomService.recoveryRoom(room);
            if (room.getOwnerId() != 0) {
                TbOrder order = new TbOrder();
                order.setCreatedtime(new Date());
                order.setGoodId(room.getGoodId());
                order.setGname(room.getGname());
                order.setSellerId(room.getUpId());
                order.setOwnerId(room.getOwnerId());
                order.setOwnerAccount(room.getOwnerAccount());
                order.setEndprice(room.getCurrentprice());
                orderService.createOrder(order);
            }
        }
    }
}
