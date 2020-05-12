package org.relaxation.nosql.redis.redisson.controller;

import org.relaxation.nosql.redis.redisson.aspect.DistributedLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program TestController
 * @description: 测试
 * @author: jjsunw
 * @create: 2020/5/7
 **/
@RestController
@RequestMapping("test")
public class TestController {

    private static int INVENTORY_1 = 100;
    private static int INVENTORY_2 = 100;

    @GetMapping("sub")
    public void test1(){
        if(INVENTORY_1 > 0){
            String name = Thread.currentThread().getName();
            System.out.println(String.format("用户 %s 抢单成功，库存剩余： %s", name, INVENTORY_1));
            INVENTORY_1 -- ;
        }
    }

    @GetMapping("lsub")
    @DistributedLock(value = "order", expire = 1)
    public void test2(){
        if(INVENTORY_2 > 0){
            String name = Thread.currentThread().getName();
            System.out.println(String.format("用户 %s 抢单成功，库存剩余： %s", name, INVENTORY_2));
            INVENTORY_2 -- ;
        }else{
            System.out.println("=================================================已抢光！！！！！！");
        }
    }
}
