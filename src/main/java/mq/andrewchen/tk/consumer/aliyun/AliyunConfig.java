package mq.andrewchen.tk.consumer.aliyun;

import com.aliyun.openservices.ons.api.*;
import mq.andrewchen.tk.consumer.BaseConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

/**
 * Created by Andrew on 2017-5-6.
 */
//@Configuration
@Profile("test")
@ConfigurationProperties(prefix = "aliyun",ignoreUnknownFields =true)
public class AliyunConfig implements ApplicationContextAware{
    private Logger logger = LoggerFactory.getLogger(AliyunConfig.class);

    private String accessKeyId;
    private String accessKeySecret;

    private ApplicationContext applicationContext;

    @Bean(name = "CID_andrewchen1", initMethod = "start", destroyMethod = "destroy")
    public Consumer getConsumerAnddrewChen1(){
        return creatConsumer("CID_andrewchen1");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private Consumer creatConsumer(String consumerName){
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.ConsumerId, consumerName);
        properties.setProperty(PropertyKeyConst.AccessKey,accessKeyId);
        properties.setProperty(PropertyKeyConst.SecretKey, accessKeySecret);

        Consumer consumer = ONSFactory.createConsumer(properties);
        final BaseConsumer baseConsumer = (BaseConsumer) applicationContext.getBean("CID_andrewchen1");
        String topicName = baseConsumer.getTopicName();
        String tagName = baseConsumer.getTagName();
        consumer.subscribe(topicName,
                tagName,
                new MessageListener() {
                    @Override
                    public Action consume(Message message, ConsumeContext consumeContext) {
                        logger.info("start consume message from aliyun," +
                                "the id is {} the key is {}", message.getMsgID(), message.getKey());
                        try{
                            baseConsumer.conume(message.getBody());
                            logger.info("end consume message from aliyun," +
                                    "the id is {} the key is {}", message.getMsgID(), message.getKey());
                            return Action.CommitMessage;
                        }catch (Exception e){
                            logger.error("there is fetal error {},the consume will be tried again," +
                                            " end consumer message from aliyun the id is {} the key is {}",
                                            e.getMessage(),
                                            message.getMsgID(),
                                            message.getKey());

                            return Action.ReconsumeLater;
                        }
                    }
                });
        return consumer;
    }
}
