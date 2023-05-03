package org.town.permit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.town.Person;
import org.town.exception.IllegalRequestorException;
import org.town.service.PermitIssuerService;
import org.town.service.VerificationService;
import org.town.vehicle.*;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PermitManagerTest {

    PermitManager underTest;
    private Person person;

    @Mock
    VerificationService mockVerificationService;
    @Mock
    PermitIssuerService mockPermitIssuerService;
    BuildingSiteVehicle buildingSiteVehicle;
    PrivateCar privateCar;
    Motorbike motorbike;


    @BeforeEach
    void setUp() {
        underTest = new PermitManager(mockVerificationService, mockPermitIssuerService);
        person = new Person("fjdsk3848","CR7");
        buildingSiteVehicle = new BuildingSiteVehicle("GN 4001 20",300);
        privateCar = new PrivateCar("GN 4001 20");
        motorbike = new Motorbike("GN 4001 20", 720.0);
    }

    @Test
    void returnsExceptionOnUnregisteredPerson() {
        when(mockVerificationService.verifyPerson(any(Person.class), any(Vehicle.class))).thenReturn(false);
        assertThatExceptionOfType(IllegalRequestorException.class)
                .isThrownBy(()-> underTest.generatePermitNumber(privateCar, person));
    }

    @Test
    void doesNotThrowExceptionOnVerifiedUser() throws IllegalRequestorException {
        when(mockVerificationService.verifyPerson(any(Person.class), any(Vehicle.class))).thenReturn(true);
        when(mockPermitIssuerService.issuePermit(any(Vehicle.class))).thenReturn("j");
        assertThatCode(()-> underTest.generatePermitNumber(privateCar, person)).doesNotThrowAnyException();
        verify(mockVerificationService,times(1)).verifyPerson(person,privateCar);
    }

    @Test
    void storesVehicleOnlyOnPermitIssue() throws IllegalRequestorException {
        when(mockVerificationService.verifyPerson(any(Person.class), any(Vehicle.class))).thenReturn(true).thenReturn(false);
        when(mockPermitIssuerService.issuePermit(any(Vehicle.class))).thenReturn("j");

        underTest.generatePermitNumber(privateCar,person);
        Set<Vehicle> registeredVehicles = underTest.getRegisteredVehicles();

        assertThat(registeredVehicles).contains(privateCar);
        assertThatExceptionOfType(IllegalRequestorException.class)
                .isThrownBy(() -> underTest.generatePermitNumber(motorbike,person));
        assertThat(registeredVehicles).doesNotContain(motorbike);
    }

    @Test
    void returnsIssuerServicePermitForPrivateCars() throws IllegalRequestorException {
        when(mockVerificationService.verifyPerson(any(Person.class), any(Vehicle.class))).thenReturn(true);
        when(mockPermitIssuerService.issuePermit(any(Vehicle.class))).thenReturn("j");

        underTest.generatePermitNumber(privateCar,person);

        verify(mockPermitIssuerService, times(1)).issuePermit(privateCar);
        assertThat(privateCar.getPermitNumber()).isEqualTo("j");
    }

    @Test
    void returnsIssuerServicePermitForMotorBikes() throws IllegalRequestorException {
        when(mockVerificationService.verifyPerson(any(Person.class), any(Vehicle.class))).thenReturn(true);
        when(mockPermitIssuerService.issuePermit(any(Vehicle.class))).thenReturn("j");

        underTest.generatePermitNumber(motorbike,person);

        verify(mockPermitIssuerService, times(1)).issuePermit(motorbike);
        assertThat(motorbike.getPermitNumber()).isEqualTo("j");
    }

    @Test
    void returnsDefaultPermitForSiteVehicle() throws IllegalRequestorException {
        when(mockVerificationService.verifyPerson(any(Person.class), any(Vehicle.class))).thenReturn(true);

        underTest.generatePermitNumber(buildingSiteVehicle,person);

        verify(mockPermitIssuerService, times(0)).issuePermit(buildingSiteVehicle);
        assertThat(buildingSiteVehicle.getPermitNumber()).isEqualTo("PRT" + buildingSiteVehicle.getNumberPlate());
    }

    @Test
    void returnVehicleByType() throws IllegalRequestorException {
        when(mockVerificationService.verifyPerson(any(Person.class), any(Vehicle.class))).thenReturn(true);
        when(mockPermitIssuerService.issuePermit(any(Vehicle.class))).thenReturn("j");

        underTest.generatePermitNumber(privateCar,person);
        underTest.generatePermitNumber(motorbike,person);
        underTest.generatePermitNumber(buildingSiteVehicle,person);

        assertThat(underTest.getType(VehicleType.PRIVATE_CAR)).isEqualTo(List.of(privateCar));
        assertThat(underTest.getType(VehicleType.MOTORBIKE)).isEqualTo(List.of(motorbike));
        assertThat(underTest.getType(VehicleType.CONSTRUCTION_VEHICLE)).isEqualTo(List.of(buildingSiteVehicle));
    }
}