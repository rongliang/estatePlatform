package com.kerry.wechat;

import com.kerry.wechat.filter.CrossDomainFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class WechatConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatConsumerApplication.class, args);
	}

	/**
	 * 跨域处理器注册
	 * @return
	 */
	@Bean
	public FilterRegistrationBean crossDomainFilterRegistr(){
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CrossDomainFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("crossDomainFilter");
		registration.setOrder(1);
		return registration;
	}
}
