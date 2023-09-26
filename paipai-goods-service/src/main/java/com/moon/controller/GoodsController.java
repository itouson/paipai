package com.moon.controller;

import com.moon.entity.Goods;
import com.moon.model.EsGoods;
import com.moon.model.EsGoodsRepository;
import com.moon.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.moon.utils.BaseController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/moon/Goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private EsGoodsRepository esGoodsRepository;

    @RequestMapping("/listGoods")
    @ResponseBody
    public List<Goods> listGoods() {
        return goodsService.listGoods();
    }

    @RequestMapping("/addGoods")
    @ResponseBody
    public String addGoods(@RequestBody Goods goods) {
        int r = goodsService.addGoods(goods);
        EsGoods esGoods = new EsGoods();
        BeanUtils.copyProperties(goods, esGoods);
        EsGoods save = esGoodsRepository.save(esGoods);
        if (r != 0 && save != null) return "success";
        else return "err";
    }

    @RequestMapping("/listGoodsByUserId")
    @ResponseBody
    public List<Goods> listGoodsByUserId(@RequestParam("userId") int userId) {
        return goodsService.listGoodsByUserId(userId);
    }

    @RequestMapping("/selectGoodById")
    @ResponseBody
    public Goods selectGoodById(@RequestParam("goodId") int goodId) {
        return goodsService.selectGoodById(goodId);
    }

    @RequestMapping("/initEsGoods")
    @ResponseBody
    public String initEsGoods() {
        List<Goods> goods = goodsService.listGoods();
        for (Goods g : goods) {
            EsGoods esGoods = new EsGoods();
            BeanUtils.copyProperties(g, esGoods);
            esGoodsRepository.save(esGoods);
            System.out.println("已导入:" + esGoods.getGname());
        }
        return "success";
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<Goods> search(@RequestParam("typeId") int typeId, @RequestParam("word") String word) {
        return goodsService.search(typeId, word);
    }
}