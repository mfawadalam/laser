package com.dataoptimo;

import com.dataoptimo.laser.streaming.consumer.SparkKafkaConsumer;
import junit.framework.TestCase;

/**
 * Created by fawadalam on 27/11/2016.
 */
public class SparkKafkaConsumerTest extends TestCase {

    public void testConsume() throws Exception {
        SparkKafkaConsumer consumer = new SparkKafkaConsumer("my-topic");
        consumer.consume();

    }
}
