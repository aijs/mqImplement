package mq.andrewchen.tk.produceer.aliyun;

import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import mq.andrewchen.tk.config.andrewchen1.TopicName;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Properties;

/**
 * Created by Andrew on 2017-5-6.
 */
@Configuration
@Profile({"TEST", "PRE", "PROD" })
@ConfigurationProperties(prefix = "aliyun.ons")
class ProduceerConfig {
    private String accessKeyId;
    private String accessKeySecret;

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

    /**
     *
     * @return
     */
    @Bean(name ="andrewchen_1", initMethod = "start", destroyMethod = "shutdown")
    @Qualifier("andrewchen_1")
    public Producer getProduccer(){
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.ProducerId, TopicName.ANDREWCHEN_1.toString());
        properties.setProperty(PropertyKeyConst.AccessKey, accessKeyId);
        properties.setProperty(PropertyKeyConst.SecretKey, accessKeySecret);
        return  ONSFactory.createProducer(properties);
    }
}
