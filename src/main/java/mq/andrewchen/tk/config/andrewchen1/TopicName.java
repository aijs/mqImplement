package mq.andrewchen.tk.config.andrewchen1;

/**
 * Created by Andrew on 2017-5-6.
 */
public enum TopicName {
    ANDREWCHEN_1("andrewchen_1");

    private final String topicName;

    TopicName(String topicName){
        this.topicName = topicName;
    }
    @Override
    public String toString(){
        return topicName;
    }
}
