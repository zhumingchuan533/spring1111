package com.tedu.sp04.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.pojo.Order;
import com.tedu.sp01.pojo.User;
import com.tedu.sp01.service.OrderService;
import com.tedu.web.util.JsonResult;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    private ItemFeignService itemservice;
    @Autowired
    private UserFeignService userservice;
    
    
	@Override
	public Order getOrder(String orderId) {
		//调用商品微服务,获取订单中的商品列表数据
		JsonResult<List<Item>> items = itemservice.getItems(orderId);
		//调用用户微服务,获取用户数据
		JsonResult<User> user = userservice.getUser(7);
		Order order = new Order();
		order.setId(orderId);
		order.setItems(items.getData());
		order.setUser(user.getData());
		return order;
	}

	@Override
	public void addOrder(Order order) {
      //减少商品的库存
		itemservice.decreaseNumber(order.getItems());
		//增加用户的积分
		userservice.addScore(7, 100);
		log.info("保存订单:"+order);
	}

}
