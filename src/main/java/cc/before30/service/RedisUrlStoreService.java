package cc.before30.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by before30 on 03/07/2017.
 */
public class RedisUrlStoreService implements UrlStoreService {
    @Autowired
    private StringRedisTemplate template;

    static final String prefix = "cc.before30.redis.";


    @Override
    public String findById(String id) {
        ValueOperations<String, String> operations = template.opsForValue();
        return operations.get(prefix + id);
    }

    @Override
    public void save(String id, String url) {
        ValueOperations<String, String> operations = template.opsForValue();
        operations.set(prefix + id, url);
    }

    @Override
    public Map<String, String> findAll() {
        Set<String> keys = template.keys(prefix+"*");
        HashMap<String, String> map = new HashMap();
        ValueOperations<String, String> operations = template.opsForValue();

        for (String key : keys) {
            String url = operations.get(key);
            map.put(key, url);
        }

        return map;
    }
}
