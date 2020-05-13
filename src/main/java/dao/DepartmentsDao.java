package dao;

import models.Departments;

import java.util.List;

public interface DepartmentsDao {
    //CREATE: save department information
    void save(Departments departments);

    //READ: list all departments
    List<Departments> findAll();
    //find department by id
    Departments findById(int id);


    //UPDATE
    //void update(String name, String description, int numOfEmployees);

    //DESTROY
    //delete a department
    void delete(int id);
    //delete all departments
    void clearAll();
}


