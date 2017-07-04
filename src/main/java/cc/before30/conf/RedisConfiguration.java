package cc.before30.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Protocol;
import redis.embedded.RedisServer;

/**
 * Created by before30 on 03/07/2017.
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisServer redisServer() {
        RedisServer.builder().reset();
        return RedisServer.builder().port(Protocol.DEFAULT_PORT).build();
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

}
