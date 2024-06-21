package com.example.meogirlmusic;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.meogirlmusic.mapper")
public class MeogirlMusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeogirlMusicApplication.class, args);
	}

}
