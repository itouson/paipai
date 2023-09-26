package com.moon.service;

import com.moon.entity.SysUser;
import com.moon.utils.CurrUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moon.entity.TbOrder;
import com.moon.dao.TbOrderMapper;

import java.util.List;

@Transactional
@Service
public class TbOrderService {
	@Autowired
	private TbOrderMapper tborderMapper;
	@Autowired
	private CurrUserUtils currUserUtils;

	public TbOrderMapper getTbOrderMapper() {
		return tborderMapper;
	}

    public void createOrder(TbOrder order) {
		tborderMapper.insert(order);
    }

    public List<TbOrder> listOrderByOwnerId() {
		TbOrder crt = new TbOrder();
		crt.setOwnerId(currUserUtils.selectCurrUser("token", SysUser.class).getId());
		return tborderMapper.select(crt);
    }

	public int updateAddressByOrderId(int orderId, String address) {
		TbOrder crt = new TbOrder();
		crt.setId(orderId);
		crt.setAddress(address);
		return tborderMapper.updateByPrimaryKeySelective(crt);
	}

	public List<TbOrder> listOrdersBySellerId() {
		TbOrder crt = new TbOrder();
		crt.setSellerId(currUserUtils.selectCurrUser("token", SysUser.class).getId());
		return tborderMapper.select(crt);
	}
}