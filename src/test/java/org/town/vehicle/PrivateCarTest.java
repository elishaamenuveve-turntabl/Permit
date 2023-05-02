package org.town.vehicle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.town.Person;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PrivateCarTest {

    PrivateCar underTest;

    @BeforeEach
    void setUp() {
        underTest = new PrivateCar("GR-7474-23");
    }

    @Test
    void generatesChargeCorrectly() {
        //arrange
        Person person = mock(Person.class);
        underTest.setRegisteredOwners(List.of(person, person, person));

        //act
        double charge = underTest.generateCharge();

        //assert
        Assertions.assertEquals(60.0, charge);

    }
}