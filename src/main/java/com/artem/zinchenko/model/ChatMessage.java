package com.artem.zinchenko.model;

public class ChatMessage {
    private String message;
    private String login;
    private String timeStamp;

    public ChatMessage(String message, String login, String timeStamp) {
        this.message = message;
        this.login = login;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        //json format
        return "{\"timeStamp\":" + "\"" + timeStamp + "\"" + "," +
                "\"login\":" + "\"" + login + "\"" + "," +
                "\"message\":" + "\"" + message + "\"" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return timeStamp.equals(that.timeStamp);

    }

    @Override
    public int hashCode() {
        return timeStamp.hashCode();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
