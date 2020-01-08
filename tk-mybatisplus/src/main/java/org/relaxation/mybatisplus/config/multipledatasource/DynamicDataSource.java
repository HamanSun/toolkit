package org.relaxation.mybatisplus.config.multipledatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @program DynamicDataSource
 * @description: 动态数据源决策
 * @author: jjsunw
 * @create: 2020/1/6
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }
}
