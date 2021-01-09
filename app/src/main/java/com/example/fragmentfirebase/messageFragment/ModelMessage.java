package com.example.fragmentfirebase.messageFragment;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class ModelMessage {

    public String author;
    public String body;

    public ModelMessage() {
    }

    public ModelMessage(String author, String body) {
        this.author = author;
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
