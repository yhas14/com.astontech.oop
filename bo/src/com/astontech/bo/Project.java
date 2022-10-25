package com.astontech.bo;

import java.util.Date;

public class Project extends BaseBO{
    private int ProjectId;
    private int ClientId;
    private int EntityTypeId;
    private int Rate;
    private Date StartDate;
    private Date EndDate;
    private String ProjectName;

    //region CONSTRUCTOR
        public Project(){}

        public Project(int projectId, String projectName){
            this.setProjectId(projectId);
            this.setProjectName(projectName);
        }
    //endregion

    //region GETTERS/SETTERS
    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int clientId) {
        ClientId = clientId;
    }

    public int getEntityTypeId() {
        return EntityTypeId;
    }

    public void setEntityTypeId(int entityTypeId) {
        EntityTypeId = entityTypeId;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }
    //endregion
}
