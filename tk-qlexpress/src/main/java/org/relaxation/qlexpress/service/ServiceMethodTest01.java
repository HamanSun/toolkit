package org.relaxation.qlexpress.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceMethodTest01 {

    public void dynamicParamsMethodTest(Object ...params){
        for(Object o : params){
            System.out.println(o);
        }
    }
}
