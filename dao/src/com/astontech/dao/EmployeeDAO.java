package com.astontech.dao;

import com.astontech.bo.Employee;
import com.astontech.bo.Person;

import java.util.List;

public interface EmployeeDAO {
    public Employee getEmployeeById(int employeeId);
    public List<Employee> getEmployeeList();

    //notes: EXECUTE METHODS
    public int insertEmployee(Employee employee);
    public boolean updateEmployee(Employee employee);
    public boolean deleteEmployee(int employee);
}
