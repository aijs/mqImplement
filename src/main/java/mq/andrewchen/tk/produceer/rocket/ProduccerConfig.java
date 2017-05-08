package mq.andrewchen.tk.produceer.rocket;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.producer.DefaultMQProducer;
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
@ConfigurationProperties("alibaba.rocketmq")
public class ProduccerConfig {
    private String ipAddress ;
    private String producerName;

    @Bean(name = "andrewchen_1", initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQProducer mqProducer(){
        DefaultMQProducer producer = new DefaultMQProducer(producerName);
        producer.setNamesrvAddr(ipAddress);
        producer.setInstanceName(producerName);
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
}
