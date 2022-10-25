package com.astontech.dao;

import com.astontech.bo.Vehicle;

import java.util.List;

public interface VehicleDAO {
    public Vehicle getVehicleById(int vehicleId);
    public List<Vehicle> getVehicleList();

    public int insertVehicle(Vehicle vehicle);
    public boolean updateVehicle(Vehicle vehicle);
    public boolean deleteVehicle(int vehicle);

}
