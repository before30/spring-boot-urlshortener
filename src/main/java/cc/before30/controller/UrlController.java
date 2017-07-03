package cc.before30.controller;

import cc.before30.controller.dto.ShortenUrlRequest;
import cc.before30.service.UrlStoreService;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Created by before30 on 03/07/2017.
 */
@Controller
public class UrlController {

    @Autowired
    private UrlStoreService urlStoreService;

    @GetMapping("/")
    public String showFrom(ShortenUrlRequest request) {
        return "shortener";
    }

    @PostMapping("/")
    public ModelAndView shortenUrl(HttpServletRequest httpReq,
                                   @Valid ShortenUrlRequest req,
                                   BindingResult bindingResult) {
        String url = req.getUrl();

        ModelAndView modelAndView = new ModelAndView("shortener");
        if (!bindingResult.hasErrors()) {
            final String id = Hashing.murmur3_32()
                    .hashString(url, StandardCharsets.UTF_8).toString();
            urlStoreService.save(id, url);
            String requestUrl = httpReq.getRequestURL().toString();
            String prefix = requestUrl.substring(0, requestUrl.indexOf(httpReq.getRequestURI(),
                    "http://".length()));

            modelAndView.addObject("shortenedUrl", prefix + "/" + id);
        }

        return modelAndView;
    }

}
