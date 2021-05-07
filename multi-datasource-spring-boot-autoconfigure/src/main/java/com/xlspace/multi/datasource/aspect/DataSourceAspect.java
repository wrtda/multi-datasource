package com.xlspace.multi.datasource.aspect;

import com.xlspace.multi.datasource.annotation.DataSource;
import com.xlspace.multi.datasource.holder.DataSourceHolder;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @ClassName: DataSourceAspect
 * @Description
 * @Author gxy
 * @Date 2021/4/27 23:52
 * @Copyright: 2021 gxy. All rights reserved.
 **/
@Log4j2
@Aspect
@Component
public class DataSourceAspect implements Ordered {

    @Pointcut("@annotation(com.xlspace.multi.datasource.annotation.DataSource)")
    private void pointCut() {
    }

    @Before("@annotation(dataSource)")
    public void before(DataSource dataSource) {
        if (log.isDebugEnabled()) {
            log.debug("current datasource key is {}", dataSource.value());
        }
        DataSourceHolder.setDataSourceKey(dataSource.value());
    }

    @After("pointCut()")
    public void after() {
        DataSourceHolder.clear();
    }

    public int getOrder() {
        return 0;
    }
}
