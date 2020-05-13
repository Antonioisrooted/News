package dao;

import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oNewsDao implements NewsDao {
    private final Sql2o sql2o;

    public Sql2oNewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void save(News news){
        String sql = "INSERT INTO news (title, content, departmentid) VALUES (:title, :content, :departmentId)";
        try (Connection connection = sql2o.open()){
            int id = (int)connection.createQuery(sql, true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Sql2oException ex) { System.out.println(ex);}
    }
    @Override
    public List<News> allNews(){
        String sql = "SELECT * FROM news";
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(News.class);
        }
    }
    @Override
    public List<News> allNewsInDepartment(int departmentId){
        String sql = "SELECT * FROM news WHERE departmentid = :departmentId";
        try (Connection connection = sql2o.open()){
            return connection.createQuery(sql)
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(News.class);
        }
    }
    public News findById(int id){
        String sql = "SELECT * FROM news WHERE id = :id";
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(News.class);
        }
    }
    @Override
    public void delete(int id){
        String sql = "DELETE FROM news WHERE id = :id";
        try (Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException ex) {System.out.println(ex); }
    }
    @Override
    public void clearAll(){
        String sql = "DELETE FROM news";
        try (Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){ System.out.println(ex);}
    }

    public void add(News news) {
    }
}
