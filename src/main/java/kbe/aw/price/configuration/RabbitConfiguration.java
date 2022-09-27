package kbe.aw.price.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration
{
   public static final String REQUEST_PRICE_QUE = "request_price_que";
   public static final String REQUEST_PRICE_EXCHANGE = "request_price_exchange";
   public static final String MESSAGE_ROUTING_KEY = "message_routingKey";

   @Bean
   public MessageConverter messageConverter()
   {
      return new Jackson2JsonMessageConverter();
   }

   @Bean
   public AmqpTemplate template(ConnectionFactory connectionFactory)
   {
      RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
      rabbitTemplate.setMessageConverter(messageConverter());
      return rabbitTemplate;
   }

   private static class RequestPriceConfiguration
   {
      @Bean
      @Qualifier(REQUEST_PRICE_QUE)
      public Queue request_price_que()
      {
         return new Queue(REQUEST_PRICE_QUE);
      }


      @Bean
      @Qualifier(REQUEST_PRICE_EXCHANGE)
      public TopicExchange request_price_exchange()
      {
         return new TopicExchange(REQUEST_PRICE_EXCHANGE);
      }

      @Bean
      public Binding binding_request_price_with_exchange(@Qualifier(REQUEST_PRICE_QUE) Queue queue,
            @Qualifier(REQUEST_PRICE_EXCHANGE) TopicExchange exchange)
      {
         return BindingBuilder
               .bind(queue)
               .to(exchange)
               .with(MESSAGE_ROUTING_KEY);
      }
   }
}
