package cc.before30.conf;

import cc.before30.service.RedisUrlStoreService;
import cc.before30.service.UrlStoreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by before30 on 04/07/2017.
 */
@Configuration
public class UrlStoreConfiguration {
    @Bean
    UrlStoreService urlStoreService() {
        return new RedisUrlStoreService();
    }
}
