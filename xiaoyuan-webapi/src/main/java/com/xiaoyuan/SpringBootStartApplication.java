package com.xiaoyuan;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/*@ComponentScan(basePackages={"com.demo.supermarket"})*/
//扫描mapper
@EnableSwagger2
@SpringBootApplication

@MapperScan(basePackages="com.xiaoyuan.mapper")
@ComponentScan(basePackages = "com.xiaoyuan.*")
public class SpringBootStartApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(SpringBootStartApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(SpringBootStartApplication.class);
	}
}
