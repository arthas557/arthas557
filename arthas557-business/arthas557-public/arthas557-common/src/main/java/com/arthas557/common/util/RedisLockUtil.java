/*
package com.arthas557.common.util;

import com.arthas557.common.constants.Symbol;
import com.arthas557.common.exception.BusinessException;
import com.xiaoleilu.hutool.http.HttpUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

*/
/**
  * <a href='https://blog.csdn.net/qq_28397259/article/details/80839072'>简单的redis锁工具类<a/>
  * <a href='https://www.imooc.com/article/34098'><a/>
  * @author wangty
  *//*

@Slf4j
//@Component
public class RedisLockUtil {

    private static RedisTemplate<String,Object> redisTemplate;
    private static final String UNLOCK_LUA;
    private static String SET_IF_NOT_EXIST = "NX";
    private static String SET_WITH_EXPIRE_TIME = "PX";
    public static Long DEFAULT_EXPIRE_TIME = 180000L;
    static {
        UNLOCK_LUA = "if redis.call(\"get\",KEYS[1]) == ARGV[1] " +
                "then " +
                "    return redis.call(\"del\",KEYS[1]) " +
                "else " +
                "    return 0 " +
                "end ";
    }

    */
/**
     * 获取redis的key<br/>
     * @param qualifiedMethodName 需要进行分布式锁控制的方法全限定名
     * @param requestIp 请求ip
     * @param groupByIp key是否加上Ip(存在多个中间层系统)
     * @return redis key
     *//*

    public static String getRedisKey(String qualifiedMethodName,String requestIp,boolean groupByIp){
        if(StrUtil.isBlank(qualifiedMethodName)){
            throw new BusinessException("当前请求资源名为空");
        }
        if(!groupByIp){
            return qualifiedMethodName;
        }
        if(StrUtil.isBlank(requestIp)){
            return qualifiedMethodName;
        }
        return requestIp.concat(Symbol.COLON).concat(qualifiedMethodName);
    }

    */
/**
     * 获取redis的key<br/>
     * @param qualifiedMethodName 需要进行分布式锁控制的方法全限定名
     * @param httpServletRequest 请求
     * @param groupByIp key是否加上Ip(存在多个中间层系统)
     * @see Jedis#set(java.lang.String, java.lang.String, java.lang.String, java.lang.String, long)
     * @return redis key
     *//*

    public static String getRedisKey(String qualifiedMethodName, HttpServletRequest httpServletRequest, boolean groupByIp){
        if(StrUtil.isNotBlank(qualifiedMethodName)){
            throw new BusinessException("当前请求资源名为空");
        }
        if(!groupByIp){
            return qualifiedMethodName;
        }
        if(null == httpServletRequest){
            return qualifiedMethodName;
        }

        return HttpUtil.getClientIP(httpServletRequest).concat(Symbol.COLON).concat(qualifiedMethodName);
    }

    */
/**
     * 获取锁
     * @param key key
     * @param uniqueId value,需全局唯一.
     * @return 获取锁是否成功
     *//*

    public static boolean lock(String key,String uniqueId){
        try {
            RedisCallback<String> callback = (connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.set(key, uniqueId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, DEFAULT_EXPIRE_TIME);
            };
            String result = redisTemplate.execute(callback);
            boolean isSuccess = StrUtil.isNotBlank(result);
            if(isSuccess){
                log.info("获取redis锁成功,key:{},uniqueId:{}",key,uniqueId);
            }else {
                log.info("获取redis锁失败,key:{},uniqueId:{}",key,uniqueId);
            }
            return isSuccess;
        } catch (Exception e) {
            log.error(String.format("获取redis锁异常,key:%s,uniqueId:%s",key,uniqueId), e);
        }
        return false;

    }

    */
/**
     * 解锁
     *@param key key
     * @param uniqueId value,需全局唯一.
     *//*

    public static boolean unLock(String key,String uniqueId){
        // 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
        try {
            List<String> keys = new ArrayList<>();
            keys.add(key);
            List<String> args = new ArrayList<>();
            args.add(uniqueId);

            // 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
            // spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
            RedisCallback<Long> callback = (connection) -> {
                Object nativeConnection = connection.getNativeConnection();
                // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                // 集群模式
                if (nativeConnection instanceof JedisCluster) {
                    return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }

                // 单机模式
                else if (nativeConnection instanceof Jedis) {
                    return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                return 0L;
            };
            Long result = redisTemplate.execute(callback);

            return result != null && result > 0;
        } catch (Exception e) {
            log.error(String.format("释放redis锁异常,key:%s,uniqueId:%s",key,uniqueId), e);
        }
        return false;
    }


    @Autowired
    public  void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisLockUtil.redisTemplate = redisTemplate;
    }
}
*/
