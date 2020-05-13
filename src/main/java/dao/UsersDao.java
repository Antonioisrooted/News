package dao;

import models.Departments;
import models.Users;

import java.util.List;

public interface UsersDao {
    //save user information
    void save(Users users);
    //list all users
    List<Users> findAll();
    //find user by id
    Users findById(int id);
    //find all users in a department
    List<Users> allUsersInADepartment(int departmentId);
    //delete a user
    void delete(int id);
    //delete all users
    void clearAll();
}
