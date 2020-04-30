package org.relaxation.nosql.linfluxdb.pojo;

import lombok.Data;

/**
 * @program AccessLog
 * @description: TODO
 * @author: jjsunw
 * @create: 2020/1/14
 **/
@Data
public class AccessLog {

    private String userId;
    private String moduleId;
    private Integer accessCount;
}
