package org.town.permit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.town.Person;
import org.town.exception.IllegalRequestorException;
import org.town.vehicle.Motorbike;
import org.town.vehicle.PrivateCar;
import org.town.vehicle.Vehicle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PermitManagerTest {

    PermitManager underTest;



    @BeforeEach
    void setUp() {
        underTest = new PermitManager();
    }

    @Test
    void returnsPermitOnRegisteredOwner () throws IllegalRequestorException {
        Person person = mock(Person.class);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getRegisteredOwners()).thenReturn(List.of(person));
        when(vehicle.getNumberPlate()).thenReturn("GH-7474-23");


       String permitNumber = underTest.generatePermitNumber(vehicle, person);

        Assertions.assertNotNull(permitNumber);
        Assertions.assertEquals("PRTGH-7474-23", permitNumber);
    }

    @Test
    void returnsExceptionOnUnregisteredOwner () {
        Person person = mock(Person.class);
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getRegisteredOwners()).thenReturn(List.of());

        Assertions.assertThrows(IllegalRequestorException.class, ()-> underTest.generatePermitNumber(vehicle, person));
    }

//    @Test
//    void returnsPermitOnRegisteredOwnerUnmocked () {
//        Person person = new Person("SS123", "Jane Doe");
//        Vehicle vehicle = new Motorbike("GH-123-21",230.0);
//        vehicle.setRegisteredOwners(List.of(person));
//
//
//        String permitNumber = underTest.generatePermitNumber(vehicle, person);
//
//        Assertions.assertNotNull(permitNumber);
//        Assertions.assertEquals("PRTGH-123-21", permitNumber);
//    }

}