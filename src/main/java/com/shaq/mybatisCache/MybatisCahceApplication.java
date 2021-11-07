package com.shaq.mybatisCache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.shaq.mybatisCache.mbg.mapper"})
public class MybatisCahceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisCahceApplication.class, args);
    }

}
