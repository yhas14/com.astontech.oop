package com.astontech.bo;

import java.util.Date;

public class Training extends BaseBO{
    private int TrainingId;
    private int EmployeeId;
    private String TrainingName;
    private Date    CreateDate;

    //region CONSTRUCTOR
        public Training(){}

        public Training(int trainingId, String trainingName){
            this.setTrainingId(trainingId);
            this.setTrainingName(trainingName);
        }
    //endregion

    //region GETTERS/SETTERS
    public int getTrainingId() {
        return TrainingId;
    }

    public void setTrainingId(int trainingId) {
        TrainingId = trainingId;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getTrainingName() {
        return TrainingName;
    }

    public void setTrainingName(String trainingName) {
        TrainingName = trainingName;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }
    //endregion
}
