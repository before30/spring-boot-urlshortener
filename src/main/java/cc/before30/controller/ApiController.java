package cc.before30.controller;

import cc.before30.service.UrlStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * Created by before30 on 03/07/2017.
 */
@RestController
@Slf4j
public class ApiController {
    @Autowired
    private UrlStoreService urlStoreService;

    @GetMapping("/list")
    public Map<String, String> list() {
        return urlStoreService.findAll();
    }

    @GetMapping("/{id}")
    public void redirectToUrl(@PathVariable("id") String id, HttpServletResponse resp) throws Exception {
        final String url = urlStoreService.findById(id);
        log.info("id : {}, url : {}", id, url);
        if (Objects.nonNull(url)) {
            resp.addHeader(HttpHeaders.LOCATION, url);
            resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
