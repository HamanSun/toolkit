package org.relaxation.common.test;

import org.relaxation.common.annotation.ErrLog;
import org.relaxation.common.exception.ErrLogException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program Tester
 * @description: 测试接口类
 * @author: jjsunw
 * @create: 2020/4/14
 **/
@RestController
@RequestMapping("test")
public class Tester {

    @GetMapping("exp")
    @ErrLog(module = "系统测试", opttype = "test", optdesc = "小二哥做系统测试")
    public void exptest(){
        throw new ErrLogException("小二哥", "手动抛出一个自定义异常，测试能否获取到封装结果", new Date());
    }
}
