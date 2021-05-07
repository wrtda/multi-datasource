package com.xlspace.multi.datasource.holder;

import org.springframework.util.StringUtils;

/**
 * @ClassName: DataSourceHolder
 * @Description
 * @Author gxy
 * @Date 2021/4/27 23:56
 * @Copyright: 2021 gxy. All rights reserved.
 **/
public class DataSourceHolder {
    private static ThreadLocal<String> dataSourceKey = new ThreadLocal<>();
    private static String MASTER = "master";

    public static String getDataSourceKey() {
        return StringUtils.isEmpty(dataSourceKey.get()) ? MASTER : dataSourceKey.get();
    }

    public static void setDataSourceKey(String key) {
        dataSourceKey.set(key);
    }

    public static void clear() {
        dataSourceKey.remove();
    }
}
