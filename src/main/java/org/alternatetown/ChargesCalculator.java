package org.alternatetown;

public class ChargesCalculator {
    private static final double BASE_CHARGE_PC = 20;
    private static final double BASE_CHARGE_SMALL_MB = 7;
    private static final double BASE_CHARGE_LARGE_MB = 10;
    private static final double VOLUME_CUTOFF_POINT_MB = 850;
    private static final double BASE_CHARGE_CV = 30;
    private static final double CHARGE_PER_ADDITIONAL_20KG_CV = 5;
    private static final double FREE_CAPACITY_CV = 150;



    public static double calculateCharges(Vehicle vehicle) {
        return switch (vehicle.getVehicleType()) {
            case MOTORBIKE ->
                 calculateMotorbikeCharge(vehicle);

            case PRIVATE_CAR -> {
                 yield calculatePrivateCarCharge(vehicle);
            }
            case CONSTRUCTION_VEHICLE ->
                 calculateConstructionVehicleCharge(vehicle);
          };
    }

    private static double calculatePrivateCarCharge(Vehicle vehicle) {
        return vehicle.getRegisteredOwners().size() * BASE_CHARGE_PC;
    }
    private static double calculateMotorbikeCharge(Vehicle vehicle) {
        return vehicle.getGallonCapacityCC() > VOLUME_CUTOFF_POINT_MB ? BASE_CHARGE_LARGE_MB : BASE_CHARGE_SMALL_MB;
    }
    private static double calculateConstructionVehicleCharge(Vehicle vehicle) {
        return BASE_CHARGE_CV + Math.ceil((vehicle.getWeightCapacityKG() - FREE_CAPACITY_CV) / 20.0) * CHARGE_PER_ADDITIONAL_20KG_CV;
    }


}
