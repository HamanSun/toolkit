package org.relaxation.nosql.linfluxdb.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * @program User
 * @description: user对象
 * @author: jjsunw
 * @create: 2020/1/13
 **/
@Data
public class User {

    private Integer id;
    private String name;
    private int age;
    private String lastAccessMedia;
    private Long birthTimePoint;
    private Date birthDate;
    private LocalDate birthLocalDate;
    private LocalDateTime birthLocalDateTime;
}
