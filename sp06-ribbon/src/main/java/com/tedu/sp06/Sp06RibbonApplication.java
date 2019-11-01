package com.tedu.sp06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
@EnableDiscoveryClient
@SpringBootApplication
public class Sp06RibbonApplication {
	//创建RestTemplate实例,存入spring容器
	@LoadBalanced //负载均衡
	@Bean
	public RestTemplate getRestTemplate() {
		SimpleClientHttpRequestFactory f = new SimpleClientHttpRequestFactory();
		f.setConnectTimeout(1000);//连接一秒超时
		f.setReadTimeout(1000);
		return new RestTemplate(f);
	}

	public static void main(String[] args) {
		SpringApplication.run(Sp06RibbonApplication.class, args);
	}

}
