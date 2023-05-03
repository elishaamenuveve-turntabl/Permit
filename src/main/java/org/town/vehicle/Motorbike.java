package org.town.vehicle;

public class Motorbike extends Vehicle{
    private static final double BASE_CHARGE_SMALL = 7;
    private static final double BASE_CHARGE_LARGE = 10;
    private static final double VOLUME_CUTOFF_POINT = 850;

    private Double sizeInCC;

    public Motorbike(String numberPlate, Double sizeInCC) {
        super(numberPlate, VehicleType.MOTORBIKE);
        this.sizeInCC = sizeInCC;
    }

    public void setSizeInCC(Double sizeInCC) {
        this.sizeInCC = sizeInCC;
    }

    @Override
    public double generateCharge() {
        return sizeInCC > VOLUME_CUTOFF_POINT ? BASE_CHARGE_LARGE : BASE_CHARGE_SMALL;
    }
}
