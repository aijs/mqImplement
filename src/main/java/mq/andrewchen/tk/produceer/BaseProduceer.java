package mq.andrewchen.tk.produceer;

/**
 * Created by Andrew on 2017-5-6.
 */
public interface BaseProduceer {
    void sendMessage(String topicName, String tagName, String key, byte[] bytes);
}
