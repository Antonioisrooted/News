package Dao;

import models.Departments;
import models.News;

import java.util.List;

public interface NewsDao {
    //create
    void add(News news);
    void addDepartmentsToNews(News news, Departments departments);

    //read
    List<News> getAll();
    List<Departments> getAllDepartmentsByNews(int departments_id);




    //update
    //void update(String general_news, String department_news);

    //delete
    void deleteById(int id);
    void clearAll();
}
