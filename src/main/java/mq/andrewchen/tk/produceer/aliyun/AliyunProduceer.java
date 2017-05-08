package mq.andrewchen.tk.produceer.aliyun;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import mq.andrewchen.tk.produceer.BaseProduceer;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
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
    private Logger logger = org.slf4j.LoggerFactory.getLogger(AliyunProduceer.class);
    private ApplicationContext applicationContext;
    @Override
    public void sendMessage(String topicName, String tagName, String key, byte[] bytes) {
        Producer producer = (Producer) applicationContext.getBean(topicName);
        Message message = new Message(
                topicName,
                tagName,
                key,
                bytes
        );
        producer.send(message);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
