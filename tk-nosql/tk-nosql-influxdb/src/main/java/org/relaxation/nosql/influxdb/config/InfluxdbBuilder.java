package org.relaxation.nosql.influxdb.config;

import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program InfluxdbBuilder
 * @description: 定义influxdb bean
 * @author: jjsunw
 * @create: 2020/1/13
 **/
@Slf4j
@Configuration
public class InfluxdbBuilder {
    @Value("${spring.influx.url}")
    private String url;
    @Value("${spring.influx.user}")
    private String username;
    @Value("${spring.influx.password}")
    private String password;
    @Value("${spring.influx.database}")
    private String database;

    @Bean
    public InfluxDB influxDB() {
        InfluxDB influxDB = InfluxDBFactory.connect(url, username, password);
        try {
            influxDB.query(new Query("CREATE DATABASE " + database));//创建数据库
            influxDB.setDatabase(database).enableBatch();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            influxDB.setRetentionPolicy("autogen");
        }
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        return influxDB;
    }
}
