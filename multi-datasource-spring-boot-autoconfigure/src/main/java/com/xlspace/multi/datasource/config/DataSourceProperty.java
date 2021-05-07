package com.xlspace.multi.datasource.config;

import com.zaxxer.hikari.HikariConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @ClassName: DataSourceConfig
 * @Description
 * @Author gxy
 * @Date 2021/4/29 21:53
 * @Copyright: 2021 gxy. All rights reserved.
 **/
@Data
@ConfigurationProperties("spring.datasource")
public class DataSourceProperty {
    private Map<String, HikariConfig> multi;
}
