package org.relaxation.nosql.influxdb.controller;

import org.relaxation.nosql.influxdb.component.InfluxdbRepository;
import org.relaxation.nosql.influxdb.pojo.AccessLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program AccessLogController
 * @description: 访问历史
 * @author: jjsunw
 * @create: 2020/1/14
 **/
@RestController
public class AccessLogController {
    @Autowired
    private InfluxdbRepository influxdbRepository;

    @PostMapping("access/{moduleId}")
    public void access(@PathVariable("moduleId") String moduleId){
        AccessLog accessLog = new AccessLog();
        accessLog.setModuleId(moduleId);
        accessLog.setUserId("1");
        influxdbRepository.save(accessLog);
    }

    @GetMapping("access/history")
    public List<String> orderByModuleId(){
        String sql = "";
        return null;
    }
}
