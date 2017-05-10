package mq.andrewchen.tk.produceer.rocket;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import mq.andrewchen.tk.config.andrewchen1.ConsumerName;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * http://www.tuicool.com/articles/mEBVneB
 * Created by Administrator on 2017/5/8.
 */
@Configuration
@Profile("DEV")
@ConfigurationProperties("aliyun.rocketmq")
public class ProduccerConfig {
    private String producerGroupName;
    private String ipAddress ;
    private String producerName;

    @Bean(name = "andrewchen_1", initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQProducer mqProducer(){
        DefaultMQProducer producer = new DefaultMQProducer(producerGroupName);
        producer.setNamesrvAddr(ipAddress);
        producer.setInstanceName(ConsumerName.CID_andrewchen1.toString());
        return producer;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getProducerGroupName() {
        return producerGroupName;
    }

    public void setProducerGroupName(String producerGroupName) {
        this.producerGroupName = producerGroupName;
    }
}
