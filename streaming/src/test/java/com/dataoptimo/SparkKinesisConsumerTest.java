package com.dataoptimo;

import com.dataoptimo.laser.streaming.consumer.SparkKinesisConsumer;
import junit.framework.TestCase;

/**
 * Created by fawadalam on 06/12/2016.
 */
public class SparkKinesisConsumerTest extends TestCase {

    public void testConsume(){

        SparkKinesisConsumer consumer = new SparkKinesisConsumer("mystream","AKIAI7V74BCDOSRDE7BQ", "wRspo8TTFD+D19gbZIyBsDjkvMEebkA6PSMRoy7u","");
        try {
            consumer.consume();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
