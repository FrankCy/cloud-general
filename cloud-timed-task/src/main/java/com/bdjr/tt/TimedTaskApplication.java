package com.bdjr.tt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.bdjr.tt、
 * @email: cy880708@163.com
 * @date: 2019/3/19 下午6:51
 * @mofified By:
 */
@SpringBootApplication
@MapperScan("com.bdjr.tt.mapper")
public class TimedTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimedTaskApplication.class, args);
    }
}
