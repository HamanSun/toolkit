package org.relaxation.nosql.mongo.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

/**
 * @program Customer
 * @description: 测试对象
 * @author: jjsunw
 * @create: 2020/1/9
 **/
@Data
public class Customer {
    @Id
    private String Id;
    private String username;
    private LocalDate birth;
}
