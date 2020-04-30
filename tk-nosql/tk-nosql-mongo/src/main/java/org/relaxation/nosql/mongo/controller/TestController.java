package org.relaxation.nosql.mongo.controller;

import org.relaxation.nosql.mongo.pojo.Customer;
import org.relaxation.nosql.mongo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @program TestController
 * @description: 测试
 * @author: jjsunw
 * @create: 2020/1/9
 **/
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private CustomerRepository repository;

    @GetMapping("add")
    public void add(){
        List<Customer> list = new ArrayList<>();
        for(int i=1;i<12;i++){
            Customer customer = new Customer();
            customer.setId("0" + i);
            customer.setUsername("jjsunw" + i);
            customer.setBirth(LocalDate.of(1988,i,23));
            list.add(customer);
        }
        repository.insert(list);
    }
}
