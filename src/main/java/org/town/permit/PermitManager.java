package org.town.permit;

import org.town.Person;
import org.town.exception.IllegalRequestorException;
import org.town.vehicle.Vehicle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermitManager {
    private final Set<Vehicle> registeredVehicles;


    public PermitManager() {
        this.registeredVehicles = new HashSet<>();
    }

    public String generatePermitNumber(Vehicle vehicle, Person requestor) throws IllegalRequestorException {
        if (!isRegisteredOwner(requestor, vehicle.getRegisteredOwners()))
            throw new IllegalRequestorException();

        registeredVehicles.add(vehicle);
        return "PRT" + vehicle.getNumberPlate();
    }
    public boolean isRegisteredOwner(Person person, List<Person> owners) {

        return owners.contains(person);
    }

}
