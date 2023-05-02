package org.alternatetown;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.town.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChargesCalculatorTest {

    @Test
    void privateCarChargeTest () {
        Person person = new Person("SS123", "JohnDoe");
        Vehicle privateCar = Vehicle.builder()
                .vehicleType(VehicleType.PRIVATE_CAR)
                .numberPlate("GR-234")
                .registeredOwners(List.of(person, person))
                .build();

        double charges = ChargesCalculator.calculateCharges(privateCar);
        assertEquals(40, charges);

    }
    @Test
    void ConstructionChargeTest () {
        Vehicle constructionVehicle = Vehicle.builder()
                .vehicleType(VehicleType.CONSTRUCTION_VEHICLE)
                .numberPlate("GR-234")
                .weightCapacityKG(300)
                .build();

        double charges = ChargesCalculator.calculateCharges(constructionVehicle);
        assertEquals(70, charges);
    }

    @Test
    void MotorBikeChargeTest () {
        Vehicle constructionVehicle = Vehicle.builder()
                .vehicleType(VehicleType.MOTORBIKE)
                .numberPlate("GR-234")
                .gallonCapacityCC(800)
                .build();

        double charges = ChargesCalculator.calculateCharges(constructionVehicle);
        assertEquals(7, charges);
    }

}