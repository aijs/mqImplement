package mq.andrewchen.tk.consumer.process.print;


import mq.andrewchen.tk.consumer.BaseConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by Andrew on 2017-5-6.
 */
@Component
@Qualifier("print")
public class PrintMessage extends BaseConsumer {
    Logger logger = LoggerFactory.getLogger(PrintMessage.class);
    private static String TOPICNAME = "andrewchen_1";
    private static String TAGNAME = "print";
    private static String CONSUMERID = "CID_andrewchen1";

    public PrintMessage(){
        super(TOPICNAME, TAGNAME, CONSUMERID);
    }
    @Override
    public void conume(byte[] bytes) {
        String content = new String(bytes);
        logger.info(content);
    }
}
