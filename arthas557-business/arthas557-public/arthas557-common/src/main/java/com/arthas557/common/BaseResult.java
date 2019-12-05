package com.arthas557.common;

public class BaseResult<T> {

    public T data;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
