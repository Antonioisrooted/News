package Dao;

import models.Departments;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class sql2oNewsDao implements NewsDao{
    private final Sql2o sql2o;
    public sql2oNewsDao (Sql2o sql2o) {this.sql2o = sql2o; }

    @Override
    public void add(News news) {
        String sql = "INSERT INTO news(general_news, department_news) VALUES (:general_news :department_news)";
        try(Connection con  = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(news)
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public List<News> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM news")
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public void addDepartmentsToNews(News news, Departments departments) {
        String sql = "INSERT INTO departments_news (departments_id, department-news) VALUES (:departments_id, :department_news)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departments_id", departments.getId())
                    .addParameter("Department_news", news.getDepartment_news())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<Departments> getAllDepartmentsByNews(int department_id) {
        ArrayList<Departments> departments = new ArrayList();
        String joinQuery = "SELECT department_news FROM departments_news WHERE department_id= :department_id";


        try (Connection con = sql2o.open()) {
            List<Integer> allDepartment_Ids = con.createQuery(joinQuery)
                    .addParameter("department_id", department_id)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer Department_id : allDepartment_Ids){
                String departmentsQuery = "SELECT * FROM employees WHERE id = :department_news";
                departments.add(
                        con.createQuery(departmentsQuery)
                                .addParameter("department_id", department_id )
                                .executeAndFetchFirst(Departments.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return departments;
    }

    /*@Override
    public List<Employees> getAllEmployeesByDepartments(int employees_Id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM employees WHERE employees_Id = :employees_Id")
                    .addParameter("employees_Id", employees_Id)
                    .executeAndFetch(Employees.class);
        }
    }*/

    @Override
    public void deleteById(int id ) {
        String sql = "DELETE from news WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from employees";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
