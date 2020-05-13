package dao;

import models.Departments;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUsersDao implements UsersDao{
    private final Sql2o sql2o;

    public Sql2oUsersDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void save(Users users){
        String sql = "INSERT INTO users (name, position, role, departmentid) VALUES (:name, :position, :role, :departmentId)";
        try (Connection connection = sql2o.open()) {
            int id = (int)connection.createQuery(sql, true)
                    .bind(users)
                    .executeUpdate()
                    .getKey();
            users.setId(id);
        } catch (Sql2oException ex) { System.out.println(ex); }
    }
    @Override
    public List<Users> findAll(){
        String sql = "SELECT * FROM users";
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(Users.class);
        }
    }
    @Override
    public Users findById(int id){
        String sql = "SELECT * FROM users WHERE id = :id";
        try(Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Users.class);
        }
    }
    @Override
    public List<Users> allUsersInADepartment(int departmentId){
        String sql = "SELECT * FROM users WHERE departmentid = :departmentId";
        try (Connection connection = sql2o.open()){
            return connection.createQuery(sql)
                    .addParameter("departmentId",departmentId)
                    .executeAndFetch(Users.class);
        }
    }
    @Override
    public void delete(int id){
        String sql = "DELETE FROM users WHERE id = :id";
        try (Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        } catch (Sql2oException ex) {System.out.println(ex); }
    }
    @Override
    public void clearAll(){
        String sql = "DELETE FROM users";
        try (Connection connection = sql2o.open()){
            connection.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){ System.out.println(ex);}
    }
}
