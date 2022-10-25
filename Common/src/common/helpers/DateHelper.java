package common.helpers;

public class DateHelper {
    // setting sql date to java date
    public static java.sql.Date utilDateToSqlDate(java.util.Date date){
        return new java.sql.Date(date.getTime());
    }
    // setting java date to sql date
    public static java.util.Date sqlDateToUtilDate(java.sql.Date date){
        return new java.util.Date(date.getTime());
    }
}
