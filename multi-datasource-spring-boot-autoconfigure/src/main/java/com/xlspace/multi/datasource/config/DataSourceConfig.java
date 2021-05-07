package com.xlspace.multi.datasource.config;

import com.xlspace.multi.datasource.routing.DataSourceRouting;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @ClassName: DataSourceConfig
 * @Description
 * @Author gxy
 * @Date 2021/4/29 22:07
 * @Copyright: 2021 gxy. All rights reserved.
 **/
@Log4j2
@Configuration
@Import({DataSourceRouting.class})
public class DataSourceConfig {
    /**
     * 将自定义datasourceRouting注入sqlSessionFactory
     * @param dataSourceRouting
     * @return
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSourceRouting dataSourceRouting) {
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(dataSourceRouting);
            return bean.getObject();
        } catch (Exception e) {
            log.error("create sqlSessionFactory error: {}", e);
        }
        return null;
    }

    /**
     * 将自定义dataSourceRouting注入txManager,防止事务失效
     * @param dataSourceRouting
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSourceRouting dataSourceRouting) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSourceRouting);
        return transactionManager;
    }
}
