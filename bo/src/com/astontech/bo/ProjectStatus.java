package com.astontech.bo;

import java.util.Date;

public class ProjectStatus extends BaseBO{
    private int ProjectStatusId;
    private int ProjectId;
    private int EntityTypeId;
    private String Notes;
    private Double PercentComplete;
    private Date   StartDate;
    private Date   EndDate;

    //region CONSTRUCTOR
        public ProjectStatus(){}

        public ProjectStatus(int projectId, String notes){
            this.setProjectId(projectId);
            this.setNotes(notes);
        }

    //endregion

    //region GETTERS/SETTERS
    public int getProjectStatusId() {
        return ProjectStatusId;
    }

    public void setProjectStatusId(int projectStatusId) {
        ProjectStatusId = projectStatusId;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
    }

    public int getEntityTypeId() {
        return EntityTypeId;
    }

    public void setEntityTypeId(int entityTypeId) {
        EntityTypeId = entityTypeId;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public Double getPercentComplete() {
        return PercentComplete;
    }

    public void setPercentComplete(Double percentComplete) {
        PercentComplete = percentComplete;
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
    //endregion
}
