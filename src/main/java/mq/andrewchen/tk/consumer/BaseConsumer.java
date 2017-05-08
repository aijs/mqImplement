package mq.andrewchen.tk.consumer;

/** 基类消费者 包含必须的
 * consumerid 消费者id
 * tagName 二级编号
 * topic 主题编号
 * Created by Andrew on 2017-5-6.
 */
public abstract class BaseConsumer {
    private String consumerId;
    private String tagName;
    private String topicName;

    public BaseConsumer(String topicName, String tagName, String consumerId){
        this.tagName = tagName;
        this.topicName = topicName;
        this.consumerId = consumerId;
    }
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public abstract void conume(byte[] bytes)throws Exception;
}
