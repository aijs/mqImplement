package mq.andrewchen.tk.consumer.messagelistener.print;


import mq.andrewchen.tk.config.andrewchen1.ConsumerName;
import mq.andrewchen.tk.config.andrewchen1.TagName;
import mq.andrewchen.tk.config.andrewchen1.TopicName;
import mq.andrewchen.tk.consumer.BaseConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by Andrew on 2017-5-6.
 */
@Component
@Qualifier("printMessageListenerIml")
public class PrintMessageListenerImpl extends BaseConsumer {
    Logger logger = LoggerFactory.getLogger(PrintMessageListenerImpl.class);
    private static String TOPICNAME = TopicName.ANDREWCHEN_1.toString();
    private static String TAGNAME = TagName.TEST.toString();
    private static String CONSUMERID = ConsumerName.CID_andrewchen1.toString();

    public PrintMessageListenerImpl(){
        super(TOPICNAME, TAGNAME, CONSUMERID);
    }
    @Override
    public void conume(byte[] bytes) {
        String content = new String(bytes);
        logger.info(content);
    }
}
