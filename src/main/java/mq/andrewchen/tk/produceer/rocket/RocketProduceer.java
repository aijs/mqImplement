package mq.andrewchen.tk.produceer.rocket;

import mq.andrewchen.tk.produceer.BaseProduceer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by Andrew on 2017-5-6.
 */
@Component
@Profile("DEV")
public class RocketProduceer implements BaseProduceer, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void sendMessage(String topicName, String tagName, String key, byte[] bytes) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
