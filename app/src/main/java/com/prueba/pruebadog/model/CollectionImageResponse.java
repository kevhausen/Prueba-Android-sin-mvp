package com.prueba.pruebadog.model;

import com.google.firebase.Timestamp;

public class CollectionImageResponse {

private String url;
private Timestamp timestamp;

    public CollectionImageResponse(String url, Timestamp timestamp) {
        this.url = url;
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
