package com.bdjr.client.pc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: PACKAGE_NAME、
 * @email: cy880708@163.com
 * @date: 2019/2/19 下午4:41
 * @mofified By:
 */
@SpringBootApplication
@EnableFeignClients
public class ClientPcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientPcApplication.class, args);
    }
}
