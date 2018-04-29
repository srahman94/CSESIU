package com.example.sujon.csesiu.model;

/**
 * Created by SuJoN on 4/20/2018.
 */

public class EventModel {
    private String eventTitle, eventDesc, eventImgUrl;
    private int date;

    public EventModel() {

    }

    public EventModel(String eventTitle, String eventDesc, String eventImgUrl, int date) {
        this.eventTitle = eventTitle;
        this.eventDesc = eventDesc;
        this.eventImgUrl = eventImgUrl;
        this.date = date;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventImgUrl() {
        return eventImgUrl;
    }

    public void setEventImgUrl(String eventImgUrl) {
        this.eventImgUrl = eventImgUrl;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
