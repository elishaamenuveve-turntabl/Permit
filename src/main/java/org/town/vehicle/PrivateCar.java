package org.town.vehicle;

public class PrivateCar extends Vehicle{
    private static final double BASE_CHARGE = 20;

    public PrivateCar(String numberPlate) {
        super(numberPlate, VehicleType.PRIVATE_CAR);
    }


    @Override
    public double generateCharge() {
        return super.getRegisteredOwners().size() * BASE_CHARGE;
    }
}
