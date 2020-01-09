package org.relaxation.nosql.mongo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.relaxation.nosql.mongo.pojo.Customer;
import org.relaxation.nosql.mongo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class TkNosqlMongoApplicationTests {

    @Autowired
    private CustomerRepository repository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSave(){
        List<Customer> list = new ArrayList<>();
        for(int i=1;i<12;i++){
            Customer customer = new Customer();
            customer.setId("0" + i);
            customer.setUsername("sunjunjie" + i);
            customer.setBirth(LocalDate.of(1988,i,23));
            list.add(customer);
        }
        repository.insert(list);
    }

    @Test
    public void testfindbyusername(){
        Customer customer = repository.findByUsername("sunjunjie");
        Assertions.assertEquals("1", customer.getId());
    }

    @Test
    public void testfindbybirth(){
        Customer customer = repository.findByBirth(LocalDate.of(1988,7,23));
        Assertions.assertEquals("sunjunjie",customer.getUsername());
    }

    @Test
    public void testfindbyExample(){
        Optional<Customer> customer = repository.findOne(new Example<Customer>() {
            @Override
            public Customer getProbe() {
                Customer customer = new Customer();
                customer.setId("1");
                return customer;
            }

            @Override
            public ExampleMatcher getMatcher() {
                return ExampleMatcher.matching();
            }
        });
        Assertions.assertEquals("sunjunjie1",customer.get().getUsername());
    }

    @Test
    public void testdeletebyusername(){
        Long re =repository.deleteByUsername("sunjunjie1");
        System.out.println(re);
    }

    @Test
    public void testupdate(){
        Customer customer = new Customer();
        customer.setId("1212");
        customer.setUsername("sunjunjie11" );
        customer.setBirth(LocalDate.of(1988,12,23));
        repository.save(customer);
    }

}
