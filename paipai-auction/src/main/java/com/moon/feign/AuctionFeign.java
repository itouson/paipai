package com.moon.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "paipai-gateway")
public interface AuctionFeign {
}
