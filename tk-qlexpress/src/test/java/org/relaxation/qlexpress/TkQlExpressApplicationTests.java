package org.relaxation.qlexpress;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.relaxation.qlexpress.component.QlExpressUtil;
import org.relaxation.qlexpress.component.properties.Operator;
import org.relaxation.qlexpress.component.properties.QLExpressProperties;
import org.relaxation.qlexpress.service.ServiceMethodTest01;
import org.relaxation.qlexpress.utils.ClassMethodTest01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TkQlExpressApplicationTests {
    @Autowired
    private QLExpressProperties expressProperties;
    @Autowired
    private ServiceMethodTest01 serviceMethodTest01;
    @Autowired
    private QlExpressUtil qlExpressUtil;


    @Test
    void contextLoads() {

    }
    @Test
    void testGetList(){
        List<Operator> operators = expressProperties.getOperator();
        for(Operator opt : operators){
            System.out.println(StringUtils.join(opt.getKeyword(),">", opt.getRealkeyword(),">", opt.getErrinfo()));
        }
    }

    @Test
    void testServiceMethod01(){
        serviceMethodTest01.dynamicParamsMethodTest("1","2", 3, 4, 5);
    }
    @Test
    void testClassMethod01(){
        ClassMethodTest01.dynamicParamsMethodTest("a", "b", 1, 2);
    }

    @Test
    void testQLFunctionOfServiceMethod() throws Exception {
        Map<String, Object> context = new HashMap<>();
        context.put("id", 1);
        context.put("年龄", 20);
        context.put("工资", 50000);
        context.put("生日", new Date());
        qlExpressUtil.execute("测试service方法动态参数(id, 年龄, 工资, 生日)", context);
    }

    @Test
    void testQLFunctionOfClassMethod() throws Exception {
        Map<String, Object> context = new HashMap<>();
        context.put("id", 1);
        context.put("年龄", 20);
        context.put("工资", 50000);
        qlExpressUtil.execute("测试class方法动态参数(id, 年龄, 工资)", context);
    }

}
