package com.dataoptimo.laser.streaming.consumer;

import kafka.serializer.StringDecoder;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.apache.spark.streaming.api.java.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import scala.Tuple2;

/**
 * Created by fawadalam on 26/11/2016.
 */
public class SparkKafkaConsumer {

    String topic;



    public SparkKafkaConsumer(String topic){
        this.topic = topic;
    }

    public void consume() throws Exception {

        SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("spark-consumer");
        JavaStreamingContext ssc = new JavaStreamingContext(conf, Durations.seconds(3));
        Map<String, String> kafkaParams = new HashMap();
        Set<String> topicsSet = new HashSet(Arrays.asList(topic));

        kafkaParams.put("bootstrap.servers", "quickstart.cloudera:9092");
        kafkaParams.put("auto.offset.reset", "smallest");
        //kafkaParams.put("enable.auto.commit", false);
        JavaPairInputDStream<String,String> stream = KafkaUtils.createDirectStream(
                ssc,
                String.class,
                String.class,
                StringDecoder.class,
                StringDecoder.class,
                kafkaParams,
                topicsSet);

        JavaPairDStream<Integer,Double> temperatureKeyedById = stream.mapToPair(x->
                new Tuple2<>(Integer.parseInt(x._1()),Double.parseDouble(x._2())

        ));
        temperatureKeyedById.mapValues(x->new Tuple2<>(x,1))
        .reduceByKey((x,y)->new Tuple2<>(x._1()+y._1(),x._2()+y._2()))
                .mapToPair(x->new Tuple2<>(x._1(),x._2()._1()/x._2()._2()))
                .foreachRDD(x->x.foreach(record->System.out.println(record)));

        ssc.start();
        ssc.awaitTermination();
    }

}
