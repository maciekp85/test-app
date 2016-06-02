package pl.wimiip.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nishi on 2016-05-27.
 */
@RestController
public class HomeController {

    @RequestMapping("/greeting2")
    public Map<String, Object> welcome() {
        Map<String, Object> model = new HashMap<>();
        model.put("greeting", "Welcome to online store");
        model.put("tagline", "special and unique online store");
        return model;
    }
}
