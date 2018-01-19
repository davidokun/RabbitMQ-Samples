package com.darksideofthedev.factory;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitConnectionFactory {

    public final static String QUEUE_NAME = "simple-message";
    private final static String RABBIT_HOST = "localhost";

    private static Connection connection;
    private static Channel channel;

    public static Channel getChannel() throws IOException, TimeoutException {

        if (channel == null) {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(RABBIT_HOST);
            connection = factory.newConnection();
            channel = connection.createChannel();
        }

        return channel;
    }

    public static Connection getConnection() {
        return connection;
    }
}
