package mq.andrewchen.tk.produceer.aliyun;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import mq.andrewchen.tk.produceer.BaseProduceer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by Andrew on 2017-5-6.
 */
@Component
@Profile({"TEST","PRE","PROD"})
public class AliyunProduceer implements BaseProduceer, ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(AliyunProduceer.class);
    private ApplicationContext applicationContext;
    @Override
    public void sendMessage(String topicName, String tagName, String key, byte[] bytes) {
        logger.info("find bean from spring context");
        Producer producer = (Producer) applicationContext.getBean(topicName);
        logger.info("create message the topic name is {}, the tag name is {}" +
                "the key is {} and the bytes is {}", topicName, tagName, key, bytes);
        Message message = new Message(
                topicName,
                tagName,
                key,
                bytes
        );

        SendResult result = producer.send(message);
        logger.info("send the message succed the id is {}", result.getMessageId());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
