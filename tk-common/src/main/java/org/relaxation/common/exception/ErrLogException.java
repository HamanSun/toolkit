package org.relaxation.common.exception;

import lombok.Data;

import java.util.Date;

/**
 * @program ErrLogException
 * @description: TODO
 * @author: jjsunw
 * @create: 2020/4/14
 **/
@Data
public class ErrLogException extends RuntimeException {
    private String optuserid;
    private String errmsg;
    private Date opttime;

    public ErrLogException(String optuserid, String errmsg, Date opttime) {
        super(errmsg);
        this.errmsg = errmsg;
        this.opttime = opttime;
        this.optuserid = optuserid;
    }
}
