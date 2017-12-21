package com.honva.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

@Controller
@RequestMapping("/rabbit")
public class RabbitMqController {
	private final static String QUEUE_NAME = "hello";

	@RequestMapping("/send")
	public void Send(@PathParam(value = "msg") String msg) throws IOException, TimeoutException {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.7.101");
		factory.setPort(5672);
		factory.setUsername("root");
		factory.setPassword("123456");
		// 创建连接
		Connection connection = factory.newConnection();
		// 创建消息通道
		Channel channel = connection.createChannel();
		// 生成消息队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		String message = msg;

		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

		System.out.println("[x] Sent '" + message + "'");

		channel.close();
		connection.close();
	}

	@RequestMapping("/rece")
	public void Rece() throws IOException, TimeoutException, ShutdownSignalException, ConsumerCancelledException,
			InterruptedException {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.7.101");
		factory.setPort(5672);
		factory.setUsername("root");
		factory.setPassword("123456");
		// 创建连接
		Connection connection = factory.newConnection();
		// 创建消息通道
		Channel channel = connection.createChannel();
		// 生成消息队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		System.out.println("[*] Waiting for message. To exist press CTRL+C");

//		DefaultConsumer defaultConsumer = new DefaultConsumer(channel);
//		channel.basicConsume(QUEUE_NAME, defaultConsumer);
		
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE_NAME, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println("[x] Received '" + message + "'");
		}
	}
}
