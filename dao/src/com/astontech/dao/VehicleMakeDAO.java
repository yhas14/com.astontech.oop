package com.astontech.dao;

import com.astontech.bo.VehicleMake;

import java.util.List;

public interface VehicleMakeDAO {
    public VehicleMake getVehicleMakeById(int vehicleMakeId);
    public List<VehicleMake> getVehicleMakeList();

    public int insertVehicleMake(VehicleMake vehicleMake);
    public boolean updateVehicleMake(VehicleMake vehicleMake);
    public boolean deleteVehicleMake(int vehicleMakeId);
}
