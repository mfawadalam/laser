package com.dataoptimo.laser.streaming.consumer;

/**
 * Created by fawadalam on 05/12/2016.
 */
public interface ValueParser<T> {

    public T parse(String value);
}
