package com.hx.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class DistributeLockService {

    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接数
        config.setMaxTotal(200);
        //设置最大空闲数
        config.setMaxIdle(8);
        //设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "192.168.137.130", 6379, 3000);
    }

    DistributeLock lock = new DistributeLock(pool);

    int n = 500;
    public void seckill() {
        //返回锁的value值，供释放锁进行判断
        String identifier = lock.lockWithTimeOut("resource", 5000, 1000);
        System.out.println(Thread.currentThread().getName() + "获得了锁");
        System.out.println(--n);
        lock.releaseLock("resource", identifier);
    }
}
