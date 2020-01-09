package org.relaxation.nosql.mongo.repository;

import org.relaxation.nosql.mongo.pojo.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

/**
 * @program CustomerRepository
 * @description: TODO
 * @author: jjsunw
 * @create: 2020/1/9
 **/
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByUsername(String username);
    Customer findByBirth(LocalDate brithdate);
    Long deleteByUsername(String username);

}
