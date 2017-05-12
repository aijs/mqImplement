package mq.andrewchen.tk.producer;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.producer.SendResult;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aijinsong on 2017/5/12.
 */
public class RocketMQProducerAdapter implements ProducerExecutor {

    private static Logger logger = LoggerFactory.getLogger(RocketMQProducerAdapter.class);

    private DefaultMQProducer producer;

    public RocketMQProducerAdapter(String producerGroupName, String ipAddress, String producerId) {
        producer = new DefaultMQProducer(producerGroupName);
        producer.setNamesrvAddr(ipAddress);
        producer.setInstanceName(producerId);
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
        try {
            SendResult result = producer.send(message);
            logger.info("send the message succed the id is {}", result.getMsgId());
        } catch (Exception e) {
            logger.error("error occurred while trying to sending the message , the topic name" +
                            "is {}, the tag name is {}, the key is {}, the bytes is {}",
                    topicName, tagName, key, bytes);
        }
    }

    @Override
    public void start() {
    }

    @Override
    public void shutdown() {
    }
}
