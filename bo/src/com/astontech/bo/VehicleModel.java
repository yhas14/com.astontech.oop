package com.astontech.bo;

public class VehicleModel extends BaseBO{
    private int VehicleModelId;
    private String VehicleModelName;
    private int VehicleMakeId;

    //region CONSTRUCTOR
        public VehicleModel(){}
        public VehicleModel(int vehicleModelId, String vehicleModelName){
            this.setVehicleModelId(vehicleModelId);
            this.setVehicleModelName(vehicleModelName);
        }
    //endregion
    //region GETTERS/SETTERS
    public int getVehicleModelId() {
        return VehicleModelId;
    }

    public void setVehicleModelId(int vehicleModelId) {
        VehicleModelId = vehicleModelId;
    }

    public String getVehicleModelName() {
        return VehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        VehicleModelName = vehicleModelName;
    }

    public int getVehicleMakeId() {
        return VehicleMakeId;
    }

    public void setVehicleMakeId(int vehicleMakeId) {
        VehicleMakeId = vehicleMakeId;
    }
    //endregion
}
