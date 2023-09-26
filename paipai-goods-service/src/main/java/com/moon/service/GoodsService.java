package com.moon.service;

import com.moon.entity.Goods;
import com.moon.model.EsGoods;
import com.moon.model.EsGoodsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moon.dao.GoodsMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private EsGoodsRepository esGoodsRepository;

    public GoodsMapper getGoodsMapper() {
        return goodsMapper;
    }

    public List<Goods> listGoods() {
        return goodsMapper.selectAll();
    }

    public int addGoods(Goods goods) {
        return goodsMapper.insert(goods);
    }

    public List<Goods> listGoodsByUserId(int userId) {
        Goods crt = new Goods();
        crt.setUserId(userId);
        return goodsMapper.select(crt);
    }

    public Goods selectGoodById(int goodId) {
        Goods crt = new Goods();
        crt.setId(goodId);
        return goodsMapper.selectOne(crt);
    }

    public List<Goods> search(int typeId, String word) {
        HashSet<EsGoods> esGoods = new HashSet<EsGoods>();
        List<Goods> goods = new ArrayList<Goods>();

        if (typeId == -1) {
            if (word.equals("")) {
                return goodsMapper.selectAll();
            } else {
                List<EsGoods> l1 = esGoodsRepository.findByGname(word);
                List<EsGoods> l2 = esGoodsRepository.findByIntro(word);
                esGoods.addAll(l1);
                esGoods.addAll(l2);
                for (EsGoods esGood : esGoods) {
                    Goods g = new Goods();
                    BeanUtils.copyProperties(esGood, g);
                    goods.add(g);
                }
            }
        } else {
            List<Integer> ids = new ArrayList<>();
            Goods crt = new Goods();
            crt.setTypeId(typeId);
            List<Goods> list = goodsMapper.select(crt);
            for (Goods g : list) {
                ids.add(g.getId());
            }
            if (word.equals("")) {
                return list;
            } else {
                List<EsGoods> l1 = esGoodsRepository.findByGname(word);
                List<EsGoods> l2 = esGoodsRepository.findByIntro(word);
                esGoods.addAll(l1);
                esGoods.addAll(l2);
                for (EsGoods esGood : esGoods) {
                    Goods g = new Goods();
                    BeanUtils.copyProperties(esGood, g);
                    goods.add(g);
                }
                goods = goods.stream().filter(g -> ids.contains(g.getId())).collect(Collectors.toList());
            }
        }
        return goods;
    }
}