package mq.andrewchen.tk.controller;

import mq.andrewchen.tk.config.andrewchen1.TagName;
import mq.andrewchen.tk.config.andrewchen1.TopicName;
import mq.andrewchen.tk.producer.ProducerExecutor;
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
    private ProducerExecutor producerExecutor;

    @RequestMapping(value = "/")
    public String test(){
        producerExecutor.sendMessage(TopicName.ANDREWCHEN_1.toString(),
                TagName.TEST.toString(),
                TagName.TEST.toString(),
                "hello world".getBytes());
        return "1";
    }
}
