package com.bdjr.client.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.client.app、
 * @email: cy880708@163.com
 * @date: 2019/1/30 下午4:26
 * @mofified By:
 */
@SpringBootApplication
@EnableFeignClients
public class ClientAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientAppApplication.class, args);
    }
}
