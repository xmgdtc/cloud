package org.xmgdtc.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.xmgdtc.redis.dto.NonceDTO;
import org.xmgdtc.redis.dto.RefreshTokenDTO;
import org.xmgdtc.redis.dto.TokenInfoDTO;

import java.time.Duration;
import java.util.Set;

@Configuration
@EnableCaching
public class RedisConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private Integer port;

    @Value("${redis.username}")
    private String username;

    @Value("${redis.password}")
    private String passwd;

    @Value("${redis.database}")
    private Integer database;

    @Value("${redis.pool.max-active}")
    private Integer poolMaxActive;

    @Value("${redis.pool.max-wait}")
    private Long poolMaxWait;

    @Value("${redis.pool.max-idle}")
    private Integer poolMaxIdle;

    @Value("${redis.pool.min-idle}")
    private Integer poolMinIdle;

    /**
     * 缓存的库
     *
     * @return
     */
    @Bean
    public CacheManager cacheManager() {

        RedisCacheConfiguration config = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        Set<String> cacheNames = Set.of("common-cache");


        return RedisCacheManager.builder(this.getConnectionFactory(database))
                .cacheDefaults(config)
                .initialCacheNames(cacheNames)
                .build();

    }


    /**
     * 登录存nonce的
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, NonceDTO> nonceRedisTemplate() {
        RedisTemplate<String, NonceDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(this.getConnectionFactory(0));
        this.setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }


    /**
     * 存token的
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, TokenInfoDTO> tokenRedisTemplate() {
        RedisTemplate<String, TokenInfoDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(this.getConnectionFactory(1));
        this.setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 存refresh token的
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, RefreshTokenDTO> refreshTokenRedisTemplate() {
        RedisTemplate<String, RefreshTokenDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(this.getConnectionFactory(2));
        this.setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }


    /**
     * redis连接工厂
     *
     * @param database
     * @return
     */
    private LettuceConnectionFactory getConnectionFactory(Integer database) {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(
                this.getRedisStandaloneConfiguration(database),
                this.getClientConfiguration()
        );
        factory.afterPropertiesSet();
        return factory;
    }

    /**
     * redis配置
     *
     * @param database
     * @return
     */
    private RedisStandaloneConfiguration getRedisStandaloneConfiguration(Integer database) {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setUsername(username);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(passwd));

        return redisStandaloneConfiguration;

    }

    /**
     * 连接池配置
     *
     * @return
     */
    private LettuceClientConfiguration getClientConfiguration() {

        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(poolMaxIdle);
        genericObjectPoolConfig.setMinIdle(poolMinIdle);
        genericObjectPoolConfig.setMaxTotal(poolMaxActive);
        genericObjectPoolConfig.setMaxWait(Duration.ofMillis(poolMaxWait));

        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(300))
                .shutdownTimeout(Duration.ofMillis(300))
                .poolConfig(genericObjectPoolConfig)
                .build();
        return clientConfig;

    }


    private void setSerializer(RedisTemplate redisTemplate) {

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

    }


}
