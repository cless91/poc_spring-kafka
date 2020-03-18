package com.example;

import com.example.generated.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

@SpringBootApplication
public class SpringKafkaSandboxApplication implements CommandLineRunner {

	// un producer kafka de spring-kafka
    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaSandboxApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Payment payment1 = Payment.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setAmount(777.0)
                .build();
        System.out.println("sending kafka message (topic1) " + payment1);
        kafkaTemplate.send("topic1", payment1);

        Payment payment2 = Payment.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setAmount(888.0)
                .build();
        System.out.println("sending kafka message (topic2) " + payment2);
        kafkaTemplate.send("topic2", payment2);
    }
}
