package cc.before30.service;

import java.util.Map;

/**
 * Created by before30 on 03/07/2017.
 */
public interface UrlStoreService {

    String findById(String id);

    void save(String id, String url);

    Map<String, String> findAll();
}
