package com.dataoptimo.laser.streaming.producer;

import com.amazonaws.auth.BasicAWSCredentials;

import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.dataoptimo.laser.generator.DataGenerator;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by fawadalam on 05/12/2016.
 */
public final class KinesisProducerWrapper<T> {

    public DataGenerator<T> dataGenerator;
    public Class<T> cls;
    private int key;
    String topic;

    public KinesisProducerWrapper(DataGenerator dataGenerator, String topic, int key) {
        this.dataGenerator = dataGenerator;
        this.cls = dataGenerator.getType();
        this.topic = topic;
        this.key = key;
    }

    public void produce() {
        BasicAWSCredentials credentialsProvider = new BasicAWSCredentials("AKIAI7V74BCDOSRDE7BQ", "wRspo8TTFD+D19gbZIyBsDjkvMEebkA6PSMRoy7u");
        AmazonKinesisClient amazonKinesisClient = new AmazonKinesisClient(credentialsProvider);
        amazonKinesisClient.setEndpoint("https://kinesis.eu-west-1.amazonaws.com");
        PutRecordsRequest putRecordsRequest = new PutRecordsRequest();
        putRecordsRequest.setStreamName(topic);


        //while (true) {
            try {
                Thread.sleep(1000);
                List<PutRecordsRequestEntry> putRecordsRequestEntryList = new ArrayList<>();
                T t = dataGenerator.generate();
                PutRecordsRequestEntry putRecordsRequestEntry = new PutRecordsRequestEntry();
                putRecordsRequestEntry.setData(ByteBuffer.wrap(String.valueOf(t).getBytes()));
                putRecordsRequestEntry.setPartitionKey(String.format("partitionKey-%d", key));
                putRecordsRequestEntryList.add(putRecordsRequestEntry);
                putRecordsRequest.setRecords(putRecordsRequestEntryList);
                PutRecordsResult putRecordsResult = amazonKinesisClient.putRecords(putRecordsRequest);
                System.out.println("Put Result" + putRecordsResult);
            }
            catch (Exception e){
                e.printStackTrace();
            }

       // }
    }
}
