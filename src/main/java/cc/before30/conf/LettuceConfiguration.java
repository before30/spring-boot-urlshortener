package cc.before30.conf;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: before30 
 * Date: 2017. 7. 4.
 * Time: PM 5:51
 */
@Configuration
public class LettuceConfiguration {
	@Bean(destroyMethod = "shutdown")
	ClientResources clientResources() {
		return DefaultClientResources.create();
	}

	@Bean(destroyMethod = "shutdown")
	RedisClient redisClient(ClientResources clientResources) {
		return RedisClient.create(clientResources, RedisURI.create("127.0.0.1", 6379));
	}

	@Bean(destroyMethod = "close")
	StatefulRedisConnection<String, String> connection(RedisClient redisClient) {
		return redisClient.connect();
	}
}
