package com.liurq.os;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Desc 监控控制类
 * @Author liurq
 * @Date 2020/12/14 17:51
 */
//@SpringBootApplication(scanBasePackages = {"cn.hywy.**"}, exclude = {DataSourceAutoConfiguration.class, ExceptionAutoConfiguration.class, ValidatorAutoConfiguration.class})
@SpringBootApplication
public class AgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentApplication.class, args);
    }

}
