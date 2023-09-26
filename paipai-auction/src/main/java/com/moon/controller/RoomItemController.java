package com.moon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.moon.utils.BaseController;
import com.moon.entity.RoomItem;
import com.moon.service.RoomItemService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/moon/RoomItem")
public class RoomItemController extends BaseController {
	@Autowired
	private RoomItemService roomitemService;

	@RequestMapping("/listRoomItemByRoomId")
	@ResponseBody
	public List<RoomItem> listRoomItemByRoomId(int roomId){
		return roomitemService.listRoomItemByRoomId(roomId);
	}
}