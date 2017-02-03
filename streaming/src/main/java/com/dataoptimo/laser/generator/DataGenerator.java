package com.dataoptimo.laser.generator;

import org.glassfish.jersey.server.model.Parameterized;

import java.lang.reflect.ParameterizedType;

/**
 * Created by fawadalam on 25/11/2016.
 */
public abstract class DataGenerator<T> {


    public Class getType(){
        return (Class)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public abstract T generate();
}
