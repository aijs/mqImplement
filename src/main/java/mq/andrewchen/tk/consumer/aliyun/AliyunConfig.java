package mq.andrewchen.tk.consumer.aliyun;

import com.aliyun.openservices.ons.api.*;
import mq.andrewchen.tk.consumer.BaseConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

/**
 * Created by Andrew on 2017-5-6.
 */
@Configuration
@Profile({"TEST", "PRE", "PROD"})
@ConfigurationProperties(prefix = "aliyun.ons")
public class AliyunConfig implements ApplicationContextAware{
    private Logger logger = LoggerFactory.getLogger(AliyunConfig.class);

    private String accessKeyId;
    private String accessKeySecret;

    @Autowired
    @Qualifier("printMessageListenerIml")
    private BaseConsumer printMessageListenerIml;

    private ApplicationContext applicationContext;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    @Bean(name = "printConsumer", initMethod = "start", destroyMethod = "shutdown")
    public Consumer getPrintConsumer(){
        return creatConsumer(printMessageListenerIml);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private Consumer creatConsumer(final BaseConsumer baseConsumer){
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.ConsumerId, baseConsumer.getConsumerId());
        properties.setProperty(PropertyKeyConst.AccessKey,accessKeyId);
        properties.setProperty(PropertyKeyConst.SecretKey, accessKeySecret);

        Consumer consumer = ONSFactory.createConsumer(properties);
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
