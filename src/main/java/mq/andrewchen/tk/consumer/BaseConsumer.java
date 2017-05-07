package mq.andrewchen.tk.consumer;

/**
 * Created by Andrew on 2017-5-6.
 */
public abstract class BaseConsumer {
    private String tagName;
    private String topicName;

    public BaseConsumer(String topicName, String tagName){
        this.tagName = tagName;
        this.topicName = topicName;
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

    public abstract void conume(byte[] bytes);
}
