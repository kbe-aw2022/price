package kbe.aw.price.rabbitmq;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kbe.aw.price.configuration.RabbitConfiguration;
import kbe.aw.price.model.HardwareComponent;
import kbe.aw.price.model.Product;
import kbe.aw.price.calculation.PriceCalculator;

@Component
public class PriceCalculationRequestHandler
{
   @Autowired
   private ObjectMapper objectMapper;

   @Autowired
   private PriceCalculator priceCalculator;

   @RabbitListener(queues = RabbitConfiguration.REQUEST_PRICE_QUE)
   public String handlePriceCalculationRequest(Product product)
   {
      List<HardwareComponent> hardwareComponents = product.getHardwareComponents();

      try
      {
         return objectMapper.writeValueAsString(priceCalculator.calculatePrice(hardwareComponents));
      }
      catch (JsonProcessingException e)
      {
         throw new RuntimeException(e);
      }
   }

}
