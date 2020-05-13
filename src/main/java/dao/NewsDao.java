package dao;

import models.News;

import java.util.List;

public interface NewsDao {
    //CREATE
    void save(News news);

    //READ
    List<News> allNews();
    News findById(int id);
    List<News> allNewsInDepartment(int departmentId);

    //UPDATE
    //void update(String title, String news, int departmentId)

    //DESTROY
    void delete(int id);
    void clearAll();
}

