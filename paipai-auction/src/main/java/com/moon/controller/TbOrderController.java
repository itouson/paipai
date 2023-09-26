package com.moon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.moon.utils.BaseController;
import com.moon.entity.TbOrder;
import com.moon.service.TbOrderService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/moon/TbOrder")
public class TbOrderController extends BaseController {
    @Autowired
    private TbOrderService tborderService;

    @RequestMapping("/listOrderByOwnerId")
    @ResponseBody
    public List<TbOrder> listOrderByOwnerId() {
        return tborderService.listOrderByOwnerId();
    }

    @RequestMapping("/updateAddress")
    @ResponseBody
    public String updateAddress(int orderId, String address) {
        int r = tborderService.updateAddressByOrderId(orderId,address);
        return r != 0 ? "success" : "err";
    }

    @RequestMapping("/listOrdersBySellerId")
    @ResponseBody
    public List<TbOrder> listOrdersBySellerId(){
        return tborderService.listOrdersBySellerId();
    }
}