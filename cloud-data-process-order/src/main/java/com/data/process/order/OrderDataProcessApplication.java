package com.data.process.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.data.process.order、
 * @email: cy880708@163.com
 * @date: 2019/2/14 下午4:30
 * @mofified By:
 */
@SpringBootApplication
@MapperScan("com.data.process.order.mapper")
public class OrderDataProcessApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderDataProcessApplication.class, args);
    }
}
