package org.alternatetown;

import org.town.Person;
import org.town.exception.IllegalRequestorException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class PermitManager {
    private final Set<Vehicle> registeredVehicles;

    public PermitManager() {
        this.registeredVehicles = new HashSet<>();
    }

    public String generatePermitNumber(Vehicle vehicle, Person requester) throws IllegalRequestorException {
        if (!isRegisteredOwner(requester, vehicle.getRegisteredOwners()))
            throw new IllegalRequestorException();

        registeredVehicles.add(vehicle);
        return "PRT" + vehicle.getNumberPlate();
    }

    public Stream<Vehicle> getType(VehicleType vehicleType) {
        return registeredVehicles.stream().filter((vehicle)->vehicle.getVehicleType() == vehicleType);
    }
    public boolean isRegisteredOwner(Person person, List<Person> owners) {
        return owners.contains(person);
    }
}
