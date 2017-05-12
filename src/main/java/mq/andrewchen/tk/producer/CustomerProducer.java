package mq.andrewchen.tk.producer;

import mq.andrewchen.tk.config.andrewchen1.ProducerName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aijinsong on 2017/5/12.
 */
@Configuration
public class CustomerProducer {

    private static Logger logger = LoggerFactory.getLogger(CustomerProducer.class);

    @Bean(name = "customerProducer", initMethod = "start", destroyMethod = "shutdown")
    public ProducerExecutor getBean() {
        ProducerFactoryBean producerFactoryBean = new ProducerFactoryBean();
        producerFactoryBean.setProducerId(ProducerName.PID_CUSTOMER.name());
        return producerFactoryBean.getObject();
    }

}
