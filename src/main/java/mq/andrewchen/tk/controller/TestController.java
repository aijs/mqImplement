package mq.andrewchen.tk.controller;

import mq.andrewchen.tk.produceer.BaseProduceer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Andrew on 2017-5-7.
 */
@RestController
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private BaseProduceer baseProduceer;

    @RequestMapping(value = "/")
    public String test(){
        baseProduceer.sendMessage("andrewchen_1", "print", "print", "hello world".getBytes());
        return "1";
    }
}
