package org.town.permit;

import org.town.Person;
import org.town.exception.IllegalRequestorException;
import org.town.service.PermitIssuerService;
import org.town.service.VerificationService;
import org.town.vehicle.Vehicle;
import org.town.vehicle.VehicleType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermitManager {
    private final Set<Vehicle> registeredVehicles;
    private final VerificationService verificationService;
    private final PermitIssuerService permitIssuerService;

    public PermitManager(VerificationService verificationService, PermitIssuerService permitIssuerService) {
        this.registeredVehicles = new HashSet<>();
        this.verificationService = verificationService;
        this.permitIssuerService = permitIssuerService;
    }

    public Set<Vehicle> getRegisteredVehicles() {
        return registeredVehicles;
    }

    public List<Vehicle> getType(VehicleType vehicleType) {
        return registeredVehicles.stream().filter((vehicle)->vehicle.getVehicleType() == vehicleType).toList();
    }

    public String generatePermitNumber(Vehicle vehicle, Person requestor) throws IllegalRequestorException {
        if (!verificationService.verifyPerson(requestor,vehicle))
            throw new IllegalRequestorException();
        registeredVehicles.add(vehicle);
        if(vehicle.getVehicleType() == VehicleType.CONSTRUCTION_VEHICLE) {
            String permit = "PRT" + vehicle.getNumberPlate();
            vehicle.setPermitNumber(permit);
            return permit;
        }
        String permit = permitIssuerService.issuePermit(vehicle);
        vehicle.setPermitNumber(permit);
        return permit;
    }
}
