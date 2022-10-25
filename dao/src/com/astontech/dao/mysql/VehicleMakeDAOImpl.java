package com.astontech.dao.mysql;

import com.astontech.bo.VehicleMake;
import com.astontech.dao.VehicleMakeDAO;
import common.helpers.DateHelper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleMakeDAOImpl extends MySQL implements VehicleMakeDAO {
    @Override
    public VehicleMake getVehicleMakeById(int vehicleMakeId) {
        Connect();
        VehicleMake vehicleMake = null;

        try{
            String sp = "{Call GetVehicleMake(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_BY_ID);
            cStmt.setInt(2, vehicleMakeId);

            ResultSet rs = cStmt.executeQuery();

            if(rs.next()){
                vehicleMake = HydrateObject(rs);
            }

        }catch (SQLException sqlEx){
            logger.info(sqlEx);
        }


        return vehicleMake;
    }

    @Override
    public List<VehicleMake> getVehicleMakeList() {
        Connect();
        List<VehicleMake> vehicleMakeList = new ArrayList<>();

        try{
            String sp = "{Call GetVehicleMake(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_COLLECTION);
            cStmt.setInt(2, 0);

            ResultSet rs = cStmt.executeQuery();

            while(rs.next()){
                vehicleMakeList.add(HydrateObject(rs));
            }

        }catch (SQLException sqlEx){
            logger.info(sqlEx);
        }

        return vehicleMakeList;
    }

    @Override
    public int insertVehicleMake(VehicleMake vehicleMake) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecVehicleMake(?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, INSERT);
            cStmt.setInt(2, 0);
            cStmt.setString(3,vehicleMake.getVehicleMakeName());
            cStmt.setDate(4, DateHelper.utilDateToSqlDate(vehicleMake.getCreateDate()));


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
    public boolean updateVehicleMake(VehicleMake vehicleMake) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecVehicleMake(?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);
            cStmt.setInt(2, vehicleMake.getVehicleMakeId());
            cStmt.setString(3,vehicleMake.getVehicleMakeName());
            cStmt.setDate(4, DateHelper.utilDateToSqlDate(vehicleMake.getCreateDate()));


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
    public boolean deleteVehicleMake(int vehicleMakeId) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecVehicleMake(?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, vehicleMakeId);
            cStmt.setString(3,"");
            cStmt.setDate(4, new java.sql.Date(0));


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

    public static VehicleMake HydrateObject(ResultSet rs) throws SQLException {
        VehicleMake vehiclemake = new VehicleMake();

        vehiclemake.setVehicleMakeId(rs.getInt(1));
        vehiclemake.setVehicleMakeName(rs.getString(2));

        return vehiclemake;
    }
}
