package com.dataoptimo.laser.generator;

import com.dataoptimo.laser.model.GPSCordinates;

/**
 * Created by fawadalam on 25/11/2016.
 */
public class GPSCordinatesGenerator extends DataGenerator<GPSCordinates> {


    public GPSCordinates generate() {

        GPSCordinates data = new GPSCordinates(Math.random(), Math.random());
        return data;

    }

}
