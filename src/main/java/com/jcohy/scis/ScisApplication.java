package com.jcohy.scis;

import com.jcohy.scis.interception.CommonIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@Configuration
public class ScisApplication extends WebMvcConfigurerAdapter{

	@Autowired
	private CommonIntercepter commonIntercepter;


	public static void main(String[] args) {
		SpringApplication.run(ScisApplication.class, args);
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonIntercepter).addPathPatterns("/admin/**");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//登录首页
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/index").setViewName("login");
		registry.addViewController("/register").setViewName("register");

		//员工首页
		registry.addViewController("/staff/main").setViewName("/staff/main");
		registry.addViewController("/staff/index").setViewName("/staff/index");
		registry.addViewController("/staff/form").setViewName("/staff/form");
        registry.addViewController("/staff/detail").setViewName("/staff/detail");
		registry.addViewController("/staff/process").setViewName("/staff/process");
		registry.addViewController("/staff/workinghour").setViewName("/staff/workinghour");
		registry.addViewController("/staff/addworkinghour").setViewName("/staff/addworkinghour");
		registry.addViewController("/staff/editworkinghour").setViewName("/staff/editworkinghour");
		registry.addViewController("/staff/add").setViewName("/staff/add");
		registry.addViewController("/staff/modifyMemberRole").setViewName("/staff/modifyMemberRole");

		//设备
		registry.addViewController("/staff/deviceManage").setViewName("/staff/deviceManage");
		registry.addViewController("/staff/deviceAdd").setViewName("/staff/deviceAdd");
		registry.addViewController("/staff/deviceBorrow").setViewName("/staff/deviceBorrow");
		registry.addViewController("/staff/deviceMy").setViewName("/staff/deviceMy");
		registry.addViewController("/staff/deviceBorrowable").setViewName("/staff/deviceBorrowable");

		//配置
		registry.addViewController("/staff/config").setViewName("/staff/config");


	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//单个文件最大
		factory.setMaxFileSize("102400KB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("102400KB");
		return factory.createMultipartConfig();
	}
}
