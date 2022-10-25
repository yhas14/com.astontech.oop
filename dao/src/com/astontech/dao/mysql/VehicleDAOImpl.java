package com.astontech.dao.mysql;

import com.astontech.bo.Vehicle;
import com.astontech.dao.VehicleDAO;
import common.helpers.DateHelper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl extends MySQL implements VehicleDAO {
    // Get Stored Procedure
    @Override
    public Vehicle getVehicleById(int vehicleId) {
        Connect();
        Vehicle vehicle = null;

        try{
            String sp = "{Call GetVehicle(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_BY_ID);
            cStmt.setInt(2, vehicleId);

            ResultSet rs = cStmt.executeQuery();

            if(rs.next()){
                vehicle = HydrateObject(rs);
            }

        }catch (SQLException sqlEx){
            logger.info(sqlEx);
        }


        return vehicle;
    }

    @Override
    public List<Vehicle> getVehicleList() {
        Connect();
        List<Vehicle> vehicleList = new ArrayList<>();

        try{
            String sp = "{Call GetVehicle(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_COLLECTION);
            cStmt.setInt(2, 0);

            ResultSet rs = cStmt.executeQuery();

            while(rs.next()){
                vehicleList.add(HydrateObject(rs));
            }

        }catch (SQLException sqlEx){
            logger.info(sqlEx);
        }
        return vehicleList;
    }






    // Execute Stored Procedure
    @Override
    public int insertVehicle(Vehicle vehicle) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecVehicle(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2, vehicle.getVehicleId());
            cStmt.setInt(3,vehicle.getYear());
            cStmt.setInt(4, vehicle.getLicensePlate());
            cStmt.setInt(5, vehicle.getVin());
            cStmt.setString(6, vehicle.getColor());
            cStmt.setInt(7, vehicle.getisPurchase());
            cStmt.setInt(8, vehicle.getPurchasePrice());
            cStmt.setDate(9, DateHelper.utilDateToSqlDate(vehicle.getPurchaseDate()));
            cStmt.setInt(10, vehicle.getVehicleModelId());

            ResultSet rs = cStmt.executeQuery();

            if(rs.next()){
                id = rs.getInt(1);// id =  LAST_INSERT_ID() value and we get it from sql
            }

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }

        // value returned from insert statement is an int from primary key column
        return id;
    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecVehicle(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2, vehicle.getVehicleId());
            cStmt.setInt(3,vehicle.getYear());
            cStmt.setInt(4, vehicle.getLicensePlate());
            cStmt.setInt(5, vehicle.getVin());
            cStmt.setString(6, vehicle.getColor());
            cStmt.setInt(7, vehicle.getisPurchase());
            cStmt.setInt(8, vehicle.getPurchasePrice());
            cStmt.setDate(9, DateHelper.utilDateToSqlDate(vehicle.getPurchaseDate()));
            cStmt.setInt(10, vehicle.getVehicleModelId());

            ResultSet rs = cStmt.executeQuery();

            if(rs.next()){
                id = rs.getInt(1);// id =  LAST_INSERT_ID() value and we get it from sql
            }

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }


        // value returned from insert statement is an int from primary key column
        return id > 0;
    }

    @Override
    public boolean deleteVehicle(int vehicleId) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecVehicle(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, vehicleId);
            cStmt.setInt(3,0);
            cStmt.setInt(4, 0);
            cStmt.setInt(5, 0);
            cStmt.setString(6, "");
            cStmt.setInt(7, 0);
            cStmt.setInt(8, 0);
            cStmt.setDate(9, new java.sql.Date(0));
            cStmt.setInt(10, 0);

            ResultSet rs = cStmt.executeQuery();

            if(rs.next()){
                id = rs.getInt(1);// id =  LAST_INSERT_ID() value and we get it from sql
            }

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }

        // value returned from insert statement is an int from primary key column
        return id > 0;
    }



    // Hydrate Object

    public static Vehicle HydrateObject(ResultSet rs) throws SQLException{
        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleId(rs.getInt(1));
        vehicle.setYear(rs.getInt(2));
        vehicle.setLicensePlate(rs.getInt(3));
        vehicle.setVin(rs.getInt(4));
        vehicle.setColor(rs.getString(5));
        vehicle.setisPurchase(rs.getInt(6));
        vehicle.setPurchasePrice(rs.getInt(8));
        vehicle.setPurchaseDate(rs.getDate(7));
        vehicle.setVehicleModelId(rs.getInt(9));

        return vehicle;
    }
}
