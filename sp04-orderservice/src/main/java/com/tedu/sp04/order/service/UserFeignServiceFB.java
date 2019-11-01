package com.tedu.sp04.order.service;

import org.springframework.stereotype.Component;

import com.tedu.sp01.pojo.User;
import com.tedu.web.util.JsonResult;
@Component
public class UserFeignServiceFB implements UserFeignService{

	@Override
	public JsonResult<User> getUser(Integer userId) {
		if(Math.random()<0.46) {
			return JsonResult.ok(new User(userId, "缓存"+userId,"缓存密码+"+userId));
		}
		return JsonResult.err("无法获取用户信息");
	}

	@Override
	public JsonResult addScore(Integer userId, Integer score) {
		
		return JsonResult.err("无法增加用户积分");
	}

}
