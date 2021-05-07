package com.xlspace.multi.datasource.routing;

import com.xlspace.multi.datasource.config.DataSourceProperty;
import com.xlspace.multi.datasource.constant.Constants;
import com.xlspace.multi.datasource.exception.NoMasterException;
import com.xlspace.multi.datasource.holder.DataSourceHolder;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DataSourceRouting
 * @Description
 * @Author gxy
 * @Date 2021/4/29 22:10
 * @Copyright: 2021 gxy. All rights reserved.
 **/
@Configuration
@EnableConfigurationProperties(DataSourceProperty.class)
public class DataSourceRouting extends AbstractRoutingDataSource {

    private DataSourceProperty dataSourceProperty;

    @Autowired
    private void setDataSourceProperty(DataSourceProperty dataSourceProperty) {
        this.dataSourceProperty = dataSourceProperty;
    }

    @Override
    public void afterPropertiesSet() {
        Map<String, HikariConfig> hikariConfigMap = dataSourceProperty.getMulti();
        Map<Object, Object> dataSourceMap = new HashMap<Object, Object>(hikariConfigMap.size());
        for (Map.Entry<String, HikariConfig> entry : hikariConfigMap.entrySet()) {
            HikariDataSource dataSource = new HikariDataSource(entry.getValue());
            dataSourceMap.put(entry.getKey(), dataSource);
        }
        // 必须指定master datasource否则抛出异常
        if (dataSourceMap.get(Constants.MASTER) == null) {
            logger.error("No master datasource found!");
            throw new NoMasterException("No master datasource found!");
        }
        Assert.notNull(dataSourceMap.get(Constants.MASTER), () -> {
            return "";
        });
        this.setDefaultTargetDataSource(dataSourceMap.get(Constants.MASTER));
        this.setTargetDataSources(dataSourceMap);
        super.afterPropertiesSet();
    }

    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSourceKey();
    }
}
