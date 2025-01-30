package cn.yong.boot.redis.utils;

import lombok.RequiredArgsConstructor;
import org.redisson.api.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/30 13:14
 */
@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedissonClient redissonClient;

    /**
     * 向redis中设置值，并设置时间为永不过期（除非被淘汰策略淘汰）
     *
     * @param key   键
     * @param value 值
     * @param <T>   泛型对象
     */
    public <T> void setValue(String key, T value) {
        redissonClient.<T>getBucket(key).set(value);
    }

    /**
     * @param key     键
     * @param value   值
     * @param expired 过期时间（单位为毫秒）
     * @param <T>     泛型对象
     */
    public <T> void setValue(String key, T value, long expired) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.setIfAbsent(value, Duration.ofMillis(expired));
    }

    /**
     * @param key 键
     * @param <T> 泛型对象
     * @return 泛型对象
     */
    public <T> T getValue(String key) {
        return redissonClient.<T>getBucket(key).get();
    }

    /**
     * 根据表达式获取redis中满足表达式的 键
     *
     * @param pattern 表达式
     * @return 满足表达式的值
     */
    public Collection<String> keys(String pattern) {
        Stream<String> stream = redissonClient.getKeys().getKeysStreamByPattern(pattern);
        return stream.collect(Collectors.toList());
    }

    /**
     * 获取该键的剩余存活时间
     *
     * @param key 键
     * @return 剩余存活时间
     */
    public <T> long getTimeToLive(String key) {
        RBucket<T> rBucket = redissonClient.getBucket(key);
        return rBucket.remainTimeToLive();
    }


    public boolean expire(String key, Duration duration) {
        RBucket<Object> rBucket = redissonClient.getBucket(key);
        return rBucket.expire(duration);
    }


    public Long getTimeOut(String key) {
        RBucket<Object> rBucket = redissonClient.getBucket(key);
        return rBucket.remainTimeToLive();
    }

    public boolean expire(String key, Long seconds) {
        RBucket<Object> rBucket = redissonClient.getBucket(key);
        return rBucket.expire(Duration.ofSeconds(seconds));
    }


    public <T> RQueue<T> getQueue(String key) {
        return redissonClient.getQueue(key);
    }


    public <T> RBlockingQueue<T> getBlockingQueue(String key) {
        return redissonClient.getBlockingQueue(key);
    }


    public <T> RDelayedQueue<T> getDelayedQueue(RBlockingQueue<T> rBlockingQueue) {
        return redissonClient.getDelayedQueue(rBlockingQueue);
    }


    public long incr(String key) {
        return redissonClient.getAtomicLong(key).incrementAndGet();
    }


    public long incrBy(String key, long delta) {
        return redissonClient.getAtomicLong(key).addAndGet(delta);
    }


    public long decr(String key) {
        return redissonClient.getAtomicLong(key).decrementAndGet();
    }


    public long decrBy(String key, long delta) {
        return redissonClient.getAtomicLong(key).addAndGet(-delta);
    }


    public void remove(String key) {
        redissonClient.getBucket(key).delete();
    }


    public boolean isExists(String key) {
        return redissonClient.getBucket(key).isExists();
    }

    public void addToSet(String key, String value) {
        RSet<String> set = redissonClient.getSet(key);
        set.add(value);
    }

    public boolean isSetMember(String key, String value) {
        RSet<String> set = redissonClient.getSet(key);
        return set.contains(value);
    }

    public void addToList(String key, String value) {
        RList<String> list = redissonClient.getList(key);
        list.add(value);
    }

    public String getFromList(String key, int index) {
        RList<String> list = redissonClient.getList(key);
        return list.get(index);
    }

    public void addToMap(String key, String field, String value) {
        RMap<String, String> map = redissonClient.getMap(key);
        map.put(field, value);
    }

    public String getFromMap(String key, String field) {
        RMap<String, String> map = redissonClient.getMap(key);
        return map.get(field);
    }

    public void addToSortedSet(String key, String value) {
        RSortedSet<String> sortedSet = redissonClient.getSortedSet(key);
        sortedSet.add(value);
    }


    public RLock getLock(String key) {
        return redissonClient.getLock(key);
    }


    public RLock getFairLock(String key) {
        return redissonClient.getFairLock(key);
    }


    public RReadWriteLock getReadWriteLock(String key) {
        return redissonClient.getReadWriteLock(key);
    }


    public RSemaphore getSemaphore(String key) {
        return redissonClient.getSemaphore(key);
    }


    public RPermitExpirableSemaphore getPermitExpireAbleSemaphore(String key) {
        return redissonClient.getPermitExpirableSemaphore(key);
    }


    public RCountDownLatch getCountDownLatch(String key) {
        return redissonClient.getCountDownLatch(key);
    }


    public <T> RBloomFilter<T> getBloomFilter(String key) {
        return redissonClient.getBloomFilter(key);
    }


    public <T> T getValueObj(String key) {
        RBucket<T> rBucket = redissonClient.getBucket(key);
        return rBucket.get();
    }
}
