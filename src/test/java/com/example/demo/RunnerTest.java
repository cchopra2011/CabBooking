package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.CabController;
import Controller.RiderController;
import Dao.CabManager;
import Dao.RiderManager;
import Dao.TripManager;
import Exception.CabAlreadyExistException;
import Exception.CabNotFoundException;
import Exception.NoCabsAvailableException;
import Strategies.DefaultPriceMatchingStrategy;
import Strategies.PriceMatchingStrategy;;

public class RunnerTest {
  CabController cabsController;
  RiderController ridersController;

  @BeforeEach
  void setUp() {
    CabManager cabsManager = new CabManager();
    RiderManager ridersManager = new RiderManager();

    PriceMatchingStrategy pricingStrategy = new DefaultPriceMatchingStrategy();

    TripManager tripsManager = new TripManager(cabsManager, ridersManager, pricingStrategy);

    cabsController = new CabController(cabsManager, tripsManager);
    ridersController = new RiderController(ridersManager, tripsManager);
  }

  @Test
  void testCabBookingFlow() {

    String r1 = "r1";
    ridersController.registerRider(r1, "ud", "1234567891");
    String r2 = "r2";
    ridersController.registerRider(r2, "du","123987622");
    String r3 = "r3";
    ridersController.registerRider(r3, "rider3","994356628");
    String r4 = "r4";
    ridersController.registerRider(r4, "rider4","9900983333");

    String c1 = "c1";
    cabsController.registerCab(c1, "driver1");
    String c2 = "c2";
    cabsController.registerCab(c2, "driver2");
    String c3 = "c3";
    cabsController.registerCab(c3, "driver3");
    String c4 = "c4";
    cabsController.registerCab(c4, "driver4");
    String c5 = "c5";
    cabsController.registerCab(c5, "driver5");

    cabsController.updateCabLocation(c1, 1.0, 1.0);
    cabsController.updateCabLocation(c2, 2.0, 2.0); //na
    cabsController.updateCabLocation(c3, 100.0, 100.0);
    cabsController.updateCabLocation(c4, 110.0, 110.0); //na
    cabsController.updateCabLocation(c5, 4.0, 4.0);

    cabsController.updateCabAvailability(c2, false);
    cabsController.updateCabAvailability(c4, false);

    ridersController.updateRiderLocation(r1, 300.0, 300.0);
    ridersController.updateRiderLocation(r2, 400.0, 400.0);
    ridersController.book(r1, c1, 500.0, 500.0);
    ridersController.book(r2, c3, 500.0, 500.0);

    System.out.println("\n### Printing current trips for r1 and r2");
    //System.out.println(ridersController.fetchHistory(r1).getBody());
    //System.out.println(ridersController.fetchHistory(r2).getBody());


    assertThrows(NoCabsAvailableException.class, () -> {
      ridersController.book(r3, c5, 500.0, 500.0);
    });

    
    assertThrows(CabAlreadyExistException.class, () -> {
      cabsController.registerCab("c1", "skjhsfkj");
    });

    assertThrows(CabNotFoundException.class, () -> {
      cabsController.updateCabLocation("shss", 110.0, 110.0);
    });

    assertThrows(CabNotFoundException.class, () -> {
      cabsController.updateCabAvailability("shss", false);
    });
  }
}
