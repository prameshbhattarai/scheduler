package com.scheduler.scheduler.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class RoutingDataSourceConfig extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return ClientContextHolder.getContext();
    }

    public void initDataSources(DataSource dataSource1, DataSource dataSource2, DataSource dataSource3) {
        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put(ClientContextHolder.CLIENT.CLIENT1, dataSource1);
        dsMap.put(ClientContextHolder.CLIENT.CLIENT2, dataSource2);
        dsMap.put(ClientContextHolder.CLIENT.CLIENT3, dataSource3);
        this.setTargetDataSources(dsMap);
    }
}
