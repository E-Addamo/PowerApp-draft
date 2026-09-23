package com.powerapp;

public class KeyValue {
    private String key;
    private String value;
    // empty is true when both key and value are set to "".
    private boolean empty;

    public KeyValue(String key, String value){
        this.key = (key == null) ? "" : key;
        this.value = (value == null) ? "" : value;
        empty = key.equals("") && value.equals("");
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }

    public boolean isEmpty(){
        return empty;
    }
}
