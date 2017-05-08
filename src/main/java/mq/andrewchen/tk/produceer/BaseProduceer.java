package mq.andrewchen.tk.produceer;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.exception.MQClientException;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * Created by Andrew on 2017-5-6.
 */
public interface BaseProduceer {
    void sendMessage(String topicName, String tagName, String key, byte[] bytes);
}
