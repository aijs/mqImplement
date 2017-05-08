package mq.andrewchen.tk.config.andrewchen1;

/**
 * Created by Andrew on 2017-5-8.
 */
public enum ProducerName {
    PID_andrewchen1("PID_andrewchen1");

    private final String producerName;

     ProducerName(String producerName){
        this.producerName = producerName;
    }

    @Override
    public String toString(){
        return producerName;
    }
}
