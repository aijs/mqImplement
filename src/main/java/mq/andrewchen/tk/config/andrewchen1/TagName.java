package mq.andrewchen.tk.config.andrewchen1;

/**
 * Created by Andrew on 2017-5-8.
 */
public enum  TagName {
    TEST("TEST");

    private final String tagName;

    private TagName(String tagName){
        this.tagName = tagName;
    }

    @Override
    public String toString(){
        return tagName;
    }
}
