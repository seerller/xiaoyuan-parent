package com.xiaoyuan.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis缓存配置类
 * @author szekinwin
 *  TODO:目前暂无使用redis的操作。仅配置redis
 */
@Configuration
@EnableAutoConfiguration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate (RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redis = new RedisTemplate();
        Jackson2JsonRedisSerializer<Object> j = new Jackson2JsonRedisSerializer<Object>(Object.class);
        //value值的序列化采用Jackson2JsonRedisSerializer
        redis.setValueSerializer(j);
        redis.setHashValueSerializer(j);
        //key值则使用StringRedisSerializer进行序列化.
        redis.setKeySerializer(new StringRedisSerializer());
        redis.setHashKeySerializer(new StringRedisSerializer());
        //设置redis工厂。
        redis.setConnectionFactory(redisConnectionFactory);
        return redis;
    }
}
