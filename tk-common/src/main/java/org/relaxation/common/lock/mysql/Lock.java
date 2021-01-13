package org.relaxation.common.lock.mysql;

import lombok.Data;

import java.util.Date;

@Data
public class Lock {
    /**自增主键*/
    private Integer id;
    /**唯一全限定名*/
    private String methodName;
    /**持有锁的线程名，可重入控制*/
    private String LockHolderThread;
    /**数据创建时间*/
    private Date createTime;
    /**数据更新时间*/
    private Date updateTime;
}
