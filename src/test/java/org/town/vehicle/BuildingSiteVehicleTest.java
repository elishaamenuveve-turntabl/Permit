package org.town.vehicle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuildingSiteVehicleTest {
    BuildingSiteVehicle underTest;

    @BeforeEach
    void setUp() {
        underTest = new BuildingSiteVehicle("GR-7474-23", 200.0);
    }

    @Test
    void generatesChargeCorrectlyForLargerThanFreeCapacity() {
        //arrange
        underTest.setCapacity(300);

        //act
        double charge = underTest.generateCharge();

        //assert
        Assertions.assertEquals(70.0, charge);

    }

    @Test
    void generatesChargeCorrectlyForWithinFreeCapacity() {
        //arrange
        underTest.setCapacity(50);

        //act
        double charge = underTest.generateCharge();

        //assert
        Assertions.assertEquals(30.0, charge);

    }

}