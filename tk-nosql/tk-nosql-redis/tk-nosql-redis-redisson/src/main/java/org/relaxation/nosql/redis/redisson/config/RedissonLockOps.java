package org.relaxation.nosql.redis.redisson.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @program RedissonLockOps
 * @description: 锁操作类
 * @author: jjsunw
 * @create: 2020/5/7
 **/
@Slf4j
@Component
public class RedissonLockOps {
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 获取锁
     *
     * @param key           命名规则：可以是模块名-方法名-版本号
     * @param expireSeconds
     * @return
     */
    public boolean lock(String key, long expireSeconds) {
        RLock rLock = redissonClient.getLock(key);
        try {
            if (rLock.tryLock(expireSeconds, expireSeconds, TimeUnit.SECONDS)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 释放锁
     *
     * @param key
     */
    public void unlock(String key) {
        RLock rLock = redissonClient.getLock(key);
        if(rLock.isHeldByCurrentThread()){
            rLock.unlock();
        }
    }
}
