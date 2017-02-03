package com.dataoptimo.laser.streaming.producer;

import com.dataoptimo.laser.generator.DataGenerator;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by fawadalam on 25/11/2016.
 */


public final class KafkaProducerWrapper<T> {

    public DataGenerator<T> dataGenerator;
    public Class<T> cls;
    private int key;
    String topic;

    public KafkaProducerWrapper(DataGenerator dataGenerator, String topic, int key){
        this.dataGenerator = dataGenerator;
        this.cls = dataGenerator.getType();
        this.topic = topic;
        this.key = key;
    }

    public void produce(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "quickstart.cloudera:9092");
        props.put("acks", "all");
        //props.put("retries", 0);
        //props.put("batch.size", 16384);
        //props.put("linger.ms", 1);
        //props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer(props);
        while (true) {
            try {
                Thread.sleep(1000);
                T t = dataGenerator.generate();
                Method method = cls.getMethod("toString");
                String value = (String) method.invoke(t);
                System.out.println(t);
                producer.send(new ProducerRecord<String, String>(topic, Integer.toString(key) , value));
                System.out.println("Sent");
                //producer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
