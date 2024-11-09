package xyz.luckypeak.playground.daomybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xyz.luckypeak.playground.daomybatisdemo.dao")
public class DaoMybatisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaoMybatisDemoApplication.class, args);
	}
}
