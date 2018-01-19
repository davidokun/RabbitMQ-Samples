package com.darksideofthedev.consumer;

import com.darksideofthedev.factory.RabbitConnectionFactory;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyConsumer {


    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitConnectionFactory.getChannel();

        channel.queueDeclare(RabbitConnectionFactory.QUEUE_NAME, false, false, false, null);
        System.out.println("[*] Waiting for messages...");

        Consumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                String message = new String(body, "UTF-8");
                System.out.println("[X] Received message " + message);
            }
        };

        channel.basicConsume(RabbitConnectionFactory.QUEUE_NAME, true, consumer);

    }
}
