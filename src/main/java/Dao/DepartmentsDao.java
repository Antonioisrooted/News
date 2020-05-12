package Dao;

import models.Departments;
import models.Employees;
import models.News;

import java.util.List;

public interface DepartmentsDao {
    //create
    void add(Departments departments);
    void addDepartmentsToEmployees(Departments departments, Employees employees);
    void addDepartmentsToNews(Departments departments, News news);

    //read
    List<Departments> getAll();
    List<Employees>GetAllEmployeesByDepartments(int departments_id);
    List<News>GetAllNewsByDepartments (String Department_news);

    //update
    //void update(String department_name, String department_description, int number_of_employees);

    //delete
    void deleteByDepartment_id(int department_id);
    void clearAll();
}
