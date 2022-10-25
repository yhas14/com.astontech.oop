package com.astontech.dao.mysql;

public class Procedures {
    final static String EXEC_VEHICLE = "{call ExecVehicle(?,?,?,?,?,?,?,?,?,?)}";
    final static String GET_VEHICLE = "{call GetVehicle(?,?)}";

    final static String EXEC_VEHICLE_MODEL = "{call ExecVehicleModel(?,?,?,?)}";
    final static String GET_VEHICLE_MODEL= "{call GetVehicleModel(?,?)}";

    final static String EXEC_VEHICLE_MAKE = "{call ExecVehicleMake(?,?,?,?)}";
    final static String GET_VEHICLE_MAKE = "{call GetVehicleMake(?,?)}";

    final static String EXEC_PHONE = "{call ExecPhone(?,?,?,?,?,?,?)}";
    final static String GET_PHONE =  "{call GetPhone(?,?)}";

    final static String EXEC_PERSON ="{call ExecPerson(?,?,?,?,?,?)}";
    final static String GET_PERSON = "{call GetPerson(?,?)}";

    final static String EXEC_EMPLOYEE = "{call ExecEmployee(?,?,?,?,?,?)}";
    final static String GET_EMPLOYEE = "{call GetEmployee(?,?)}";

    final static String EXEC_EMAIL = "{call ExecEmail(?,?,?,?, ?)}";
    final static String GET_EMAIL = "{call ExecEmail(?,?,?,?, ?)}";
}
