package mq.andrewchen.tk.produceer.rocket;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.exception.MQClientException;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.producer.SendResult;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.common.message.Message;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.remoting.exception.RemotingException;
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
@Profile("DEV")
public class RocketProduceer implements BaseProduceer, ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(RocketProduceer.class);
    private ApplicationContext applicationContext;

    @Override
    public void sendMessage(String topicName, String tagName, String key, byte[] bytes){
        logger.info("find bean from spring context");
        DefaultMQProducer producer = (DefaultMQProducer) applicationContext.getBean(topicName);
        logger.info("create message the topic name is {}, the tag name is {}" +
                "the key is {} and the bytes is {}", topicName, tagName, key, bytes);
        Message message = new Message(
                topicName,
                tagName,
                key,
                bytes
        );
        try{
            SendResult result = producer.send(message);
            logger.info("send the message succed the id is {}", result.getMsgId());
        }catch (Exception e){
            logger.error("error occurred while trying to sending the message , the topic name" +
                    "is {}, the tag name is {}, the key is {}, the bytes is {}",
                    topicName, tagName, key, bytes);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
