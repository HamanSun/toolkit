package org.relaxation.qlexpress.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private Date birth;
    private Integer age;
    private BigDecimal salary;
}
