package org.relaxation.common.lock.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

@Configuration
public class LuaConfiguration {

    @Bean
    public DefaultRedisScript<Integer> redisLockScript(){
        DefaultRedisScript<Integer> redisLockScript = new DefaultRedisScript<>();
        redisLockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/lock.lua")));
        redisLockScript.setResultType(Integer.class);
        return redisLockScript;
    }
    @Bean
    public DefaultRedisScript<Integer> redisUnLockScript(){
        DefaultRedisScript<Integer> redisUnLockScript = new DefaultRedisScript<>();
        redisUnLockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/unlock.lua")));
        redisUnLockScript.setResultType(Integer.class);
        return redisUnLockScript;
    }
}
