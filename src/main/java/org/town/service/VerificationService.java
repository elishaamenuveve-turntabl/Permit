package org.town.service;

import org.town.Person;
import org.town.vehicle.Vehicle;

public interface VerificationService {

    boolean verifyPerson(Person p, Vehicle v);
}
