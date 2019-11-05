/******************************************************************************
*  Purpose: This is a configuration class of rabbitMQ.It creates beans which are
*  			required while sending message using rabbitMQ
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   04-11-2019
*
******************************************************************************/
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
	
	/**
	 * purpose: this method is used for creating a bean of Queue
	 * 
	 * @return Queue
	 */
	@Bean
	public Queue getQueue()
	{
		return new Queue("userMessageQueue", false);
	}
	
	/**
	 * purpose: this method is used for creating a bean of TopicExchange
	 * 
	 * @return TopicExchange
	 */
	@Bean
	public TopicExchange getExchange()
	{
		return new TopicExchange("exchange");
	}
	/**
	 * purpose: this method is used for creating a bean of Binding
	 * 
	 * @param queue Queue
	 * @param exchange TopicExchange
	 * 
	 * @return Binding
	 */
	@Bean
	public Binding getBinding(Queue queue,TopicExchange exchange)
	{
		return BindingBuilder.bind(queue).to(exchange).with("userMessageQueue");
	}

	/**
	 * purpose: this method is used for creating a bean of SimpleMessageListenerContainer
	 * 
	 * @param connectionFactory
	 * @param messageListenerAdapter
	 * 
	 * @return SimpleMessageListenerContainer
	 */
	/**
	 *
	 * @return
	 */
	@Bean
	public SimpleMessageListenerContainer getContainer(ConnectionFactory connectionFactory,MessageListenerAdapter messageListenerAdapter)
	{
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setQueueNames("userMessageQueue");
		container.setConnectionFactory(connectionFactory);
		container.setMessageListener(messageListenerAdapter);
		return container;
	}
	/**
	 * purpose: this method is used for creating a bean of MessageReceiver
	 * 
	 * @return MessageReceiver
	 */
	@Bean
	public MessageReceiver getReceiver()
	{
		return new MessageReceiver();
	}
	/**
	 * purpose: this method is used for creating a bean of MessageListenerAdapter
	 * 
	 * @return MessageListenerAdapter
	 */
	@Bean
	public MessageListenerAdapter getAdapter(MessageReceiver receiver)
	{
		return new MessageListenerAdapter(receiver,"sendMessage");
	}
}
