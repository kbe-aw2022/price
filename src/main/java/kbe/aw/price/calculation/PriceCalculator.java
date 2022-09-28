package kbe.aw.price.calculation;

import java.util.List;

import org.springframework.stereotype.Service;

import kbe.aw.price.model.HardwareComponent;

@Service
public class PriceCalculator
{
   public Double calculatePrice(List<HardwareComponent> hardwareComponents)
   {
      double price = 0D;

      for(HardwareComponent hardwareComponent : hardwareComponents)
      {
         price = price + hardwareComponent.getPrice();
      }

      return price;
   }
}
