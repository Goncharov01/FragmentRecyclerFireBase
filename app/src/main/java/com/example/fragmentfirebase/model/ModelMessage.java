package com.example.fragmentfirebase.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class ModelMessage {

    public String author = "qwetrty";
    public String body = "qwerty";

    public ModelMessage() {
    }

    public ModelMessage(String author, String body) {
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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("author", author);
        result.put("body", body);

        return result;
    }

    @Override
    public String toString() {
        return "ModelMessage{" +
                "author='" + author + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
