package com.dataoptimo.laser.generator;

/**
 * Created by fawadalam on 02/12/2016.
 */
public class DataGeneratorFactory {

    public static DataGenerator create(String className) throws Exception{
        if(className.equals("TemperatureGenerator")){
            return new TemperatureGenerator();
        }
        else if (className.equals("GPSCordinatesGenerator")){
            return new GPSCordinatesGenerator();
        }
        else
            throw new Exception("Data Generator not available");
    }

}
