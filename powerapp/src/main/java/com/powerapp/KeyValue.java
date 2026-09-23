package com.powerapp;

public class KeyValue {
    private String key;
    private String value;

    public KeyValue(String key, String value){
        this.key = (key == null) ? "" : key;
        this.value = (value == null) ? "" : value; 
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }
}
