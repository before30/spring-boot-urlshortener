package cc.before30.service;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * User: before30 
 * Date: 2017. 7. 4.
 * Time: PM 5:55
 */
@Service
@Slf4j
public class LettuceUrlStoreService {

	@Autowired
	private RedisClient client;

	static final String prefix = "cc.before30.redis.";

	public RedisFuture<String> findOne(String id) {
		StatefulRedisConnection<String, String> connect = client.connect();
		return connect.async().get(prefix + id);
	}

	public Mono<String> findOne2(String id) {
		StatefulRedisConnection<String, String> connect = client.connect();
		return connect.reactive().get(prefix + id);
	}

}
