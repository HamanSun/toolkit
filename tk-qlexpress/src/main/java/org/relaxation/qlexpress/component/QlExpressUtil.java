package org.relaxation.qlexpress.component;

import com.ql.util.express.DynamicParamsUtil;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import org.relaxation.qlexpress.component.properties.Operator;
import org.relaxation.qlexpress.component.properties.QLExpressProperties;
import org.relaxation.qlexpress.utils.ClassMethodTest01;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * （1）打通了spring容器，通过扩展IExpressContext->QLExpressContext
 * 获取本地变量的时候，可以获取到spring的bean
 * （2）在runner初始化的时候，使用了函数映射功能：addFunctionOfServiceMethod
 * （3）在runner初始化的时候，使用了代码映射功能：addMacro
 */
@Component
public class QlExpressUtil implements ApplicationContextAware {

    @Autowired
    private QLExpressProperties qlExpressProperties;

    private static ExpressRunner runner;

    static {
        runner = new ExpressRunner(true, false);
        // 短路逻辑, 默认是true：启用短路；设置成false，可验证所有逻辑
        runner.setShortCircuit(false);
        // 打开动态参数开关， 方法调用更灵活
        DynamicParamsUtil.supportDynamicParams = true;
    }

    private static boolean isInitialRunner = false;
    private ApplicationContext applicationContext;// spring上下文

    /**
     * @param statement 执行语句
     * @param context   上下文
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Object execute(String statement, Map<String, Object> context) throws Exception {
        initRunner(runner);
        IExpressContext expressContext = new QLExpressContext(context, applicationContext);
        return runner.execute(statement, expressContext, null, true, false);
    }

    private void initRunner(ExpressRunner runner) {
        if (isInitialRunner == true) {
            return;
        }
        synchronized (runner) {
            if (isInitialRunner == true) {
                return;
            }
            try {
                // 添加操作符
                List<Operator> optWithAlias = qlExpressProperties.getOperator();
                for(Operator opt : optWithAlias){
                    runner.addOperatorWithAlias(opt.getKeyword(), opt.getRealkeyword(), opt.getErrinfo());
                }
                // 添加service方法的功能函数
                runner.addFunctionOfServiceMethod("测试service方法动态参数", applicationContext.getBean("serviceMethodTest01"), "dynamicParamsMethodTest", new Class[]{Object[].class}, null);
                runner.addFunctionOfClassMethod("测试class方法动态参数", ClassMethodTest01.class, "dynamicParamsMethodTest", new Class[]{Object[].class}, null);
                // 宏定义，全局的
                runner.addMacro("判定用户是否vip", "userDO.salary>200000");

            } catch (Exception e) {
                throw new RuntimeException("初始化失败表达式", e);
            }
        }
        isInitialRunner = true;
    }

    public void setApplicationContext(ApplicationContext aContext)
            throws BeansException {
        applicationContext = aContext;
    }

}
