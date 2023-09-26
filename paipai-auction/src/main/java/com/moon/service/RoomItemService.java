package com.moon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moon.entity.RoomItem;
import com.moon.dao.RoomItemMapper;

import java.util.List;

@Transactional
@Service
public class RoomItemService {
    @Autowired
    private RoomItemMapper roomitemMapper;

    public RoomItemMapper getRoomItemMapper() {
        return roomitemMapper;
    }

    public int addRoomItem(RoomItem roomItem) {
        return roomitemMapper.insert(roomItem);
    }

    public List<RoomItem> listRoomItemByRoomId(int roomId) {
        RoomItem crt = new RoomItem();
        crt.setRoomId(roomId);
        return roomitemMapper.select(crt);
    }

    public List<RoomItem> listRoomItemByOwnerId(int ownerId) {
        RoomItem crt = new RoomItem();
        crt.setOwnerId(ownerId);
        return roomitemMapper.select(crt);
    }
}