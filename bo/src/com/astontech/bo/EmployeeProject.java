package com.astontech.bo;

import java.util.Date;

public class EmployeeProject extends BaseBO{
    private int EmployeeProjectId;
    private int ProjectId;
    private int EmployeeId;
    private int EntityTypeId;
    private int vehicleId;
    private Date StartDate;
    private Date EndDate;
    private String Notes;


    //region CONSTRUCTOR
     public EmployeeProject(){}
     public EmployeeProject(int employeeProjectId, int projectId){
         this.setEmployeeProjectId(employeeProjectId);
         this.setProjectId(projectId);
     }
    //endregion

    //region GETTERS/SETTERS
    public int getEmployeeProjectId() {
        return EmployeeProjectId;
    }

    public void setEmployeeProjectId(int employeeProjectId) {
        EmployeeProjectId = employeeProjectId;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int projectId) {
        ProjectId = projectId;
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

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
    //endregion
}
