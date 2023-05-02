package org.town.vehicle;

import org.town.Person;

import java.util.List;
import java.util.Objects;

public abstract class Vehicle {
    private List<Person> registeredOwners;
    private String numberPlate;
    private String permitNumber;

    public Vehicle(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public abstract double generateCharge();

    public List<Person> getRegisteredOwners() {
        return registeredOwners;
    }


    public void setRegisteredOwners(List<Person> registeredOwners) {
        this.registeredOwners = registeredOwners;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return numberPlate.equals(vehicle.numberPlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberPlate);
    }

}
