package dao;

import models.Departments;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentsDao implements DepartmentsDao {
    private Sql2oDepartmentsDao departmentsDao;
    private Connection conn;
    private final Sql2o sql2o;

    public Sql2oDepartmentsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(Departments departments){
        String sql = "INSERT INTO departments (name, description, numOfEmployees) VALUES (:name, :description, :numOfEmployees)";
        try (Connection connection = sql2o.open()){
            int id = (int)connection.createQuery(sql, true)
                    .bind(departments)
                    .executeUpdate()
                    .getKey();
            departments.setId(id);
        } catch (Sql2oException ex) { System.out.println(ex); }
    }
    @Override
    public List<Departments> findAll(){
        String sql = "SELECT * FROM departments";
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(Departments.class);
        }
    }
    @Override
    public Departments findById(int id){
        String sql = "SELECT * FROM departments WHERE id = :id";
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Departments.class);
        }
    }
    @Override
    public void delete(int id){
        String sql = "DELETE FROM departments WHERE id = :id";
        try (Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException ex) {System.out.println(ex); }
    }
    @Override
    public void clearAll(){
        String sql = "DELETE FROM departments";
        try (Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){ System.out.println(ex);}
    }
}
