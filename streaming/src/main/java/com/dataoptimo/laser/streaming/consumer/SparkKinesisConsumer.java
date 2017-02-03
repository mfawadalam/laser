package com.dataoptimo.laser.streaming.consumer;

import com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream;
import org.apache.spark.SparkConf;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kinesis.KinesisUtils;

/**
 * Created by fawadalam on 05/12/2016.
 */
public class SparkKinesisConsumer {

    String topic;
    String awsAccessKey;
    String awsSecretKey;
    String url;
    InitialPositionInStream inpos;

    public SparkKinesisConsumer(String topic,String awsAccessKey,String awsSecretKey,String url){
        this.topic = topic;
        this.url = "https://kinesis.eu-west-1.amazonaws.com";
        this.awsAccessKey = awsAccessKey;
        this.awsSecretKey = awsSecretKey;
        this.inpos = InitialPositionInStream.LATEST;
    }


    public SparkKinesisConsumer(String topic){
        this.topic = topic;
    }

    public void consume() throws Exception {

        SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("spark-consumer");
        JavaStreamingContext ssc = new JavaStreamingContext(conf, Durations.seconds(3));

        JavaReceiverInputDStream<byte []> stream = KinesisUtils.createStream(ssc,"kinesis-appp",topic,url,
                "eu-west-1",inpos,Durations.seconds(3), StorageLevel.MEMORY_ONLY(),awsAccessKey,awsSecretKey);
        stream.foreachRDD(x->x.foreach(y->System.out.println(y)));

        ssc.start();
        ssc.awaitTermination();
    }

}
