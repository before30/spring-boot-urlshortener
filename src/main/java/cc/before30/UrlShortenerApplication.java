package cc.before30;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static java.util.stream.Collectors.joining;

/**
 * Created by before30 on 03/07/2017.
 */
@SpringBootApplication
@Slf4j
public class UrlShortenerApplication {

    @Autowired
    private RedisServer redisServer;

    @PostConstruct
    public void start() {
        log.info("starting redis...");
        if (!redisServer.isActive()) {
            redisServer.start();
        }
        log.info("redis listen ports: {}", redisServer.ports().stream()
                .map(Object::toString).collect(joining(",")));

    }

    @PreDestroy
    public void stop() {
        log.info("shutting down redis...");
        redisServer.stop();
    }

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }
}
