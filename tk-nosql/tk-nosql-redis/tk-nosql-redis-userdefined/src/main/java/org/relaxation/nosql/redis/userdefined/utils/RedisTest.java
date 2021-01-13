package org.relaxation.nosql.redis.userdefined.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * redis 锁测试
 */
@Slf4j
public class RedisTest {
    private static int INVERTORY = 1001;
    private static final int NUM = 1000;
    private static LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
    CountDownLatch countDownLatch = new CountDownLatch(NUM);

    public static void main(String[] args) {
        withlock();
    }

    public static void nolock() {
        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(INVERTORY, INVERTORY, 10L, linkedBlockingQueue);
        for (int i = 0; i < 10; i++) {
            try {
                if (i == 3) {
                    throw new UnsupportedOperationException("出错了");
                }
                System.out.println(i);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    public static void withlock() {
        try {
            for (int i = 0; i < 10; i++) {
                if (i == 3) {
                    throw new UnsupportedOperationException("出错了");
                }
                System.out.println(i);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
