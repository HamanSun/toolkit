package org.relaxation.common.utills;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.cache.*;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaUtil {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        jdkImmutableListTest2();
//        guavaImmutableListTest2();
//        guavaJoiner();
//        guavaSplit();
        guavaCache();
    }


    /**
     * jdk的不可变集合
     * 保证线程安全。在并发程序中，使用不可变集合既保证线程的安全性，也大大地增强了并发时的效率（跟并发锁方式相比）。
     * 如果一个对象不需要支持修改操作，不可变的集合将会节省空间和时间的开销。
     * 可以当作一个常量来对待，并且集合中的对象在以后也不会被改变。
     */
    static void jdkImmutableListTest1(){
        List<String> itDalao = new ArrayList<>();
        itDalao.add("马云");
        itDalao.add("马化腾");
        List<String> jdkImmutableList = Collections.unmodifiableList(itDalao);
        jdkImmutableList.add("雷军");
        for(String item : jdkImmutableList){
            System.out.println(item);
        }
    }
    static void jdkImmutableListTest2(){
        List<String> itDalao = new ArrayList<>();
        itDalao.add("马云");
        itDalao.add("马化腾");
        List<String> jdkImmutableList = Collections.unmodifiableList(itDalao);
        itDalao.add("雷军");
        for(String item : jdkImmutableList){
            System.out.println(item);
        }
    }
    /**
     * guava的不可变集合
     * 保证线程安全。在并发程序中，使用不可变集合既保证线程的安全性，也大大地增强了并发时的效率（跟并发锁方式相比）。
     * 如果一个对象不需要支持修改操作，不可变的集合将会节省空间和时间的开销。
     * 可以当作一个常量来对待，并且集合中的对象在以后也不会被改变。
     */
    static void guavaImmutableListTest1(){
        List<String> itDalao = new ArrayList<>();
        itDalao.add("马云");
        itDalao.add("马化腾");
        List<String> guavaImmutableList = ImmutableList.copyOf(itDalao);
        itDalao.add("雷军");
        for(String item : guavaImmutableList){
            System.out.println(item);
        }
    }
    /**
     * guava的不可变集合
     * 保证线程安全。在并发程序中，使用不可变集合既保证线程的安全性，也大大地增强了并发时的效率（跟并发锁方式相比）。
     * 如果一个对象不需要支持修改操作，不可变的集合将会节省空间和时间的开销。
     * 可以当作一个常量来对待，并且集合中的对象在以后也不会被改变。
     */
    static void guavaImmutableListTest2(){
        List<String> itDalao = new ArrayList<>();
        itDalao.add("马云");
        itDalao.add("马化腾");
        List<String> guavaImmutableList = ImmutableList.copyOf(itDalao);
        guavaImmutableList.add("雷军");
        for(String item : guavaImmutableList){
            System.out.println(item);
        }
    }

    /**
     * 字符串拼接
     */
    static void guavaJoiner(){
       String ret1 = Joiner.on(",").skipNulls().join("马云", null, "马化腾");
       String ret2 = Joiner.on(",").useForNull("2b").join("马云", null, "马化腾");
       System.out.println(ret1);
       System.out.println(ret2);

    }

    /**
     * 字符串拆分
     */
    static void guavaSplit(){
        Iterable<String> a = Splitter.on(",").omitEmptyStrings().trimResults().split("马云 ,马化腾,,  abc");
        Iterator<String> iterator = a.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    static void guavaCache() throws InterruptedException, ExecutionException {
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return null;
            }
        };
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, String> removalNotification) {
                        System.out.println(String.format("key:%s, val: %s, cause: %s", removalNotification.getKey(),removalNotification.getValue(), removalNotification.getCause()));
                    }
                }).build(cacheLoader);
        loadingCache.put("a","zs");
        loadingCache.put("b","lisi");
        loadingCache.put("c","ww");
        //手动失效
        loadingCache.invalidate("b");
        System.out.println(loadingCache.get("b"));
        System.out.println(loadingCache.get("c"));
        Thread.sleep(4000);
        System.out.println(loadingCache.get("c"));
    }
}
