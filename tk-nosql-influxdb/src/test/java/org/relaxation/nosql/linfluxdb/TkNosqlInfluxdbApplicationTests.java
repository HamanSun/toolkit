package org.relaxation.nosql.linfluxdb;

import org.junit.jupiter.api.Test;
import org.relaxation.nosql.linfluxdb.component.InfluxdbRepository;
import org.relaxation.nosql.linfluxdb.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TkNosqlInfluxdbApplicationTests {

    @Autowired
    private InfluxdbRepository influxdbRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setId(110);
        user.setName("sunjj");
        user.setAge(20);
        user.setLastAccessMedia("http://www.baidu.com");
        influxdbRepository.save(user);
    }

    @Test
    public void testQuery(){
        influxdbRepository.list();
    }

}
