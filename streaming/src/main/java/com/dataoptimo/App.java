package com.dataoptimo;


import com.dataoptimo.laser.streaming.consumer.SparkKafkaConsumer;
import com.dataoptimo.laser.generator.DataGenerator;
import com.dataoptimo.laser.generator.DataGeneratorFactory;
import com.dataoptimo.laser.streaming.producer.KafkaProducerWrapper;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        //TODO: write code running the Producer and Consumer
        try {
            if (args[0].equals("producer")) {
                System.out.println("running producer");
                DataGenerator dataGenerator = DataGeneratorFactory.create(args[1]);
                String topic = args[2];
                KafkaProducerWrapper kafkaProducer = new KafkaProducerWrapper(dataGenerator,topic,Integer.parseInt(args[3]));
                kafkaProducer.produce();
            }

            else if  (args[0].equals("consumer")){
                SparkKafkaConsumer consumer  = new SparkKafkaConsumer(args[1]);
                consumer.consume();

            }
            else{
                System.out.print("use properly");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

