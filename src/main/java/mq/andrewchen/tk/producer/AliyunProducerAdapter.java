package mq.andrewchen.tk.producer;

import com.aliyun.openservices.ons.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by aijinsong on 2017/5/12.
 */
public class AliyunProducerAdapter implements ProducerExecutor {

    private static Logger logger = LoggerFactory.getLogger(AliyunProducerAdapter.class);

    private Producer producer;

    public AliyunProducerAdapter(String accessKeyId, String accessKeySecret, String producerId) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.ProducerId, producerId);
        properties.setProperty(PropertyKeyConst.AccessKey, accessKeyId);
        properties.setProperty(PropertyKeyConst.SecretKey, accessKeySecret);
        producer = ONSFactory.createProducer(properties);
    }

    @Override
    public void sendMessage(String topicName, String tagName, String key, byte[] bytes) {
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
    public void start() {
        producer.start();
    }

    @Override
    public void shutdown() {
        producer.shutdown();
    }
}
