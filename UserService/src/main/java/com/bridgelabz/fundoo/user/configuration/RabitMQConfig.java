package com.bridgelabz.fundoo.user.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bridgelabz.fundoo.user.services.MessageReceiver;

@Configuration
public class RabitMQConfig {
	
	@Bean
	public Queue queue()
	{
		return new Queue("userMessageQueue", false);
	}
	
	@Bean
	public TopicExchange exchange()
	{
		return new TopicExchange("exchange");
	}
	
	@Bean
	public Binding binding(Queue queue,TopicExchange exchange)
	{
		return BindingBuilder.bind(queue).to(exchange).with("userMessageQueue");
	}

	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,MessageListenerAdapter messageListenerAdapter)
	{
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setQueueNames("userMessageQueue");
		container.setConnectionFactory(connectionFactory);
		container.setMessageListener(messageListenerAdapter);
		return container;
	}
	
	@Bean
	public MessageReceiver receiver()
	{
		return new MessageReceiver();
	}
	
	@Bean
	public MessageListenerAdapter adapter(MessageReceiver receiver)
	{
		return new MessageListenerAdapter(receiver,"sendMessage");
	}
}
