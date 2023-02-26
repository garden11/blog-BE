package com.name.blog.util;

public class ResponseUtil {
    public String convertString(Long value){
        return value != null ? String.valueOf(value) : "";
    }
}
