package org.alternatetown;

import lombok.Builder;
import org.town.Person;

import java.util.List;

@Builder
public class Vehicle {
    private final List<Person> registeredOwners;
    private final VehicleType vehicleType;
    private String numberPlate;
    private String permitNumber;
    private double weightCapacityKG;
    private double gallonCapacityCC;



    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public List<Person> getRegisteredOwners() {
        return registeredOwners.stream().toList();
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public double getWeightCapacityKG() {
        return weightCapacityKG;
    }

    public double getGallonCapacityCC() {
        return gallonCapacityCC;
    }
}
