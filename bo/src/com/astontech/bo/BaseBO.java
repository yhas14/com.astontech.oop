package com.astontech.bo;

public class BaseBO {
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String test_method(){
        return "super Method";
    }

}
