package com.astontech.dao.mysql;

import com.astontech.bo.Email;
import com.astontech.dao.EmailDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailDAOImpl extends MySQL implements EmailDAO {
    @Override
    public Email getEmailById(int emailId) {
        Connect();
        Email email = null;
        try {
            String sp = "{Call GetEmail(?,?)}";
            CallableStatement cStm = connection.prepareCall(sp);

            cStm.setInt(1, GET_BY_ID);
            cStm.setInt(2, emailId);

            ResultSet rs = cStm.executeQuery();
            if(rs.next()){
                email = HydrateObject(rs);
            }

        }catch (SQLException sqlEx){
            logger.info(sqlEx);
        }
        return email;
    }

    @Override
    public List<Email> getEmailList() {
        Connect();

        List<Email> emailList = new ArrayList<>();

        try{
            String sp = "{Call GetEmail(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_COLLECTION);
            cStmt.setInt(2, 0);

            ResultSet rs = cStmt.executeQuery();
            while (rs.next()){
                emailList.add(HydrateObject(rs));
            }
        }catch (SQLException sqlEx){
            logger.info(sqlEx);
        }
        return emailList;
    }

    @Override
    public int insertEmail(Email email) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecEmail(?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, INSERT);
            cStmt.setInt(2, 0);
            cStmt.setString(3,email.getEmailAddress());
            cStmt.setInt(4, email.getEmployeeId());
            //cStmt.setInt(5, email.getEmailType().getEntityTypeId());
            cStmt.setInt(5, email.getEntityTypeId());
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
    public boolean updateEmail(Email email) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecEmail(?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);//queryId
            cStmt.setInt(2, email.getEmailId());
            cStmt.setString(3,email.getEmailAddress());
            cStmt.setInt(4, email.getEmployeeId());
            // cStmt.setObject(5, email.getEmailType());
            cStmt.setInt(5, email.getEntityTypeId());

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
    public boolean deleteEmail(int emailId) {
        Connect();
        int id = 0;

        try {
            String sp = "{Call ExecEmail(?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, emailId);
            cStmt.setString(3,"");
            cStmt.setInt(4, 0);
            //cStmt.setObject(5, new Object());
            cStmt.setInt(5, 0);

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

    public static Email HydrateObject(ResultSet rs) throws SQLException{
        Email email = new Email();

        email.setEmailId(rs.getInt(1));
        email.setEmailAddress(rs.getString(2));
        email.setEmployeeId(rs.getInt(3));
        email.setEntityTypeId(rs.getInt(4));
        return email;
    }
}
