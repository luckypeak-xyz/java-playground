package xyz.luckypeak.playground.mybatislearning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xyz.luckypeak.playground.mybatislearning.dao")
public class MybatisLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisLearningApplication.class, args);
	}
}
