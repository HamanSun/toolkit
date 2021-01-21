package org.relaxation.qlexpress.utils;

public class ClassMethodTest01 {

    public static void dynamicParamsMethodTest(Object ...params){
        for(Object o : params){
            System.out.println(o);
        }
    }
}
