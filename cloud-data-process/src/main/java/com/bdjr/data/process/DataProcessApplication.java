package com.bdjr.data.process;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.data.process、
 * @email: cy880708@163.com
 * @date: 2019/1/30 下午3:56
 * @mofified By:
 */
@SpringBootApplication
@MapperScan("com.bdjr.data.process.mapper")
public class DataProcessApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataProcessApplication.class, args);
    }
}
