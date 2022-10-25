package com.astontech.dao.mysql;

import com.astontech.bo.Vehicle;
import com.astontech.bo.VehicleModel;
import com.astontech.dao.VehicleModelDAO;
import common.helpers.DateHelper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleModelDAOImpl extends MySQL implements VehicleModelDAO {
    @Override
    public VehicleModel getVehicleModeleById(int vehicleModelId) {
        Connect();
        VehicleModel vehicleModel = null;

        try{
            String sp = "{Call GetVehicleModel(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_BY_ID);
            cStmt.setInt(2, vehicleModelId);

            ResultSet rs = cStmt.executeQuery();

            if(rs.next()){
                vehicleModel = HydrateObject(rs);
            }

        }catch (SQLException sqlEx){
            logger.info(sqlEx);
        }


        return vehicleModel;
    }

    @Override
    public List<VehicleModel> getVehicleModelList() {
        Connect();
        List<VehicleModel> vehicleModelList = new ArrayList<>();

        try{
            String sp = "{Call GetVehicleModel(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_COLLECTION);
            cStmt.setInt(2, 0);

            ResultSet rs = cStmt.executeQuery();

            while(rs.next()){
                vehicleModelList.add(HydrateObject(rs));
            }

        }catch (SQLException sqlEx){
            logger.info(sqlEx);
        }
        return vehicleModelList;
    }

    @Override
    public int insertVehicleModel(VehicleModel vehicleModel) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecVehicleModel(?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, INSERT);
            cStmt.setInt(2, 0);
            cStmt.setString(3,vehicleModel.getVehicleModelName());
            cStmt.setInt(4, vehicleModel.getVehicleMakeId());


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
    public boolean updateVehicleModel(VehicleModel vehicleModel) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecVehicleModel(?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2, vehicleModel.getVehicleModelId());
            cStmt.setString(3,vehicleModel.getVehicleModelName());
            cStmt.setInt(4, vehicleModel.getVehicleMakeId());


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
    public boolean deleteVehicleModel(int vehicleModelId) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecVehicleModel(?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, vehicleModelId);
            cStmt.setString(3,"");
            cStmt.setInt(4,0);


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

    public static VehicleModel HydrateObject(ResultSet rs) throws SQLException{
        VehicleModel vehiclemodel = new VehicleModel();

        vehiclemodel.setVehicleModelId(rs.getInt(1));
        vehiclemodel.setVehicleModelName(rs.getString(2));
        vehiclemodel.setVehicleMakeId(rs.getInt(3));


        return vehiclemodel;
    }
}
