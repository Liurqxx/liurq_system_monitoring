package com.liurq.os.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author liurq
 * @desc zookeeper配置类
 * @date 2022-02-21  22:02
 */
@Configuration
@ConfigurationProperties(value = "sfs")
public class zookeeperConfig {
}
