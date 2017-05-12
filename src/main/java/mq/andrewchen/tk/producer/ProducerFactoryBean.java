package mq.andrewchen.tk.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by aijinsong on 2017/5/12.
 */
public class ProducerFactoryBean implements FactoryBean<ProducerExecutor> {

    private static Logger logger = LoggerFactory.getLogger(ProducerFactoryBean.class);

    @Value("${mqToolsType}")
    private String mqToolsType;

    @Value("${accessKeyId}")
    private String accessKeyId;

    @Value("${accessKeySecret}")
    private String accessKeySecret;

    @Value("${producerGroupName}")
    private String producerGroupName;

    @Value("${ipAddress}")
    private String ipAddress;

    private String producerId;

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    @Override
    public ProducerExecutor getObject() {
        if (MQToolsType.ALIYUN.name().equals(mqToolsType)) {
            return new AliyunProducerAdapter(accessKeyId, accessKeySecret, producerId);
        }
        if (MQToolsType.MQROCKET.name().equals(mqToolsType)) {
            return new RocketMQProducerAdapter(producerGroupName, ipAddress, producerId);
        }
        return null;
    }

    @Override
    public Class<ProducerExecutor> getObjectType() {
        return ProducerExecutor.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
