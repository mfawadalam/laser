package com.dataoptimo;

import com.dataoptimo.laser.generator.DataGenerator;
import com.dataoptimo.laser.generator.GPSCordinatesGenerator;
import com.dataoptimo.laser.model.GPSCordinates;
import com.dataoptimo.laser.streaming.producer.KinesisProducerWrapper;
import junit.framework.TestCase;

/**
 * Created by fawadalam on 06/12/2016.
 */
public class KinesisProducerWrapperTest extends TestCase {

    public void testProduce(){
        DataGenerator<GPSCordinates> dataGenerator = new GPSCordinatesGenerator();
        KinesisProducerWrapper producer = new KinesisProducerWrapper(dataGenerator,"mystream",10);
        producer.produce();
    }
}
