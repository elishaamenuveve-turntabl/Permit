package org.town.vehicle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.town.Person;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class MotorbikeTest {
    Motorbike underTest;

    @BeforeEach
    void setUp() {
        underTest = new Motorbike("GR-7474-23",50.0);
    }

    @Test
    void generatesChargeCorrectlyLargeVehicles() {
        //arrange
        underTest.setSizeInCC(1000.0);


        //act
        double charge = underTest.generateCharge();

        //assert
        Assertions.assertEquals(10.0, charge);

    }

    @Test
    void generatesChargeCorrectlySmallVehicles() {
        //arrange
        underTest.setSizeInCC(780.0);


        //act
        double charge = underTest.generateCharge();

        //assert
        Assertions.assertEquals(7.0, charge);

    }
}