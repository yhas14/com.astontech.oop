package common.helpers;

public class StringHelper {
    // checks if string is null or empty. Returns true otherwise returns false.
    public static boolean isNullorEmpty(String s){
        return s == null || s.length() == 0;
    }

    public static String toString(int s){
        return s + "";
    }

    public static String toUppercase(String a){
        return a.toUpperCase();
    }

    public static String smileyFace(String b){
        return b + " :)";
    }
}
