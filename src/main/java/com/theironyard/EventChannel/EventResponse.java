package com.theironyard;

/**
 * Created by stephenwilliamson on 2/14/17.
 */
public class EventResponse {

    private String content;

    public EventResponse(){

    }

    public EventResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
