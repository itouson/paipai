package com.moon.controller;

import com.moon.entity.Goods;
import com.moon.entity.Room;
import com.moon.entity.SysUser;
import com.moon.entity.Type;
import com.moon.feign.GoodsFeign;
import com.moon.service.RoomService;
import com.moon.utils.CurrUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.moon.utils.BaseController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsFeign goodsFeign;
    @Autowired
    private RoomService roomService;
    @Autowired
    private CurrUserUtils currUserUtils;

    @Value("${nginx.dir}")
    private String nginxDir;

    @RequestMapping("/listGoods")
    @ResponseBody
    public List<Goods> listGoods() {
        return goodsFeign.listGoods();
    }

    @RequestMapping("/listTypes")
    @ResponseBody
    public List<Type> listTypes() {
        return goodsFeign.listTypes();
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<Goods> search(@RequestParam("typeId") int typeId, @RequestParam("word") String word) {
        return goodsFeign.search(typeId, word);
    }


    @RequestMapping("/upl")
    @ResponseBody
    public String upl(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + ".jpg";
        String filePath = nginxDir + "/goods/" + fileName;
        File f = new File(filePath);
        file.transferTo(f);
        return fileName;
    }

    @RequestMapping("/addGoods")
    @ResponseBody
    public String addGoods(Goods goods) {
        if ("".equals(goods.getGname()) || goods.getGname() == null) return "请输入名称";
        if ("".equals(goods.getPic()) || goods.getPic() == null) return "请上传图片";
        SysUser sysUser = currUserUtils.selectCurrUser("token", SysUser.class);
        goods.setStatus(1);
        goods.setUserId(sysUser.getId());
        goods.setUserAccount(sysUser.getAccount());
        String msg = goodsFeign.addGoods(goods);
        return msg.equals("success") ? "success" : "err";
    }

    @RequestMapping("/listGoodsByUserId")
    @ResponseBody
    public List<Goods> listGoodsByUserId() {
        return goodsFeign.listGoodsByUserId(currUserUtils.selectCurrUser("token", SysUser.class).getId());
    }

    @RequestMapping("/goodsDetailView")
    public String goodsDetailView(int goodId, Model model) {
        Goods goods = goodsFeign.selectGoodById(goodId);
        List<Room> rooms = roomService.listRoomsByGoodId(goodId);
        model.addAttribute("g", goods);
        model.addAttribute("rooms", rooms);
        return "myproj/detail";
    }
}