package com.tedu.sp06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.pojo.Order;
import com.tedu.sp01.pojo.User;
import com.tedu.web.util.JsonResult;

@RestController
public class RibbonController {
  @Autowired
  private RestTemplate  rt;
  @GetMapping("/item-service/{orderId}")
  public JsonResult<Item> getItems(@PathVariable String orderId){
	  System.out.println(orderId);
	//向指定微服务地址发送 get 请求，并获得该服务的返回结果 
	    //{1} 占位符，用 orderId 填充
	  return rt.getForObject("http://item-service/{100}", JsonResult.class,orderId);//调用其他服务器 {100}占位符,被orderId替换
  }
  @PostMapping("/item-service/decreaseNumber")
  public JsonResult decreaseNumber(@RequestBody List<Item> items){//post提交 从协议体获取
	  //发送 post 请求
		return rt.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);//item-service:服务提供者的配置name轮询
  }
  @GetMapping("/user-service/{userId}")
  public JsonResult<User> getUser(@PathVariable Integer userId) {
		return rt.getForObject("http://user-service/{1}", JsonResult.class, userId);
	}
  @GetMapping("/user-service/{userId}/score")
  public JsonResult addScore(
			@PathVariable Integer userId, Integer score) {
		return rt.getForObject("http://user-service/{1}/score?score={2}", JsonResult.class, userId, score);
	}
  @GetMapping("order-service/{orderId}")
  public JsonResult<Order> getOrder(@PathVariable String orderId) {
		return rt.getForObject("http://order-service/{1}", JsonResult.class, orderId);
	}
  @GetMapping("/order-service/")
  public JsonResult addOrder() {
		return rt.getForObject("http://order-service/", JsonResult.class);
	}
}
