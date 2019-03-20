package com.spring.cloud.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud
 * @package: com.spring.cloud.common
 * @email: cy880708@163.com
 * @date: 2018/11/2 下午5:42
 * @mofified By:
 */
@SpringBootApplication
@MapperScan("com.bdjr.tt.mapper")
public class CloudCommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudCommonApplication.class, args);
    }
}
