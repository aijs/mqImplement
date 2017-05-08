package mq.andrewchen.tk.config.andrewchen1;

/**
 * Created by Andrew on 2017-5-8.
 */
public enum  ConsumerName {
    CID_andrewchen1("CID_andrewchen1");

    private final String consumerName;

    ConsumerName(String consumerName){
        this.consumerName = consumerName;
    }

    @Override
    public String toString(){
        return consumerName;
    }
}
