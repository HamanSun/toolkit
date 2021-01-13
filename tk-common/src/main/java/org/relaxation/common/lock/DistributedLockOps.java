package org.relaxation.common.lock;

import lombok.extern.slf4j.Slf4j;
import org.relaxation.common.lock.mysql.Lock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DistributedLockOps {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * mySQL加锁
     *
     * @param methodName
     * @return
     */
    public boolean mysqlLock(String methodName) {
        String threadName = Thread.currentThread().getName();
        String anyExist = "select * from t_lock where method_name=?";
        String threadHolding = "select * from t_lock where method_name = ? and lock_holder_thread = ?";
        String insertOne = "insert into t_lock(method_name, lock_holder_thread) values (?, ?)";
        List<Lock> exist = jdbcTemplate.query(anyExist, new Object[]{methodName}, new BeanPropertyRowMapper<>(Lock.class));
        if (exist.size() == 0) {
            while (true) {
                try {
                    jdbcTemplate.update(insertOne, new Object[]{methodName, threadName});
                    return true;
                } catch (Exception e) {
                    continue;
                }
            }
        } else {
            List<Lock> threadWith = jdbcTemplate.query(threadHolding, new Object[]{methodName, threadName}, new BeanPropertyRowMapper<>(Lock.class));
            // 如果当前线程已经持有锁，不再竞争，可重入；避免死锁
            if (threadWith.size() != 0) {
                return true;
            } else {
                while (true) {
                    try {
                        jdbcTemplate.update(insertOne, new Object[]{methodName, threadName});
                        return true;
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        }

    }

    /**
     * mysql 解锁
     *
     * @param methodName
     */
    public void mysqlUnLock(String methodName) {
        String del = "delete from t_lock where method_name = ? and lock_holder_thread = ?";
        try {
            jdbcTemplate.update(del, new Object[]{methodName, Thread.currentThread().getName()});
        } catch (Exception e) {
            // 解锁失败，使用定时任务删除或是while循环删除
            log.error("线程：{}解锁：{} 失败！", Thread.currentThread().getName(), methodName);
        }
    }

    public void redisLockAndRetry(String methodName, int expire, int retryTimes) {

    }

    public void redisUnLock() {

    }

    public void zkLock() {

    }

    public void zkUnLock() {

    }
}
