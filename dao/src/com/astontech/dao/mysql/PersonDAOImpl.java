package com.astontech.dao.mysql;

import com.astontech.bo.Person;
import com.astontech.dao.PersonDAO;
import common.helpers.DateHelper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl extends MySQL implements PersonDAO {
    // Get Methods
    @Override
    public Person getPersonById(int personId) {
        Connect();

        Person person = null;
        try{
            String sp = "{call GetPerson(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_BY_ID);
            cStmt.setInt(2, personId);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()){
                person = HydrateObject(rs);
            }
        }catch(SQLException sqlEx){
            logger.error(sqlEx);
        }
        return person;
    }

    @Override
    public List<Person> getPersonList() {
        Connect();
        List<Person> personList = new ArrayList<Person>();
        try{
            String sp = "{call GetPerson(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, GET_COLLECTION); // @QueryId = 20;
            cStmt.setInt(2, 0);  // @PersonId = 0; because we return list of rows and second param doesn't matter
            ResultSet rs = cStmt.executeQuery(); // stores all rows in rs variable

            while (rs.next()){// looping rs since its a list of rows
                personList.add(HydrateObject(rs));
            }
        }catch(SQLException sqlEx){
            logger.error(sqlEx);
        }
        return personList;
    }

  // -------------------------------------------------------------------------------------------------------


    // Execute Methods
    @Override
    public int insertPerson(Person person) {
        Connect();
        int id = 0;
        /*
            10(QueryId),                    Index 1     INT
            null(PersonId),                 Index 2     NULL(INT)
           'Mr.(Title)',                    Index 3     STRING
           'Blake'(FirstName),              Index 4     STRING
           'Wittlebe'(LastName),            Index 5     STRING
           '1985-08-13'(CreateDate),        Index 6     DATE
           'Blake'(DisplayFirstName),       Index 7     STRING
            1(IsDeleted),                   Index 8     INT
           'M'(Gender);                     Index 9     STRING(CHAR)
         */
        try {
            String sp = "{Call ExecPerson(?,?,?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, INSERT);// @QueryId is where we will get our LAST_INSERT_ID() from meaning that last row inserted and it will return an int
            cStmt.setInt(2, 0);// Person Id parameter set 0 because sql will ignore it since its identity field but java requires we pass something in at least
            cStmt.setString(3,person.getTitle());// Title
            cStmt.setString(4, person.getFirstName());// FirstName
            cStmt.setString(5,person.getLastName()); // LastName
            cStmt.setDate(6, DateHelper.utilDateToSqlDate(person.getCreateDate()));// CreateDate(We created a helper method to convert java date to sql date, since we are inserting java date in our insert statement and sql would normal give an error)
            cStmt.setString(7, person.getDisplayFirstName());
            cStmt.setInt(8, person.getIsDeleted());
            cStmt.setString(9, person.getGender());

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
    public boolean updatePerson(Person person) {
        Connect();
        int id = 0;
        /*
            10(QueryId),                    Index 1     INT
            null(PersonId),                 Index 2     NULL(INT)
           'Mr.(Title)',                    Index 3     STRING
           'Blake'(FirstName),              Index 4     STRING
           'Wittlebe'(LastName),            Index 5     STRING
           '1985-08-13'(CreateDate),        Index 6     DATE
           'Blake'(DisplayFirstName),       Index 7     STRING
            1(IsDeleted),                   Index 8     INT
           'M'(Gender);                     Index 9     STRING(CHAR)
         */
        try {
            String sp = "{Call ExecPerson(?,?,?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            cStmt.setInt(1, UPDATE);// @QueryId is where we will get our SELECT ROW_COUNT() from meaning that last row inserted and it will return an int(we can check to see if SELECT ROW_COUNT() is greater than 0, meaning our update statement worked!)
            cStmt.setInt(2, person.getPersonId());// Person Id this time we need personId to know what row to update
            cStmt.setString(3,person.getTitle());// Title
            cStmt.setString(4, person.getFirstName());// FirstName
            cStmt.setString(5,person.getLastName()); // LastName
            cStmt.setDate(6, DateHelper.utilDateToSqlDate(person.getCreateDate()));// CreateDate(We created a helper method to convert java date to sql date, since we are inserting java date in our insert statement and sql would normally give an error)
            cStmt.setString(7, person.getDisplayFirstName());
            cStmt.setInt(8, person.getIsDeleted());
            cStmt.setString(9, person.getGender());

            ResultSet rs = cStmt.executeQuery();

            if(rs.next()){
                id = rs.getInt(1);// id =  LAST_INSERT_ID() value and we get it from sql
            }

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }

        // value returned from insert statement is an int from primary key column
        return id > 0; // Method expects boolean and this operation results in a boolean return value and also confirms our update worked if id(ROW_COUNT()) is greater than 0
    }

    @Override
    public boolean deletePerson(int personId) {
        Connect();
        int id = 0;
        try {

            String sp = "{Call ExecPerson(?,?,?,?,?,?,?,?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);

            // Stored Procedure parameters are NOT optional in Java unlike SQL
            cStmt.setInt(1, DELETE);
            cStmt.setInt(2, personId);
            cStmt.setString(3,"");
            cStmt.setString(4, "");
            cStmt.setString(5,"");
            cStmt.setDate(6, new java.sql.Date(0));
            cStmt.setString(7, "");
            cStmt.setInt(8, 0);
            cStmt.setString(9, "");

            ResultSet rs = cStmt.executeQuery();

            if(rs.next()){
                id = rs.getInt(1);
            }

        }catch (SQLException sqlEx){
            logger.error(sqlEx);
        }

        // value returned from insert statement is an int from primary key column
        return id > 0;
    }



    // -------------------------------------------------------------------------------------------------------


    private static Person HydrateObject(ResultSet rs) throws SQLException{
        // Hydrating an object(choosing which columns to pull out in each row)

        Person person = new Person();       //using Person class setters to set property values(columns we want to display)
        person.setPersonId(rs.getInt(1));
        person.setTitle(rs.getString(2));
        person.setFirstName(rs.getString(3));
        person.setLastName(rs.getString(4));
        person.setCreateDate(rs.getDate(5));
        person.setDisplayFirstName(rs.getString(5));
        return person;
    }
}
