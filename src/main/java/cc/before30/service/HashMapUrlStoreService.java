package cc.before30.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by before30 on 03/07/2017.
 */
@Service
public class HashMapUrlStoreService implements UrlStoreService {

    private Map<String, String> urlByIdMap = new ConcurrentHashMap<>();

    @Override
    public String findById(String id) {
        return urlByIdMap.get(id);
    }

    @Override
    public void save(String id, String url) {
        urlByIdMap.put(id, url);
    }

    @Override
    public Map<String, String> findAll() {
        return urlByIdMap;
    }


}
