package com.astontech.bo.interfaces;

public class Reversed {
    private String s;

    public String getString() {
        StringBuffer revString = new StringBuffer();


        for (int i = s.length()-1; i >=0; i --){
            revString.append(String.valueOf(s.charAt(i)));

        }

        return revString.toString();
    }

    public void setString(String s) {
        this.s = s;
    }


}
