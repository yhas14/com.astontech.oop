package com.astontech.dao.mysql;

import com.astontech.bo.Phone;
import com.astontech.dao.PhoneDAO;
import com.mysql.cj.protocol.Resultset;
import common.helpers.DateHelper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PhoneDAOImpl extends MySQL implements PhoneDAO {

    @Override
    public Phone getPhoneById(int phoneId) {
        Connect();

        Phone phone = null;
        try{
            String sp = "{Call GetPhone(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_BY_ID);
            cStmt.setInt(2, phoneId);

            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                phone = HydrateObject(rs);
            }
        }catch(SQLException sqlEx){
            logger.info(sqlEx);
        }
        return phone;
    }

    @Override
    public List<Phone> getPhoneList() {
        Connect();
        List<Phone>phoneList = new ArrayList<>();

        try{
            String sp = "{Call GetPhone(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_COLLECTION);
            cStmt.setInt(2, 0);

            ResultSet rs = cStmt.executeQuery();

            while(rs.next()){
                phoneList.add(HydrateObject(rs));
            }

        }catch (SQLException sqlEx){
            logger.info(sqlEx);
        }

        return phoneList;
    }

    @Override
    public int insertPhone(Phone phone) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecPhone(?,?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, INSERT);//queryId
            cStmt.setInt(2, 0); //PhoneID
            cStmt.setInt(3,phone.getEntityTypeId());// EntityTypeId
            cStmt.setInt(4, phone.getClientId());// ClientId
            cStmt.setInt(5, phone.getPersonId()); // PersonId
            cStmt.setInt(6, phone.getAreaCode()); // AreaCode
            cStmt.setInt(7, phone.getPhoneNumber()); // PhoneNumber
            cStmt.setInt(8, phone.getPhoneNumberPost()); // PhoneNumberPost

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
    public boolean updatePhone(Phone phone) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecPhone(?,?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);//queryId
            cStmt.setInt(2, phone.getPhoneId()); //PhoneID
            cStmt.setInt(3,phone.getEntityTypeId());// EntityTypeId
            cStmt.setInt(4, phone.getClientId());// ClientId
            cStmt.setInt(5, phone.getPersonId()); // PersonId
            cStmt.setInt(6, phone.getAreaCode()); // AreaCode
            cStmt.setInt(7, phone.getPhoneNumber()); // PhoneNumber
            cStmt.setInt(8, phone.getPhoneNumberPost()); // PhoneNumberPost

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
    public boolean deletePhone(int phoneId) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecPhone(?,?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, DELETE);//queryId
            cStmt.setInt(2, phoneId); //PhoneID
            cStmt.setInt(3, 0);// EntityTypeId
            cStmt.setInt(4, 0);// ClientId
            cStmt.setInt(5, 0); // PersonId
            cStmt.setInt(6, 0); // AreaCode
            cStmt.setInt(7, 0); // PhoneNumber
            cStmt.setInt(8, 0); // PhoneNumberPost

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



    // Hydrate Object Method
    public static Phone HydrateObject(ResultSet rs) throws SQLException{
        Phone phone = new Phone();

        phone.setPhoneId(rs.getInt(1));
        phone.setEntityTypeId(rs.getInt(2));
        phone.setClientId(rs.getInt(3));
        phone.setPersonId(rs.getInt(4));
        phone.setAreaCode(rs.getInt(5));
        phone.setPhoneNumber(rs.getInt(6));

        return phone;
    }
}
