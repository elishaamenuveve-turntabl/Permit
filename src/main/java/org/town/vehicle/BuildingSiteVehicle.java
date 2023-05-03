package org.town.vehicle;

public class BuildingSiteVehicle extends Vehicle{
    private static final double BASE_CHARGE = 30;
    private static final double CHARGE_PER_ADDITIONAL_20KG = 5;
    private static final double FREE_CAPACITY = 150;
    private double capacity;

    public BuildingSiteVehicle(String numberPlate, double capacity) {
        super(numberPlate,VehicleType.CONSTRUCTION_VEHICLE);
        this.capacity = capacity;
    }


    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public double generateCharge() {
        if (capacity <= FREE_CAPACITY)
            return BASE_CHARGE;

        return BASE_CHARGE + Math.ceil((capacity - FREE_CAPACITY) / 20.0) * CHARGE_PER_ADDITIONAL_20KG;
    }


}
