package com.moon.controller;

import com.alibaba.fastjson.JSONObject;
import com.moon.entity.SysUser;
import com.moon.utils.CurrUserUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.moon.utils.BaseController;
import com.moon.entity.Room;
import com.moon.service.RoomService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/moon/Room")
public class RoomController extends BaseController {
    private static final Object obj = new Object();

    @Autowired
    private RoomService roomService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private CurrUserUtils currUserUtils;

    @RequestMapping("/openRoomView")
    public String openRoomView(int roomId, Model model, HttpServletRequest req) {
        model.addAttribute("roomId", roomId);
        return "myproj/room";
    }

    @RequestMapping("/selectRoomById")
    @ResponseBody
    public Room selectRoomById(int roomId) {
        return roomService.selectRoomById(roomId);
    }

    @RequestMapping("/updateRoom")
    @ResponseBody
    public String updateRoom(int roomId, BigDecimal price) {
        synchronized (obj) {
            Room room = this.selectRoomById(roomId);
            if (room.getUpId().equals(currUserUtils.selectCurrUser("token", SysUser.class).getId()) || room.getStatus() == 0)
                return "无效操作";
            if (new Date().before(room.getStarttime())) return "未到起拍时间，请等待";
            if (new Date().after(room.getEndtime())) return "已结束";
            if (price.subtract(room.getCurrentprice()).compareTo(room.getStepprice()) < 0) return "请按规定的幅度加价";
            int r = roomService.updateRoom(roomId, price);
            return r != 0 ? "success" : "err";
        }
    }

    @RequestMapping("listRoomsByUserId")
    @ResponseBody
    public List<Room> listRoomsByUserId() {
        return roomService.listRoomsByUserId();
    }

    @RequestMapping("/startAuction")
    @ResponseBody
    public String startAuction(@RequestParam("room") String str) {
        Room room = JSONObject.parseObject(str, Room.class);
        if (room.getEndtime().before(room.getStarttime())) return "截止时间应大于起拍时间";
        int r = roomService.startAuction(room);
        return r != 0 ? "success" : "err";
    }

    @RequestMapping("/listRoomsByOwnerId")
    @ResponseBody
    public List<Room> listRoomsByOwnerId() {
        return roomService.listRoomsByOwnerId();
    }

}