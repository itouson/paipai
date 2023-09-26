package com.moon.model;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EsGoodsRepository extends ElasticsearchRepository<EsGoods, Integer> {
    List<EsGoods> findByGname(String word);
    List<EsGoods> findByIntro(String word);
}
