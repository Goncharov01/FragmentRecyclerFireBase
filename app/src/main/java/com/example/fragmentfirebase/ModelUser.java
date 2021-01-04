package com.example.fragmentfirebase;

public class ModelUser {

    String author;
    String body;

    public ModelUser(String author, String body) {
        this.author = author;
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
