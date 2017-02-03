package com.dataoptimo;

import com.dataoptimo.laser.generator.DataGenerator;
import com.dataoptimo.laser.generator.GPSCordinatesGenerator;
import com.dataoptimo.laser.model.GPSCordinates;
import com.dataoptimo.laser.streaming.producer.KafkaProducerWrapper;

import junit.framework.TestCase;

/**
 * Created by fawadalam on 25/11/2016.
 */
public class KafkaProducerWrapperTest extends TestCase {

    public void testProduce(){
        DataGenerator<GPSCordinates> dataGenerator = new GPSCordinatesGenerator();
        KafkaProducerWrapper producer = new KafkaProducerWrapper(dataGenerator,"temperature",10);
        producer.produce();

    }
}
