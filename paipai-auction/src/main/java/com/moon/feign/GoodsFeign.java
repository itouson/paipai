package com.moon.feign;

import com.moon.entity.Goods;
import com.moon.entity.Type;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "paipai-gateway")
public interface GoodsFeign {
    @RequestMapping("/paipai-goods-service/moon/Goods/listGoods")
    List<Goods> listGoods();

    @RequestMapping("/paipai-goods-service/moon/Type/listTypes")
    List<Type> listTypes();

    @RequestMapping("/paipai-goods-service/moon/Goods/addGoods")
    String addGoods(@RequestBody Goods goods);

    @RequestMapping("/paipai-goods-service/moon/Goods/listGoodsByUserId")
    List<Goods> listGoodsByUserId(@RequestParam("userId") int userId);

    @RequestMapping("/paipai-goods-service/moon/Goods/selectGoodById")
    Goods selectGoodById(@RequestParam("goodId") int goodId);

    @RequestMapping("/paipai-goods-service/moon/Goods/search")
    List<Goods> search(@RequestParam("typeId") int typeId, @RequestParam("word") String word);
}
