package com.astontech.dao;

import com.astontech.bo.Vehicle;
import com.astontech.bo.VehicleModel;

import java.util.List;

public interface VehicleModelDAO {
    public VehicleModel getVehicleModeleById(int vehicleModelId);
    public List<VehicleModel> getVehicleModelList();

    public int insertVehicleModel(VehicleModel vehicleModel);
    public boolean updateVehicleModel(VehicleModel vehicleModel);
    public boolean deleteVehicleModel(int vehicleModelId);
}
