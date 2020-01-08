package org.relaxation.mybatisplus.config.multipledatasource;

/**
 * @program: DBTypeEnum
 * @description: DBType枚举类
 * @author: jjsunw
 * @create: 2020/1/6
 **/
public enum DBTypeEnum {
    db1("db1"), db2("db2");
    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
