package com.name.blog.util;

public class ResponseUtil {
    public String handleValue(Long value){
        return value != null ? String.valueOf(value) : "";
    }

    public String handleValue(String value){
        return value != null ? value : "";
    }
}
