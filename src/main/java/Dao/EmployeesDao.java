package Dao;

import models.Departments;
import models.Employees;

import java.util.List;

public interface EmployeesDao {
    //create
    void add(Employees employees);
    void addEmployeesToDepartments(Employees employees, Departments departments);
    //read
    List<Employees>getAll();
    List<Departments>getAllDepartmentsForEmployees(int department_id);


    //update
    // void update(String employees_name, String position_in_company, int employee_id, String role, String department );

    //delete
    void deleteByEmployee_id(int employee_id);
    void clearAll();
}

