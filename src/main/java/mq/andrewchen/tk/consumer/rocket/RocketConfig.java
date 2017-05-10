package mq.andrewchen.tk.consumer.rocket;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.exception.MQClientException;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.common.message.MessageExt;
import mq.andrewchen.tk.consumer.BaseConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

/**
 * Created by Andrew on 2017-5-8.
 */
@Configuration
@Profile("DEV")
@ConfigurationProperties(prefix = "aliyun.rocketmq")
public class RocketConfig {
    private static Logger logger = LoggerFactory.getLogger(RocketConfig.class);
    @Autowired
    @Qualifier("printMessageListenerIml")
    private BaseConsumer printMessageListenerIml;

    private String ipAddress;
    private String consumerGroupName;


    @Bean(name="printConsumer", initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQPushConsumer getPrintConsumer(final BaseConsumer printMessageListenerIml) throws MQClientException {
        return createConsumer(printMessageListenerIml);
    }

    private DefaultMQPushConsumer createConsumer(final BaseConsumer baseConsumer)throws MQClientException{
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(consumerGroupName);
        defaultMQPushConsumer.setNamesrvAddr(ipAddress);
        defaultMQPushConsumer.setInstanceName(printMessageListenerIml.getConsumerId());
        defaultMQPushConsumer.subscribe(printMessageListenerIml.getTopicName(),
                printMessageListenerIml.getTagName());
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
                                                            ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for(MessageExt messageExt: list){
                    try {
                        baseConsumer.conume(messageExt.getBody());
                    }catch (Exception e){
                        logger.error("fetal error occurred, {}", e.getMessage());
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        return defaultMQPushConsumer;
    }
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getConsumerGroupName() {
        return consumerGroupName;
    }

    public void setConsumerGroupName(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName;
    }
}
