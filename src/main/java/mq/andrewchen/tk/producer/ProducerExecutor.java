package mq.andrewchen.tk.producer;

/**
 * Created by Andrew on 2017-5-6.
 */
public interface ProducerExecutor {

    void sendMessage(String topicName, String tagName, String key, byte[] bytes);

    /**
     * 启动服务
     */
    void start();


    /**
     * 关闭服务
     */
    void shutdown();

}
