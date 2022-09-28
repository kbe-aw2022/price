package kbe.aw.price.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import kbe.aw.price.model.HardwareComponent;

class PriceCalculatorTest
{
   PriceCalculator priceCalculator = new PriceCalculator();

   @Test
    void calculatePrice_must_calculate_correct_price()
   {
      HardwareComponent firstHardwareComponent = createHardwareComponent(1, "first", 2.0);
      HardwareComponent secondHardwareComponent = createHardwareComponent(2, "second", 55D);
      List<HardwareComponent> hardwareComponents = new ArrayList<>(Arrays.asList(firstHardwareComponent, secondHardwareComponent));

      double calculatedPrice = priceCalculator.calculatePrice(hardwareComponents);

      assertEquals(57D, calculatedPrice);
   }

   @Test
   void calculatePrice_when_price_of_hardware_components_is_0()
   {
      HardwareComponent firstHardwareComponent = createHardwareComponent(1, "first", 0D);
      HardwareComponent secondHardwareComponent = createHardwareComponent(2, "second", 0D);
      List<HardwareComponent> hardwareComponents = new ArrayList<>(Arrays.asList(firstHardwareComponent, secondHardwareComponent));

      double calculatedPrice = priceCalculator.calculatePrice(hardwareComponents);

      assertEquals(0D, calculatedPrice);
   }

   @Test
   void calculatePrice_when_empty_list_is_handled()
   {
      List<HardwareComponent> hardwareComponents = new ArrayList<>();

      double calculatedPrice = priceCalculator.calculatePrice(hardwareComponents);

      assertEquals(0D, calculatedPrice);
   }

   private HardwareComponent createHardwareComponent(Integer id, String name, Double price)
   {
      HardwareComponent hardwareComponent = new HardwareComponent();
      hardwareComponent.setId(id);
      hardwareComponent.setName(name);
      hardwareComponent.setPrice(price);

      return hardwareComponent;
   }
}