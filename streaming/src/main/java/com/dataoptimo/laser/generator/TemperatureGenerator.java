package com.dataoptimo.laser.generator;

import com.dataoptimo.laser.model.Temperature;

/**
 * Created by fawadalam on 01/12/2016.
 */
public class TemperatureGenerator extends DataGenerator<Temperature> {

    public static Class type = Temperature.class;

    @Override
    public Temperature generate() {
        return new Temperature(Math.random()*100);
    }
}
