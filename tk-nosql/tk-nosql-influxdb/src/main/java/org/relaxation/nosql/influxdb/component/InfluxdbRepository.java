package org.relaxation.nosql.influxdb.component;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.relaxation.nosql.influxdb.pojo.User;
import org.relaxation.nosql.influxdb.pojo.AccessLog;
import org.relaxation.nosql.influxdb.pojo.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @program InfluxdbRepository
 * @description: CDUL
 * @author: jjsunw
 * @create: 2020/1/13
 **/
@Component
public class InfluxdbRepository {
    @Autowired
    private InfluxDB influxDB;
    public void save(User user){
        Point point = Point.measurement("usertrace")
                .time(System.currentTimeMillis(), TimeUnit.MICROSECONDS)
                .tag("id", String.valueOf(user.getId()))
                .addField("name", user.getName())
                .addField("age", user.getAge())
                .addField("accessUrl", user.getLastAccessMedia())
                .build();
        influxDB.write(point);
    }

    public void list(){
        QueryResult queryResult = influxDB.query(new Query("select * from usertrace","toolkit"));
        queryResult.getResults().forEach(System.out::println);
    }

    public void addLog(){
        LogInfo logInfo = LogInfo.builder().deviceId("").module("").level("").msg("").build();
        Point point = Point.measurementByPOJO(logInfo.getClass()).addFieldsFromPOJO(logInfo).time(System.currentTimeMillis(), TimeUnit.MILLISECONDS).build();
        influxDB.write(point);
    }

    public void save(AccessLog accessLog){
        Point point = Point.measurement("accesslog")
                .time(System.currentTimeMillis(), TimeUnit.MICROSECONDS)
                .tag("userId", accessLog.getUserId())
                .tag("moduleId", accessLog.getModuleId())
                .addField("accessCount", 1)
                .build();
        influxDB.write(point);
    }

    public QueryResult list(String sql){
        return influxDB.query(new Query(sql));
    }
}
