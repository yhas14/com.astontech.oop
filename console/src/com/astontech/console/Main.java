package com.astontech.console;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.sql.*;
import java.util.* ;
/*above import is usually discouraged in
production because every utility is
imported even ones you dont use
*/
import com.astontech.bo.*;
import com.astontech.bo.interfaces.*;
import com.astontech.dao.*;
import com.astontech.dao.mysql.*;
import common.helpers.*;
import org.apache.log4j.Logger;
import sun.reflect.misc.FieldUtil;


import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.io.File;

public class Main {
    // ctr shift - (To collapse code/ code folding)
    // ctr shift + (To expand code)

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
//        LessonHashLAB4();
//        LessonReflectionsAndGeneric(Employee.class);
         blah();
    }
    public static void blah(){
        EntityType eb = new EntityType("work");
        TestingObj(eb);
    }
    public static void TestingObj(EntityType en){
//        System.out.println("hello");
//          EntityType et = new EntityType();
//          et.setEntityTypeId(4);

          Email em = new Email("test@test.com");
          em.setEmailType(en);
          System.out.println(em.getEmailType().getEntityTypeName());
          VehicleModel vmod = new VehicleModel();
//        vmod.setVehicleModelName("Camry");
        VehicleMake vmake = new VehicleMake();
        vmake.setVehicleModel(vmod);
        System.out.println(vmake.getVehicleModel().getVehicleModelName());
    }

    //region JPA Section
        // We'll be using a tool called Maven(used for dependency management like package.JSON
    //endregion

    //region CRUD Operations for Classes

    // Methods for Execute Stored Procedures for Phone class
    // - Insert Person stored Procedure
    private static void LessonDAOPersonInsert(){
        Person person = new Person();

        // Creating Object we'll pass to personDAOImpl.insertPerson()
        person.setFirstName("Tony");
        person.setTitle("Mr.");
        person.setLastName("Stark");
        person.setCreateDate(new Date());
        person.setDisplayFirstName("Tony");

        // This executes an insert statement in our Stored Procedure and will use above object
        PersonDAO personDAO = new PersonDAOImpl();
        int id = personDAO.insertPerson(person);// our insert stored procedure in java expects a person object and it returns an int( we will store the int return value in a variable called id)

        logger.info("New Person Record Inserted.  ID = " + id);
    }
    // - Update Person stored Procedure
    private static void LessonDAOPersonUpdate(){
        PersonDAO personDAO = new PersonDAOImpl();

        // Getting data from sql so we don't have to manually type out our update values
        Person person = personDAO.getPersonById(1);// stored all the values(column values) based on id/row = 10 in variable person
        person.setFirstName("dan");// updated the title column in row 10
        System.out.print(person);
        // Our update statement is ready above and down below will send the statement to sql
        if(personDAO.updatePerson(person)) {// if the if block works it means our update statement worked since personDAO.updatePerson() returns a boolean which means if it works it returns true
            // public boolean update(Person person) --- expects an object of Person and returns a boolean
            logger.info("Person Update Succesfully.");
        }else logger.info("Person Update failed! :(");

        /*
            we see in our console "MySQL Driver Registered" twice
            Because we intially retrieved row 10 with our statement Person person = personDAO.getPersonById(10)
            Then we updated said row using person.setDisplayFirstName("Tony") statement
            Meaning we connected to our database twice.
            Once to retrieve and Once to update
         */
    }

    // - Delete Person stored Procedure
    private static void LessonDAOPersonDelete(){
        PersonDAO personDAO = new PersonDAOImpl();

        if(personDAO.deletePerson(11)){
            logger.info("Person Deleted Successfully.");
        }else logger.info("Person Delete Failed");
    }

    // Methods for Execute Stored Procedures for Employee class
    // - Insert Employee
    private static void LessonDAOEmployeeInsert(){
        Employee employee = new Employee();

        // Creating Object we'll pass to personDAOImpl.insertPerson()
        employee.setCreateDate(new Date());
        employee.setHireDate(new Date());
        employee.setTermDate(new Date());

        // This executes an insert statement in our Stored Procedure and will use above object
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        int id = employeeDAO.insertEmployee(employee);// our insert stored procedure in java expects a person object and it returns an int( we will store the int return value in a variable called id)

        logger.info("New Employee Record Inserted.  ID = " + id);
    }
    // - Update Employee
    private static void LessonDAOEmployeeUpdate(){}
    // - Delete Employee
    private static void LessonDAOEmployeeDelete(){}



    // Insert Phone stored procedure
    private static  void LessonDAOPhoneInsert(){
        Phone phone = new Phone();

        // Creating Object we'll pass to personDAOImpl.insertPerson()
        phone.setEntityTypeId(7);
        phone.setClientId(3);
        phone.setAreaCode(612);
        phone.setPhoneNumber(7654321);
        phone.setPersonId(15);
        phone.setPhoneNumberPost(1);

        // This executes an insert statement in our Stored Procedure and will use above object
        PhoneDAO phoneDAO = new PhoneDAOImpl();
        int id = phoneDAO.insertPhone(phone);// our insert stored procedure in java expects a phone object and it returns an int( we will store the int return value in a variable called id)

        logger.info("New Phone Record Inserted.  ID = " + id);
    }
    //Update Phone stored procedure
    private static  void LessonDAOPhoneUpdate(){
        PhoneDAO phoneDAO = new PhoneDAOImpl();

        // Getting data from sql so we don't have to manually type out our update values
        Phone phone = phoneDAO.getPhoneById(1);
        phone.setAreaCode(952);


        if(phoneDAO.updatePhone(phone)) {

            logger.info("Phone Updated Succesfully.");
        }else logger.info("Phone Update failed! :(");


    }

    // Delete Phone stored procedure
    private static  void LessonDAOPhoneDelete(){
        PhoneDAO phoneDAO = new PhoneDAOImpl();


        if(phoneDAO.deletePhone(58)){
            logger.info("Phone Deleted Successfully.");
        }else logger.info("Phone Delete Failed");
    }

    // Insert Email stored Procedure
    private static void LessonDAOEmailInsert(){
        Email email = new Email();

        // Must have existing values for columns with foreign keys(Max value for EmployeeId is 5 )
        // Below example is getting employeeId value from row 1
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee em= employeeDAO.getEmployeeById(1);

        EntityType et = new EntityType();
        et.setEntityTypeId(4);
        email.setEmailType(et);
        email.setEmployeeId(em.getEmployeeId());
        email.setEmailAddress("Test.test@Astontech.com");
        System.out.println(email.getEmailType().getEntityTypeId());
        EmailDAO emailDAO = new EmailDAOImpl();
        int id = emailDAO.insertEmail(email);
        logger.info("New Email Record Inserted.  ID = " + id);
    }
    // Update Email stored procedure

    private static void LessonDAOEmailUpdate(){
        EmailDAO emailDAO = new EmailDAOImpl();

        // Getting data from sql so we don't have to manually type out our update values
        Email email = emailDAO.getEmailById(16);
        System.out.println(email.getEmailAddress());
        email.setEmailAddress("Sean7@personal.com");
        System.out.println(email.getEmailAddress()+ " "+
        email.getEmailId()+ " "+
        email.getEmployeeId()+ " "+
        email.getEntityTypeId()
        );

        if(emailDAO.updateEmail(email)) {
            logger.info("Email Updated Succesfully.");
        }else logger.info("Email Update failed! :(");

    }
    // Delete Email stored procedure

    private static void LessonDAOEmailDelete(){
        EmailDAO emailDAO = new EmailDAOImpl();

        if(emailDAO.deleteEmail(12)){
            logger.info("Email Deleted Successfully.");
        }else logger.info("Email Delete Failed");
    }


    // Insert Vehicle stored Procedure
    private static void LessonDAOVehicleInsert(){
        Vehicle vehicle = new Vehicle();

        vehicle.setYear(2013);
        vehicle.setLicensePlate(123456);
        vehicle.setVin(0);
        vehicle.setPurchaseDate(new Date());
        vehicle.setVehicleModelId(4);

        VehicleDAO vehicleDAO = new VehicleDAOImpl();
        int id = vehicleDAO.insertVehicle(vehicle);

        logger.info("New Vehicle Record Inserted.  ID = " + id);
    }
    // Update Vehicle stored procedure
    //java.sql.SQLDataException: Unsupported conversion from TIMESTAMP to java.lang.Integer  <-------------------------------------------------------
    private static void LessonDAOVehicleUpdate(){
        VehicleDAO vehicleDAO = new VehicleDAOImpl();

        // Getting data from sql so we don't have to manually type out our update values
        Vehicle vehicle = vehicleDAO.getVehicleById(19);
        System.out.println(vehicle);
        vehicle.setYear(2018);

        if(vehicleDAO.updateVehicle(vehicle)) {

            logger.info("Vehicle Updated Succesfully.");
        }else logger.info("Vehicle Update failed! :(");
    }
    // Delete Vehicle stored procedure
    private static void LessonDAOVehicleDelete(){
        VehicleDAO vehicleDAO = new VehicleDAOImpl();

        if(vehicleDAO.deleteVehicle(13)){
            logger.info("Vehicle Deleted Successfully.");
        }else logger.info("Vehicle Delete Failed");
    }



    // Insert VehicleMake stored Procedure
    private static void LessonDAOVehicleMakeInsert(){
        VehicleMake vehicleMake = new VehicleMake();

        // Creating Object we'll pass to personDAOImpl.insertPerson()
        vehicleMake.setVehicleMakeName("Acura");
        vehicleMake.setCreateDate(new Date());

        // This executes an insert statement in our Stored Procedure and will use above object
        VehicleMakeDAO vehicleMakeDAO = new VehicleMakeDAOImpl();
        int id = vehicleMakeDAO.insertVehicleMake(vehicleMake);// our insert stored procedure in java expects a phone object and it returns an int( we will store the int return value in a variable called id)

        logger.info("New VehicleMake Record Inserted.  ID = " + id);
    }
    // Update VehicleMake stored procedure
    private static void LessonDAOVehicleMakeUpdate(){
        VehicleMakeDAO vehicleMakeDAO = new VehicleMakeDAOImpl();

        // Getting data from sql so we don't have to manually type out our update values
        VehicleMake vehicleMake = vehicleMakeDAO.getVehicleMakeById(6);
        vehicleMake.setVehicleMakeName("Dodge");
        vehicleMake.setCreateDate(new Date());

        if(vehicleMakeDAO.updateVehicleMake(vehicleMake)) {

            logger.info("Vehicle Make Updated Succesfully.");
        }else logger.info("Vehicle Make Update failed! :(");
    }
    // Delete VehicleMake stored procedure  <--------------------- java.sql.SQLIntegrityConstraintViolationException: Cannot delete or update a parent row: a foreign key constraint fails
    private static void LessonDAOVehicleMakeDelete(){
        VehicleMakeDAO vehicleMakeDAO = new VehicleMakeDAOImpl();

        if(vehicleMakeDAO.deleteVehicleMake(2)){
            logger.info("Vehicle Deleted Successfully.");
        }else logger.info("Vehicle Delete Failed");
    }


    // Insert VehicleModel stored Procedure
    private static void LessonDAOVehicleModelInsert(){
        VehicleModel vehicleModel = new VehicleModel();

        // Creating Object we'll pass to personDAOImpl.insertPerson()
        vehicleModel.setVehicleModelName("TL");
        vehicleModel.setVehicleMakeId(6);



        // This executes an insert statement in our Stored Procedure and will use above object
        VehicleModelDAO vehicleModelDAO = new VehicleModelDAOImpl();
        int id = vehicleModelDAO.insertVehicleModel(vehicleModel);// our insert stored procedure in java expects a phone object and it returns an int( we will store the int return value in a variable called id)

        logger.info("New VehicleModel Record Inserted.  ID = " + id);
    }
    // Update VehicleModel stored procedure
    private static void LessonDAOVehicleModelUpdate(){
        VehicleModelDAO vehicleModelDAO = new VehicleModelDAOImpl();

        // Getting data from sql so we don't have to manually type out our update values
        VehicleModel vehicleModel = vehicleModelDAO.getVehicleModeleById(10);
        vehicleModel.setVehicleModelName("FireBird");


        if(vehicleModelDAO.updateVehicleModel(vehicleModel)) {

            logger.info("VehicleModel Updated Succesfully.");
        }else logger.info("VehicleModel Update failed! :(");
    }
    // Delete VehicleModel stored procedure
    private static void LessonDAOVehicleModelDelete(){
        VehicleModelDAO vehicleModelDAO = new VehicleModelDAOImpl();

        if(vehicleModelDAO.deleteVehicleModel(9)){
            logger.info("Vehicle Model Deleted Successfully.");
        }else logger.info("Vehicle Model Delete Failed");
    }
    // endregion

    //region Java Advance Concepts
        // High level overview of Reflections and Generics
        private static <T> void LessonReflectionsAndGeneric(Class <T> genericClass){
            // Reflection of what inside a class(like methods and properties, useful for if you don't know whats inside a class or what package it belongs to. For example if you import a class from an outside source)

            logger.info("Simple Name: " + genericClass.getName());
            logger.info("Simple Name: " + genericClass.getSimpleName());
            for(Field field : genericClass.getDeclaredFields()){ // java.lang.reflect.Field returns Properties and Property data types
                logger.info("Field: " + field.getName() + " - Type: " + field.getType()); // returns Properties in Employee and their data types
            }
            for(Method method : genericClass.getDeclaredMethods()){// java.lang.reflect.methods returns Methods
                logger.info("Method : " + method.getName() + " <-- "+ "return types: " + method.getReturnType());
            }
            List<Integer> in = new ArrayList<>(); // all methods you attach to 'in' variable comes from reflect library

            // in web services we can get an object back and use above strategy to figure out what the object is made of
            // Class ObjVariable = webServerObject.class ( then we can check ObjVariable and see whats inside the object) or we can use below syntax to make our method more dynamic if we want to check classes(obj) we import

            /*
                We can make our LessonReflection more dynamic by giving it a generic parameter
                Generic Syntax:
                Step 1:
                private static <T> void LessonReflection(Class <T> genericClass){}
                Step 2:
                LessonReflection(Employee.class) // Wherever were calling this method we pass the obj we want to check in as an argument


                <T> -- General Practice T = Type
                You're not forced to call it <T> you can name it whatever <blah> but it's good practice to call it T
            */
        }

        // Boxing and unboxing(converting a primitive to an object or an object to a primitive)

        private static void LessonBoxUnBoxCast(){
            //notes  Boxing = act of converting a value type to a ref type
            //notes  Unboxing = converting a ref type to a value type.

            //notes boxing
            int x = 10;
            Object o = x;

            //notes unboxing(this is casting, particularly 'explicit' casting)
            int y = (int) o;
            logger.info(y);

            //notes: implicit castin
            int i = 100;
            double d = i;

            double db = 1.92;
            //int in = db; this will fail(cant store double inside int)

            //notes:  explicit casting
            int in = (int) db;
            logger.info(in); // we go from 1.92 to 1 since we stored a double in an int we lose the numbers after the decimal

        }

        private static void LessonSerialization(){
            // serialization is converting an object into a stream of bytes
            // A way for one application to communicate with another
            // to serialize an object you need to mark it with a marker interface called serializable
            // we implement serializable interface and created a ToString method


            // Step 1: get an object from DB
            PersonDAO personDAO = new PersonDAOImpl();
            Person person = personDAO.getPersonById(1); // serialize the object from db in step 2
            // Step 2: serialize to a text file
            try {
                FileOutputStream fileOut = new FileOutputStream("./ser_person.txt");
                ObjectOutputStream out = new ObjectOutputStream(fileOut); // this does the converting
                out.writeObject(person);
                out.close();
                fileOut.close();
                logger.info("Object serialized and written to file: ./ser_person.txt" );
                logger.info("Serialized Object: " + person.ToString());
            }catch (IOException ioEx){
                logger.error(ioEx);
            }
        }

        private static void LessonDeserialization(){
            Person person = null;
            try {
                FileInputStream fileIn = new FileInputStream("./ser_person.txt");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                person = (Person) in.readObject();
                in.close();
                fileIn.close();

            }catch (FileNotFoundException fileEx){
                logger.error(fileEx);
            }catch (IOException ioEx){
                logger.error(ioEx);
            }catch (ClassNotFoundException clzEx){
                logger.error(clzEx);
            }

            logger.info("Deserialized Object: " + person.ToString());
        }


        private static void LessonRecursion(int recursioncount){
            logger.info("Recursive Count = " + recursioncount);
            if(recursioncount > 0){
                LessonRecursion(5 - 1);
            }
        }


        private static void LessonRecursionComplex(File dir){

            long DirectorySize = 0;
            int fileCounter = 0;
            try {
                 File[] files = dir.listFiles();
                 for(File file : files){
                         if(file.isDirectory()){
                              // notes recursion happens here
                             logger.info("directory Name : " + file.getName());
                             logger.info("Directory Path: "  + file.getCanonicalPath());
                         }else {
                             fileCounter++;
                             String extension = "";

                             int i = file.getName().lastIndexOf('.');
                             if (i > 0) {
                                 extension = file.getName().substring(i+1);
                             }

                             logger.info("  file Name: " + file.getName());
                             logger.info("  file Type: " + extension);
                             logger.info("  file Size: " + file.length());
                             logger.info("  file Path: " + file.getCanonicalPath());
                             DirectorySize += file.length();
                         }
                 }
            }catch (Exception ioEx){
                logger.error(ioEx);
            }
            logger.info("There are " + fileCounter + " files in this directory");

            // logger.info("Directory Size: " + DirectorySize);
            /*
                    Step 1:
                    Retrieve:
                    | Directory |
                    Directory Name      File.getName() in if(file.isDirectory)
                    Directory Size      DirectorySize += file.length in else block
                    Number Of Files     Counter in the else block
                    Directory Path      File.getCanonicalPath()


                    | File |
                    File Name       File.getName() in else block

                    File Type       |String extension = "";                            |
                                    |int i = file.getName().lastIndexOf('.');          |
                                    |if (i > 0) {                                      |
                                    ||     extension = file.getName().substring(i+1);  |
                                    |}                                                 |


                    File Size       File.length(); in else block
                    File Path       File.getCanonicalPath()

        */
            /*
            Make sure for each directory the file table has the same id in the foreign key column
            maybe something like

            file.setDirId(Directory.getDirId());

            Once data is retrieved and populate db above
            Step 2: Output Data meeting below conditions

            Display Directory with most files
            Display Directory largest in size
            Display 5 largest files in size
            Display all files of a certain type
            Clear DB and start over
            Exit
            */
        }
    //endregion

    //region MENUS for all DAO Implementaions
    // Menus for all our DAO implementations
    private static void PersonMenu(){
        PhoneDAO phoneDAO = new PhoneDAOImpl();

        List<Phone> phoneList = phoneDAO.getPhoneList();

        System.out.println("===========");

        for(Phone phone : phoneList){
            System.out.println("(" + phone.getAreaCode() + ")" + phone.getPhoneNumber());
        }

        System.out.println("===========");

        //Prompting user
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select a phone number");
        String phoneId = scan.nextLine();

        // Returning user input(row) with Area Code, Phone Number, and EntityTypeId
        Phone phoneDetails = phoneDAO.getPhoneById(Integer.parseInt(phoneId));
        System.out.println("---- Phone DETAILS ----");
        System.out.println("Area Code : " + phoneDetails.getAreaCode());
        System.out.println("Phone Number " + phoneDetails.getPhoneNumber());
        System.out.println("PersonID: " + phoneDetails.getEntityTypeId());
    }
    private static void EmployeeMenu(){
        // region CREATE MENU
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        List<Employee> employeeList = employeeDAO.getEmployeeList();

        System.out.println("==================");
        for(Employee employee : employeeList){
            System.out.println(employee.getEmployeeId() + ") " + employee.getPersonId());
        }

        System.out.println("==================");

        //endregion

        //region PROMPT USER
        Scanner reader = new Scanner(System.in);
        System.out.println("Please Select an Employee from the list: ");
        String employeeId = reader.nextLine();

        //region GET PERSON DETAILS
        Employee employeeDetail = employeeDAO.getEmployeeById(Integer.parseInt(employeeId));

        System.out.println("---- Employee DETAILS ----");
        System.out.println("Hire Date: " + employeeDetail.getHireDate());
        System.out.println("EmployeeID " + employeeDetail.getEmployeeId());
        System.out.println("PersonID: " + employeeDetail.getPersonId());
        System.out.println("Term Date: " + employeeDetail.getTermDate());
    }
    private static void EmailMenu(){
      EmailDAO emailDAO = new EmailDAOImpl();

      List<Email> emailList = emailDAO.getEmailList();

        System.out.println("===========");
        for(Email email  : emailList){
            System.out.println("Email ID) " + " " + email.getEmailId() + " " +  email.getEmailAddress() + "  Email Address) " + email.getEmailAddress());
        }

        System.out.println("===========");

        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose an email");
        String emailId = scan.nextLine();

        Email emailDetails = emailDAO.getEmailById(Integer.parseInt(emailId));
        System.out.println("------ Email Details ----------");
        System.out.println("Email ID  : " + emailDetails.getEmailId());
        System.out.println("Email Address : " + emailDetails.getEmailAddress());

    }
    private static void VehicleMenu(){
        VehicleDAO vehicleDAO = new VehicleDAOImpl();

        List<Vehicle> vehicleList = vehicleDAO.getVehicleList();

        System.out.println("===========");
        for(Vehicle vehicle  : vehicleList){
            System.out.println("Vehicle ID) " + " " + vehicle.getVehicleId() + " " + vehicle.getVehicleModelId());
        }

        System.out.println("===========");

        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose a Vehicle");
        String vehicleId = scan.nextLine();

        Vehicle vehicleDetails = vehicleDAO.getVehicleById(Integer.parseInt(vehicleId));
        System.out.println("------ Vehicle Details ----------");
        System.out.println("Vehicle ID  : " + vehicleDetails.getVehicleId());
        System.out.println("Vehicle Year: " + vehicleDetails.getYear());


    }
    private static void VehicleMakeMenu(){
        VehicleMakeDAO vehicleMakeDAO = new VehicleMakeDAOImpl();

        List<VehicleMake> vehicleMakeList = vehicleMakeDAO.getVehicleMakeList();

        System.out.println("===========");
        for(VehicleMake vehicleMake  : vehicleMakeList){
            System.out.println("Vehicle ID) " + " " + vehicleMake.getVehicleMakeId() + " " + vehicleMake.getVehicleMakeName());
        }

        System.out.println("===========");

        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose a Vehicle Make");
        String vehicleMakeId = scan.nextLine();

        VehicleMake vehicleMakeDetails = vehicleMakeDAO.getVehicleMakeById(Integer.parseInt(vehicleMakeId));
        System.out.println("------ Vehicle Make Details ----------");
        System.out.println("Vehicle Make ID  : " + vehicleMakeDetails.getVehicleMakeId());
        System.out.println("Vehicle Make Name: " + vehicleMakeDetails.getVehicleMakeName());

    }
    private static void VehicleModelMenu(){
        VehicleModelDAO vehicleModelDAO = new VehicleModelDAOImpl();

        List<VehicleModel> vehicleModelList = vehicleModelDAO.getVehicleModelList();

        System.out.println("===========");
        for(VehicleModel vehicleModel  : vehicleModelList){
            System.out.println("Vehicle ID) " + " " + vehicleModel.getVehicleModelId() + " " + vehicleModel.getVehicleModelName());
        }

        System.out.println("===========");

        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose a Vehicle");
        String vehicleModelId = scan.nextLine();

        VehicleModel vehicleModelDetails = vehicleModelDAO.getVehicleModeleById(Integer.parseInt(vehicleModelId));
        System.out.println("------ Vehicle Details ----------");
        System.out.println("Vehicle Model ID  : " + vehicleModelDetails.getVehicleModelId());
        System.out.println("Vehicle Model Name: " + vehicleModelDetails.getVehicleModelName());
    }


    private static void LessonDAO(){
        // region CREATE MENU

        // not sure whats happening below ( instanciating PersonDAOImpl and set it equal to PersonDAO interface?)
         PersonDAO personDAO = new PersonDAOImpl();

        // not sure why our interface is being used below since PersonDAOImpl is the one with the resultSet(rs)?
        // Looks like we are getting resultSet from PersonDAOImpl since personDAO variable is set to an instance of PersonDAOImpl above
         List<Person> personList = personDAO.getPersonList(); // right here PersonDAO interface is used instead of PersonDAOImpl(actually personDAO looks like a variable containing PersonDAOImpl class)
        // it could mean above is actually PersonDAOImpl.getPersonList() which would make more sense

        System.out.println("==================");
        for(Person person : personList){// Looping through personList since it now has PersonDAOImpl which is connected to our server and returns a list of all rows
            System.out.println(person.getPersonId() + ") " + person.getLastName() + ", " + person.getFirstName());
            // above we chose to output those three columns from our list of rows which contain more columns
        }

        System.out.println("==================");

        //endregion

        //region PROMPT USER
            Scanner reader = new Scanner(System.in);
            System.out.println("Please Select a Person from list: ");
            String personId = reader.nextLine(); // creating a variable which holds user input
        //endregion

        //region GET PERSON DETAILS
        Person personDetail = personDAO.getPersonById(Integer.parseInt(personId)); // passing user input into personDetail variable which holds our PersonDAOImpl class( so it basically like passing input into PersonDAOImpl class);

        System.out.println("---- PERSON DETAILS ----");
        System.out.println("Full Name: " + personDetail.GetFullName());
        System.out.println("Title " + personDetail.getTitle());
        System.out.println("LastName: " + personDetail.getLastName());


        // PersonDAOImpl personblah = new PersonDAOImpl();  we use PersonDAOImpl on the left and right of equal sign(Typically you want interface on left of equal and class on right of equal for some reason)
        // see what happens when you just try to instantiate the PersonDAOImpl class like above? it doesn't work(interface and class are required)
        // Which means we are forced to instantiated to our PersonDAO interface like below

        PersonDAO personblah = new PersonDAOImpl();// interface on left and class on right of equal sign(Interface - PersonDAO || Class - PersonDAOImpl() )
        Person personblah2 = personblah.getPersonById(1); // now the instance of the interface/class we can use a method inside the class and pass into its parameter(getPersonById method)
        System.out.println(personblah2.GetFullName());// now we can choose which columns to return based on row 2 we passed into getPersonById method
        System.out.println(personblah2.getTitle());
        System.out.println(personblah2.getFirstName());

        //endregion
    }
    private static void LessonGetStoredProc(){
        // have to connect everytime you want to do sql commands
        Connection conn = LessonDBConnection(); // connecting to sql server

        try{
            String sp = "{call GetPerson(?,?)}";// stored procedure syntax(call dbo.getPerson)
            CallableStatement cStmt = conn.prepareCall(sp);// preparing to call a stored procedure

            cStmt.setInt(1, 20); // @queryId is parameter 1 || @queryId = 10 select one row || @QueryId = 20 select all rows
            cStmt.setInt(2, 5); // @PersonId is parameter 2
            ResultSet rs = cStmt.executeQuery(); // storing result in rs variable

            while (rs.next()){ // mainly for @QueryId = 20 so we loop through all rows
                /*
                 rs.get --> must specify data type of column and they are in a row
                 Meaning PersonId is first column so must use rs.getInt( 1 )
                 you can pick specific column if you know its index
                 meaning if I want DisplayFirstName i know its a string and is the 6th column
                 rs.getString( 6 ) is what I would do
                 */
                /*
                    PersonId          index 1  INT
                    Title             index 2  VARCHAR
                    FirstName         index 3  VARCHAR
                    LastName          index 4  VARCHAR
                    BirthDate         index 5  DATETIME
                 */

                logger.info(rs.getInt(1) + ": " + rs.getString(2) + rs.getString(3) +" "+  rs.getString(4));
            }
        }catch (SQLException sqlEX){
            logger.error(sqlEX);
        }
    }

    private static void LessonExecQuery(){
        Connection conn = LessonDBConnection();
        try {
            Statement statement = conn.createStatement();
            String sql= "SELECT PersonId, FirstName,LastName FROM `Aston Engineer`.Person";

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                int personId = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                logger.info(personId + ": " + firstName + " "+ lastName );
            }
            conn.close();

        }catch (SQLException sqlEX){
            logger.error(sqlEX);
        }

    }
    private static Connection LessonDBConnection(){
        // variables for url aka connection string
        String dbHost = "localhost";
        String dbName = "Aston Engineer";
        String dbUser = "consoleUser";
        String dbPass = "yHassan2323!";
        String useSSL = "false";
        String procBod = "true";

        // Here we are testing to see if driver is set up
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException ex){
            logger.error("MYSQL Driver not found" + ex);
            return null;
        }
        logger.info("MySQL Driver Registered.");
        Connection connection = null;

        // Here we are actually connecting to our sql server
        try{
            connection = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":3306/" +dbName + "?useSSL=" + useSSL + "&noAccessToProcedureBodies=" + procBod + "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", dbUser, dbPass);
        }catch (SQLException ex){
            logger.error("Connection failed " + ex);
            return null;
        }

        if(connection != null){
            logger.info("Successfully connected to mySQL database");
            return connection;
        }else {
            logger.info("Connection failed!");
            return null;
        }

    }
    //endregion

    //region ReverseChar, HelpersLab, Sport Interface, ComparetoLab
    private static void reverseCharLAB5(){
        Reversed reversed = new Reversed();
        reversed.setString("Yahya");
        System.out.println(reversed.getString());
    }
    private static void LessonStringHelperLAB5(){
        Person p1 = new Person();
        p1.setPersonId(1434);
        System.out.println(p1.toString()); // StringHelper toString in Person class
        if(p1.toString().equals("1434")){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
        System.out.println(StringHelper.toUppercase("Yahya")); // StringHelper # 2
        System.out.println(StringHelper.smileyFace("Hello")); //  StringHelper # 3

        System.out.println();
        System.out.println(MathHelper.addition(2, 3));         // MathHelper addition
        System.out.println(MathHelper.subtraction(5,4));      // MathHelper Subtraction
        System.out.println((MathHelper.multiplication(3,4)));// MathHelper multiplication
    }
    private static void SportInterfaceTestLAB5(){
        Basketball bball = new Basketball();// Basketball instance
        bball.setScore("119 - 114");
        bball.setTeamName("Timberwolves");
        bball.setWinners("Timberwolves");

        Soccer soccer = new Soccer("Real Madrid","2 - 1", "Real Madrid");
        SportInterfaceLAB5(soccer);
        SportInterfaceLAB5(bball);
    }
    private static void SportInterfaceLAB5(Sports sports){
        System.out.println("=============");
        System.out.println("Score : " + sports.Score());
        System.out.println("TeamName : " + sports.TeamName());
        System.out.println("Winner: " + sports.Winners());
        System.out.println("Quarters: " + sports.Quarters());
        System.out.println("Number of Players: " + sports.NumberOfPlayers());
        System.out.println("Foul Rule: " + sports.Fouls());
    }
    private static void usingComparetoLAB5(){
        //region Compare to Person object
        Person person1 = new Person(1);
        Person person2 = new Person(2);
        System.out.println(person1.compareTo(person2));
        int result = person1.compareTo(person2);

        if (result < 0){
            System.out.println(person1.getPersonId() + " comes before " + person2.getPersonId());
        }else if(result > 0){
            System.out.println(person2.getPersonId() + " comes before " + person1.getPersonId());
        }else
            System.out.println(person1.getPersonId() + " is equal to " + person2.getPersonId());
        //endregion

        //region Compare to Vehicle Object
        Vehicle veh1 = new Vehicle(2013);
        Vehicle veh2 = new Vehicle(2014);


        int result2 = veh1.compareTo(veh2);

        if (result2 < 0){
            System.out.println(veh1.getYear() + " comes before " + veh2.getYear());
        }else if(result2 > 0){
            System.out.println(veh2.getYear() + " comes before " + veh1.getYear());
        }else
            System.out.println(veh1.getYear() + " is equal to " + veh2.getYear());
        //endregion

    }
    //endregion

    //region JAVA OOP Module Methods 21 Methods
    private static void LessonInterfaceTest(){
        Site MN010 = new Site();
        MN010.setSiteName("MN010");
        MN010.setCoffeeMachines(2);
        MN010.setConferenceRooms(1);
        MN010.setCubicles(8);
        MN010.setOffices(6);
        MN010.setTrainingDesk(36);

        Home BipsHouse = new Home();
        BipsHouse.setAddress("1 Main St.");
        BipsHouse.setOwner(new Employee("Bipin", "Butala"));

        LessonInterfaces(MN010);
        LessonInterfaces(BipsHouse);
    }
    private static void LessonInterfaces(ILocation Ilocation){
        System.out.println("================");
        System.out.println("Location Name: " + Ilocation.getLocationName());
        System.out.println("Can Have Meetings: " + Ilocation.canHaveMeetings());
        System.out.println("Number of Workspace: " + Ilocation.NumberOfWorkspaces());
        System.out.println("Has Coffee: " + Ilocation.hasCoffee());


    }
    public static void LessonHashLAB4(){
        Hashtable<Integer, String> firstHashTable = new Hashtable<>();
        firstHashTable.put(0,"One does not simply understand Java");
        firstHashTable.put(1, "Java?" + "I prefer espresso");
        firstHashTable.put(2, "Programming pro tip" +" | "+ "Code java underwater, so no one can see you crying");
        firstHashTable.put(3,"Why do java devs wear glasses? " + "\n" + "Because They can't C#");


        for (Integer key : firstHashTable.keySet()) {
            System.out.println(firstHashTable.get(key));
        }


        HashMap<Integer, String> firstHashMap = new HashMap<>();
        firstHashMap.put(0, "if(String.valueof(condition).equals(true))");
        firstHashMap.put(1, "You know, I'm something of a programmer myself");
        firstHashMap.put(2, "When you write 10 line of code without searching google :)");
        firstHashMap.put(3, "Created a char in java | named it zard");

        for (Integer key : firstHashMap.keySet()) {
            System.out.println(firstHashMap.get(key));
        }


        HashSet<String> firstHashSet = new HashSet<>();

        // Adding to HashSet
        firstHashSet.add("Documentation? " + " Where were going, we dont need documentation");
        firstHashSet.add("When a java developer switches to C#  " + "\n"+ "Well that sounds like java with extra steps");

        for (String s : firstHashSet){
            System.out.println(s);
        }

    }
    public static void LessonCollectionsLAB(){
       /* List<Vehicle> vehicles= new ArrayList<Vehicle>();

        Vehicle veh1 = new Vehicle(1, 2013, "18uub8f123", "White", true, 24312, "January 1, 1970, 00:00:00 GMT", 1);
        vehicles.add(veh1);
        vehicles.add(new Vehicle(2, 2015, "99u434f123", "Black", true, 24312, "8/17/2018", 2));
        vehicles.add(new Vehicle(3, 2018, "2343gjkl123", "Blue", true, 24312, "8/17/2018", 3));
        vehicles.add(new Vehicle(4, 2020, "67568uil123", "Red", true, 24312, "8/17/2018", 4));
        vehicles.add(new Vehicle(5, 2022, "000db8f123", "Purple", true, 24312, "8/17/2018", 5));

        System.out.println(vehicles.get(0).getColor());
        for(int i = 0; i < vehicles.size(); i++){
            System.out.println(vehicles.get(i).toString());
        }

*/
}
    private static void LessonObjectsLAB(){
        //First Custom Method, retrieve clientName
        Client firstClient = new Client(1, "Best Buy");
        System.out.println(firstClient.ClientName());

        // Second Custom Method to check if email exist
        Email firstEmail = new Email("yahya@astontech.com");
        System.out.println(firstEmail.checkEmail());

        // Third Custom Method to return Vehicle Make
        VehicleMake make1 = new VehicleMake(1,"Acura");
        System.out.println(make1.makeExist());

        //Fourth Custom Method returns EntityId
        Entity firstEntity = new Entity(2, "Phone");
        System.out.println(firstEntity.entityId());

        //Fifth Custom Method greeting From LoyaltyCompany
        LoyaltyCompany companyGreeting = new LoyaltyCompany(4, "Best Buy");
        System.out.println(companyGreeting.greeting());

        //Create Instances of each of your new classes, set an attribute and output to the console.
        // Address
        Address address1 = new Address(1, 2);
        System.out.println(address1.getAddressId());

        //Client
        Client client1 = new Client(3, "United Healthgroup");
        System.out.println(client1.getClientName());

        //ClientContact
        ClientContact cContact = new ClientContact(4,1);
        System.out.println(cContact.getClientContactId());

        //Email
        Email em1 = new Email("Yahya.Hassan@astontech.com");
        System.out.println(em1.getEmailAddress());

        // Employee
        Employee emp1 = new Employee("Yahya", "Hassan");
        System.out.println(emp1.GetFullName());

        //EmployeeProject
        EmployeeProject empP = new EmployeeProject(1,4);
        System.out.println(empP.getEmployeeProjectId());

        //Entity
        Entity e1 = new Entity(2, "LoyaltyCompany");
        System.out.println(e1.getEntityName());

        //EntityType
        EntityType et = new EntityType("Personal");
        System.out.println(et.getEntityTypeName());

        //LoyaltyAccount
        LoyaltyAccount lA = new LoyaltyAccount(2, "14424");
        System.out.println(lA.getMemberNumber());

        //LoyaltyCompany
        LoyaltyCompany lC = new LoyaltyCompany(2, "Hertz");
        System.out.println(lC.greeting());

        //Person
        Person p1 = new Person("Yahya");
        System.out.println(p1.GetFullName());

        //Phone
        Phone phone = new Phone(1, 612);
        System.out.println(phone.getAreaCode());

        //Project
        Project proj = new Project(5, "Project1");
        System.out.println(proj.getProjectName());

        //ProjectStatus
        ProjectStatus ps = new ProjectStatus(2, "Success!");
        System.out.println(ps.getNotes());

        //Review
        Review re1 = new Review(32);
        System.out.println(re1.getReviewId());

        //ReviewData
        ReviewData rd = new ReviewData(4, "Needs to speed up project completion");
        System.out.println(rd.getReviewDataValue());

        //Training
        Training tn = new Training(7, "PPE");
        System.out.println(tn.getTrainingName());

        //TrainingData
        TrainingData td = new TrainingData(7, "75% success Rate");
        System.out.println(td.getTrainingDataValue());

        //Vehicle
        Vehicle v1 = new Vehicle(8, 2014);
        System.out.println(v1.getYear());

        //VehicleMake
        VehicleMake vm = new VehicleMake(3, "TL");
        System.out.println(vm.getVehicleMakeName());

        //VehicleModel
        VehicleModel vMod = new VehicleModel(3, "Acura TL");
        System.out.println(vMod.getVehicleModelName());

        //VehicleStatus
        VehicleStatus vs = new VehicleStatus(3, 2);
        System.out.println(vs.getVehicleId() + " " + vs.getVehicleStatusId());
    }
    private static void LessonMethods(){
//        Employee constructorEmployee = new Employee("Bipin", "Batula");
//        System.out.println(constructorEmployee.getFirstName() + " " + constructorEmployee.getLastName());
//
//        Employee constructor2Employee = new Employee("Simmer");
//        System.out.println(constructor2Employee.getLastName());

        Employee employeeJames = new Employee("James","McRoberts");
        System.out.println(employeeJames.GetFullName());
        System.out.println(employeeJames.getLastName());
    }
    private static void LessonLogging(){
        // notes: Levels of Logging
        logger.debug("This is a DEBUG log message");
        logger.info("This is a INFO log message");
        logger.warn("This is a WARN log message");
        logger.error("This is a ERROR log message");
        logger.fatal("This is a FATAL log message");

    }
    private static void LessonValueVsRef(){
        //notes: reference types
        Employee firstEmp = new Employee();
        firstEmp.setFirstName("Bipin");

        Employee secondEmp = firstEmp;
        firstEmp.setFirstName("Dean");
        System.out.println(firstEmp.getFirstName());
        //notes: value types
        int firstInt = 10;
        int secondInt = firstInt;

        firstInt = 20; // updating firstInt won't affect secondInt

        System.out.println(secondInt);
    }
    private static void LessonHash() {
        //notes: Key-Value pairs, Value list

        //todo: HashTable
        /*
            1) does NOT allow null for either key or value
            2) synchronized, thread safe, but performance is decreased
         */
        System.out.println("HASH TABLE");
        Hashtable<Integer, String> firstHashTable = new Hashtable<>(); //instance of hashtable class
        firstHashTable.put(2, "Polymorphism");
        firstHashTable.put(3, "Abstraction");
        firstHashTable.put(1, "inheritance");
        firstHashTable.put(4, "Encapsulation");

        // Way 1 to retrieve from HashTable
        System.out.println(firstHashTable.get(3));
        // Way 2 using for of loop/ Enhanced for loop to be exact
        for (Integer key : firstHashTable.keySet()) {
            System.out.println("Key : " + key + "- value: " + firstHashTable.get(key));
        }
        // it prints from most recently add(Encapsulation) to last added(Inheritance)

        /*  Error if you try to put null value in key or value
           !!!  firstHashTable.put(5, null);  throws NullPointerException (NPE)   !!!!
            Exception in thread "main" java.lang.NullPointerException
        */

        System.out.println("----------------");

        //todo HashsMap
        /*
            1) DOES allow null for either key or value
            2) un-synchronized, NOT thread safe, better performance
         */
        System.out.println("---HASH MAP---");
        HashMap<Integer, String> firstHashMap = new HashMap<>();
        firstHashMap.put(1, "inheritance");
        firstHashMap.put(2, "Polymorphism");
        firstHashMap.put(3, "Abstraction");
        firstHashMap.put(4, "Encapsulation");

        // Way 1 to retrieve from HashMap
        System.out.println(firstHashMap.get(3));

        // Way 2 using for of loop/ Enhanced for loop to be exact
        // UNLIKE Hashtable, HASHMAP prints out in the correct order from (Inheritance) to (Encapsulation)
        /*
            Actually it goes based off integer value in key(1 is first and 4 last) so if we made the last value
            have a key of one, then it would be the first printed (1, "Encapsulation").
            Documentation: The elements are returned in no particular order. So order is unspecified
         */
        for (Integer key : firstHashMap.keySet()) {
            System.out.println("Key : " + key + "- value: " + firstHashMap.get(key));
        }




        /*
            HASHMAP also accepts null values
         */

        System.out.println("----------------");


        //todo Hashset

        /*
            1) built in mechanism for duplicates
            2) used for where you want to maintain a unique list
         */
        System.out.println("---HASH SET---");
        // notes: Instantiating HashSet
        HashSet<String> firstHashSet = new HashSet<>();

        // Adding to HashSet
        firstHashSet.add("Inheritance");
        firstHashSet.add("Encapsulation");


        for (String s : firstHashSet){
            System.out.println(s);
        }

       if(firstHashSet.contains("Inheritance")){
           System.out.println("Value exist!");
       }else
           System.out.println("Value does not exist!");

        System.out.println("----------------");



    }
    private static void LessonPolymorphism(){
        // compile time polymorphism Overload

        // runtime polymorphism Override
        BaseBO baseBO = new BaseBO();
        System.out.println(baseBO.test_method() + " Base ");

        EntityType entityType = new EntityType();
        System.out.println(entityType.test_method() + " Entity");
    }
    private static void LessonInstanceVsStatic(){
            //MathHelper instanceOfMathClass = new MathHelper();

            System.out.println(MathHelper.E);
            System.out.println(MathHelper.PI);




    }
    private static void LessonComplexProperties(){
        EntityType emailWorkType = new EntityType("Work");
        Email myEmail = new Email("Bipin@bip.com");
        myEmail.setEmailType(emailWorkType);

        System.out.println(myEmail.getEmailAddress() + " Type:" + myEmail.getEmailType().getEntityTypeName());

        // Collection/ List of complex(nested) objects as property
        Employee myEmployee = new Employee();
        myEmployee.getEmails().add(new Email("Test@test.com"));
        myEmployee.getEmails().add(new Email("dan@test.com"));
        myEmployee.getEmails().add(new Email("jason@test.com"));

        for(Email email : myEmployee.getEmails()){
            System.out.println(email.getEmailAddress());
        }
    }
    private static void LessonCollections(){
        // List<T> - generic type 'T'
        List<Employee> employeeList = new ArrayList<Employee>();

        Employee emp1 = new Employee("Dan", "Simmer");
        Employee emp2 = new Employee("James", "McRoberts");
        Employee emp3 = new Employee("Sean", "Nilsen");

        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);

        employeeList.add(new Employee("Adrian", "Ratanyake"));
        employeeList.add(new Employee("John", "Doe"));

        for(Employee e : employeeList){
            System.out.println(e.GetFullName());
        }

        Employee Daniel = new Employee("daniel");


    }
    private static void LessonInheritance(){
        Employee employeeBip = new Employee();
        employeeBip.setFirstName("Bipin");
        employeeBip.setLastName("Batula");
        employeeBip.setId(3);
        System.out.println(employeeBip.getId() + " "+  employeeBip.getFirstName() + " " + employeeBip.getLastName());
    }
    private static  void LessonCalculatorLAB(){
          Scanner input = new Scanner(System.in);
//          boolean h = true;
//          do{
//              try {
//                  System.out.println("Enter first number: ");
//                  int firstnum = input.nextInt();
//                  System.out.println("Enter second number: ");
//                  int secondnum = input.nextInt();
//                  if(firstnum > 0 && firstnum < 0 && secondnum  > 0 && secondnum < 0){
//                      LessonCalculatorLAB();
//                  }
//                  char operator;
//                  System.out.println("Enter the Operator (+, -, /, * )");
//                  operator = input.next().charAt(0);
//                  h = false;
//                  switch (operator){
//                      case '+':
//                          System.out.println(firstnum + secondnum);
//                       break;
//                      case '-':
//                          System.out.println(firstnum - secondnum);
//                          break;
//                      case '/':
//                          System.out.println(firstnum / secondnum);
//                          break;
//                      case '*':
//                          System.out.println(firstnum * secondnum);
//                          break;
//                      default:
//                          LessonCalculatorLAB();
//                      break;
//                  }
//
//              }catch (Exception e){
//                  System.out.println("INVALID");
//                  LessonCalculatorLAB();
//              }
//
//          }while(h == true);
//        Scanner input = new Scanner(System.in);
//        int number1 = 0;
//        boolean isNumber;
//
//        do{
//            System.out.print("Enter first number from 0 -9 ");
//            if(input.hasNextInt()){
//                number1 = input.nextInt();
//                isNumber = true;
//            }else {
//                System.out.println("INVALID");
//                isNumber = false;
//                input.next();
//            }
//        }while (!(isNumber));
//        if(number1 > 9 || number1 < 0){
//            LessonCalculatorLAB();
//        }
//
//        System.out.println(number1);
//        // getting second input
//        Scanner input2 = new Scanner(System.in);
//        int number2 = 0;
//        boolean isNumber2;
//        do{
//        System.out.print("Enter a 2nd number from 0 - 9 ");
//        if(input2.hasNextInt()){
//            number2 = input2.nextInt();
//            isNumber2 = true;
//        }else {
//            System.out.println("INVALID");
//            isNumber2 = false;
//            input.next();
//        }
//        }while(!(isNumber2));
//
//        if(number2 > 9 || number2 < 0){
//            LessonCalculatorLAB();
//        }
//
//        // getting operator input
//        Scanner operatorPrompt = new Scanner(System.in);
//        System.out.print("Enter an Operator +,-,/,*");
//        String operatorInput = operatorPrompt.nextLine();
//        switch (operatorInput){
//            case "+":
//                System.out.println(number1 + number2);
//            break;
//            case "-":
//                System.out.println(number1 - number2);
//            break;
//            case "/":
//                System.out.println(number1 / number2);
//            break;
//            case "*":
//                System.out.println(number1 * number2);
//            break;
//            default:
//                System.out.println("INVALID");
//                LessonCalculatorLAB();
//            break;
//        }
//
//
//        String greeting = "hello";
//        try {
//            int num[] = {1,2,3,4};
//            System.out.println(num[5]);
//        }catch (ArrayIndexOutOfBoundsException ex){
//            System.out.println("hi");
//        }

    }
    private static void LessonFizzBuzzLAB(){
        for(int i = 0; i <= 100; i++){
            if(i % 15 == 0) {
                System.out.println("FizzBuzz");
            }
            else if(i % 3 == 0){
                System.out.println("Fizz");
            }else if(i % 5 == 0) {
                System.out.println("Buzz");
            }
             else {
                System.out.println(i);
            }
        }
    }
    private static void  LessonList(){
       List<String> myStringCollection = new ArrayList<>();
       myStringCollection.add("1st String");
       myStringCollection.add("2nd String");
       myStringCollection.add("3rd String");
       myStringCollection.add("4th String");
       myStringCollection.add("5th String");

       for(String singleString: myStringCollection){
            System.out.println(singleString);
       }

   }
    private static void LessonStrings() {
     /* 1
        //notes: Strings have a value or not.
        String firstString = ""; // checking empty string
        firstString = null;    // checking for null value (can't use isEmpty() to check nulls, you will get an error
        firstString = "Something";

        if( firstString == null||firstString.isEmpty()){// notice we check string for empty or null
            System.out.println("String is empty");
        }else {
            System.out.println("String has value");
        }
    */
        /* 2
           notes: immutable meaning unable to change
           each iteration creates a new string, meaning 100 strings are created(check notes)


                for(int x = 0; x <= 100; x++){
                    firstString = "new value: " + Integer.toString(x);
                    System.out.println(firstString);
                    // VERY INEFFICIENT
                }
        */

    /* 3
        //notes: StringBuilder for better efficiency and one address in memory for all the iterations


                    StringBuilder myStringBuilder = new StringBuilder();
                    for(int x = 0; x <= 100; x++){
                            Below code works but you're still concatenating multiple strings("my value with string builder + x )
                            myStringBuilder.append("my value with string builder: "  + Integer.toString(x) + "\n");

                         this is better or more efficient than above
                         myStringBuilder.append("my value with string builder: ").append(x).append("\n");
                    }
                    System.out.println(myStringBuilder);

     */

    /* 4
                -- notes: Searching strings (indexOf, lastIndexOf)
                String myName = "Yahya";


                    String can be visualized as an array/list of single characters
                     Y   a   h   y   a
                    [0] [1] [2] [3] [4]




                int indexOf = myName.indexOf("h"); // case matters, if I did "H" it would return -1 meaning not in variable
                System.out.println(indexOf);

                int lastIndex = myName.lastIndexOf("a");
                System.out.println(lastIndex);
    */

    /* 5
                // enumerating a string(break that string up into it's individual characters)
                String largeString = "This is a longer string than before";
                for(char c: largeString.toCharArray()){  // "enhanced for loop(probably like for of loop from javascript)"
                    System.out.println(c);
                }

     */

        // notes: substring will trim or cut up a string depending on what indexes you pass it
        // substring(beginning index) or substring(beginning index, end index)
        String largeString = "This is a longer string than before";
        String partOfLargerString = largeString.substring(14, 18);
        System.out.println(partOfLargerString);

    }
    private static void LessonVariable() {
        String lastName = "Hassan", firstName = "Yahya";

        System.out.println(lastName + " " + firstName);

        Scanner reader = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String input = reader.nextLine();
        System.out.println(input);
    }
    private static void LessonFundamentalsLAB() { // 1 Creating LessonFundamentals static method
        /* 2 prompting user

                  Scanner userPrompt = new Scanner(System.in);
                  System.out.print("Enter a value from 1 - 10: ");
                  String userValue = userPrompt.nextLine();
                  System.out.println(Integer.parseInt(userValue) + 1.75);

        */


            /* 3 -- default values for primitives

                    // default values for primitives(I declared these variable globally above)

                    Main defaultValues = new Main(); // referencing the global scope

                    System.out.println(defaultValues.b);
                    System.out.println(defaultValues.s);
                    System.out.println(defaultValues.i);
                    System.out.println(defaultValues.l);
                    System.out.println(defaultValues.f);
                    System.out.println(defaultValues.d);
                    System.out.println(defaultValues.c);
                    System.out.println(defaultValues.bool);

                    // Min and Max values
                    // Boolean's do not have min or max values
                    System.out.println("Min integer = " + Integer.MIN_VALUE);
                    System.out.println("Max integer = " + Integer.MAX_VALUE);
                    System.out.println("Min Float= " + Float.MIN_VALUE);
                    System.out.println("Max Float= " + Float.MAX_VALUE);

                    System.out.println("Min Double = " + Double.MIN_VALUE);
                    System.out.println("Max Double = " + Double.MAX_VALUE);

                    System.out.println("Min Byte = " + Byte.MIN_VALUE);
                    System.out.println("Max Byte = " + Byte.MAX_VALUE);

                    System.out.println("Min Short = " + Short.MIN_VALUE);
                    System.out.println("Max Short = " + Short.MAX_VALUE);

                    System.out.println("Character Min Value: "+(Character.MIN_VALUE+0));
                    System.out.println("Character Max Value: "+(Character.MAX_VALUE+0));

        */

        /* 4

             // Creating a string and showing it's non-primitive
             String myString = "hello World!";
             System.out.println(myString.length()); // only objects can have methods

        */

        /* 5
                List<String> cars = new ArrayList<String>();
                cars.add("Acura");
                cars.add("Audi");
                cars.add("Ford");
                for(String model : cars){
                    System.out.println(model);
                }
        */

        /* 6 Creating variable and appropriate data types for GameName, WiningNumbers, DrawingDate etc


            // GameName variable
                String gameName = “PowerBall”;

            // WinningNumbers variable
                 List<Integer> winningNumbers = new ArrayList<Integer>();
                 winningNumbers.add(3);
                 winningNumbers.add(10);
                 winningNumbers.add(14);
                 winningNumbers.add(16);
                 winningNumbers.add(18);

            // Jackpot variable
                 String jackPot = "Jackpot*"

            // DrawingDate variable
                LocalDate drawingDate = LocalDate.of(2012, 12, 1);

         */
        Scanner firstprompt = new Scanner(System.in);
        System.out.print("Please Enter a number between 0 - 9");
//        if(firstInput <= 9 && firstInput >= 0 &&  firstInput == (int)firstInput){
//            // need to find a way to check if user passed in wrong data type(second condition in if was my attempt)
//            // in Java if wrong data type is passed in by user it just crashes smh
//            System.out.println("you passed correct input");
//        }else{ System.out.println("Invalid"); }

        try{
            int firstInput = firstprompt.nextInt();
            System.out.println("good you put a valid number in");
        }catch (NumberFormatException ex){
            System.out.println("INVALID");
        }

//        String firstName = "Yahya";
//        try{
//            int x = Integer.parseInt(firstName);
//            System.out.println(x);
//        }catch (NumberFormatException ex){
//            System.out.println("INVALID");
//        }

    }
    private static void LessonClassObject(){
        // instantiating a new object;
        Person myFirstPerson = new Person();
        myFirstPerson.setFirstName("Yahya");
        myFirstPerson.setLastName("Hassan");
        System.out.print(myFirstPerson.getFirstName());
        System.out.print(" ");
        System.out.print(myFirstPerson.getLastName());
    }
    //endregion


}


// Globally declaring primitives(You don't need to initialize for some reason in global scope)
//            byte b;
//            short s;
//            int i;
//            long l;
//            float f;
//            double d;
//            char c;
//            boolean bool;