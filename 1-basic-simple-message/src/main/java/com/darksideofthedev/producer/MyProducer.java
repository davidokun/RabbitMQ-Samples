package com.darksideofthedev.producer;

import com.darksideofthedev.factory.RabbitConnectionFactory;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.darksideofthedev.factory.RabbitConnectionFactory.QUEUE_NAME;

public class MyProducer {


    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitConnectionFactory.getChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        String message = "First simple message";

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));

        System.out.println("[X] Sent message: " + message);

        channel.close();
        RabbitConnectionFactory.getConnection().close();
    }
}
