package mq.andrewchen.tk.controller;

import mq.andrewchen.tk.produceer.BaseProduceer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Andrew on 2017-5-7.
 */
@RestController
public class TestController {
    @Autowired
    private BaseProduceer baseProduceer;

    @RequestMapping(value = "/")
    public String test(){
        baseProduceer.sendMessage("andrewchen_1", "print", "print", "hello world".getBytes());
        return "1";
    }
}
