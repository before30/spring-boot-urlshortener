package cc.before30.controller;

import cc.before30.service.LettuceUrlStoreService;
import cc.before30.service.UrlStoreService;
import io.lettuce.core.RedisFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import reactor.core.publisher.Mono;

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

    @Autowired
    private LettuceUrlStoreService lettuceUrlStoreService;

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

    @GetMapping("/v2/{id}")
    public DeferredResult<Void> redirectToUrl2(@PathVariable("id") String id, HttpServletResponse resp) throws Exception {

        DeferredResult<Void> deferredResult = new DeferredResult<>();
        RedisFuture<String> result = lettuceUrlStoreService.findOne(id);
        result.thenAcceptAsync(url ->{
            if (Objects.nonNull(url)) {
//                log.info("id : {}, url : {}", id, url);
                resp.addHeader(HttpHeaders.LOCATION, url);
                resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                deferredResult.setResult(null);
            } else {

//                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        });

        return deferredResult;
    }

    @GetMapping("/v3/{id}")
    public Mono<String> redirectToUrl3(@PathVariable("id") String id,
                                       HttpServletResponse resp)  throws Exception {
        Mono<String> result = lettuceUrlStoreService.findOne2(id);
//        log.info("result from redis {}", result.block());

//        result.doOnNext()
//        return result
//                .log()
//                .then(url -> {
//            if (Objects.nonNull(url)) {
//                log.info("id : {} url {}", id, url);
//                resp.addHeader(HttpHeaders.LOCATION, url);
//                resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
//            } else {
////                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//            }
//            return Mono.just("test");
//        }).doOnNext(c -> log.info(c));
        return Mono.just("hello world");

    }

}
