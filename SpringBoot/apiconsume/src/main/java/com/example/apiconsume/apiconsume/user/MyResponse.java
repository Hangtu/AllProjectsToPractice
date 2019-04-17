package com.example.apiconsume.apiconsume.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * data
 */
@JsonIgnoreProperties(ignoreUnknown = true) // to indicate that any properties not bound in this type should be ignored.
public class MyResponse {

    private Data data;

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    
}