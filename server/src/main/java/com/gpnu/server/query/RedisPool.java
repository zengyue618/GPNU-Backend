package com.gpnu.server.query;

import com.gpnu.core.exception.ErrorCodes;
import com.gpnu.core.exception.GPNUException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisPool {

    @Value("${custom.redis.ip}")
    private String redisIp;
    @Value("${custom.redis.port}")
    private int redisPort;

    private JedisPool jedisPool;

    public RedisPool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            jedisPool = new JedisPool(config, redisIp,redisPort);
        } catch (Exception e) {
            throw new GPNUException("init redis pool error", ErrorCodes.SYSTEM_EXCEPTION);
        }
    }

    private static final RedisPool INSTANCE = new RedisPool();

    public static RedisPool getInstance() {
        return INSTANCE;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public Jedis getConnection() {
        return jedisPool.getResource();
    }
}
