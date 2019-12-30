package com.bridgelabz.usermanagement.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfi {

	@Bean
	public Queue getQueue() {
		return new Queue("userMessageQueue",false);
	}
	
	@Bean
	public TopicExchange getExchange() {
		return new TopicExchange("Exchange");
	}
	
	@Bean
	public Binding getBinding(Queue queue,TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("userMessageQueue");
	}
	@Bean
	public SimpleMessageListenerContainer getContainer(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setQueueNames("userMessageQueue");
		container.setConnectionFactory(connectionFactory);
		container.setMessageListener(messageListenerAdapter);
		return container;
	}
	@Bean
	public MessageReceiver getMessageReceiver() {
		return new MessageReceiver();
	}
	@Bean
	public MessageListenerAdapter getListnerAdapter(MessageReceiver receiver) {
		return new MessageListenerAdapter(receiver, "sendMessage");
	}
}
