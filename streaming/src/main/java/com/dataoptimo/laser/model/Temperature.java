package com.dataoptimo.laser.model;

/**
 * Created by fawadalam on 01/12/2016.
 */
public final class Temperature {

    private Double temperature;

    public Temperature(Double temperature){
        this.temperature = temperature;
    }

    public String toString(){
        return Double.toString(temperature);
    }
}
