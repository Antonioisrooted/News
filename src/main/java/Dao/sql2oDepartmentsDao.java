package Dao;

import models.Departments;
import models.Employees;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class sql2oDepartmentsDao implements DepartmentsDao {
    private final Sql2o sql2o;
    public sql2oDepartmentsDao (Sql2o sql2o) {this.sql2o = sql2o; }

    @Override
    public void add(Departments departments) {
        String sql = "INSERT INTO departments( department_name, department_description, number_of_employees,  department_id) VALUES (:department_name, :department_description, :number_of_employees :department_id)";
        try(Connection con  = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(departments)
                    .executeUpdate()
                    .getKey();
            departments.setId(id);
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public List<Departments> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Departments.class);
        }
    }

    @Override
    public void  addDepartmentsToEmployees(Departments departments, Employees employees) {
        String sql = "INSERT INTO departments_employees( departments_id, employees_id) VALUES (: department_id, :employees_id)";
        try (Connection con = sql2o.open())  {
            con.createQuery(sql)
                    .addParameter("departments_id", departments.getId())
                    .addParameter("employees_id", employees.getId())
                    .executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
    @Override
    public void addDepartmentsToNews(Departments departments, News news) {
        String sql = "INSERT INTO departments_news( departments_id, department_news) VALUES (: department_id, :department_news)";
        try (Connection con = sql2o.open())  {
            con.createQuery(sql)
                    .addParameter("departments_id", departments.getId())
                    .addParameter("id", news.getId())
                    .executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List <Employees> GetAllEmployeesByDepartments (int departments_id) {
        ArrayList <Employees> employees = new ArrayList<>();

        String joinQuery = "SELECT employees_id FROM departments_employees WHERE departments_id= :departments_id";

        try (Connection con = sql2o.open()) {
            List<Integer> allEmployees_Ids = con.createQuery(joinQuery)
                    .addParameter("departments_Id", departments_id)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer employees_Id : allEmployees_Ids){
                String employeesQuery = "SELECT * FROM employees WHERE id = :employees_Id";
                employees.add(
                        con.createQuery(employeesQuery)
                                .addParameter("employees_Id", employees_Id)
                                .executeAndFetchFirst(Employees.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return employees;
    }

    @Override
    public List<News> GetAllNewsByDepartments (String department_news){
        ArrayList <News> news = new ArrayList<>();
        String joinQuery = "SELECT department_id FROM departments_news WHERE department_news= :department_news";

        try (Connection con = sql2o.open()) {
            List<String> allDepartment_news = con.createQuery(joinQuery)
                    .addParameter("department_news", department_news)
                    .executeAndFetch(String.class); //what is happening in the lines above?
            for (String Department_news : allDepartment_news){
                String newsQuery = "SELECT * FROM employees WHERE id = :department_news";
                news.add(
                        con.createQuery(newsQuery)
                                .addParameter("department_news", department_news )
                                .executeAndFetchFirst(News.class));
            } //why are we doing a second sql query - set?
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

        return news;
    }
   /* @Override
    public List<Departments> getAllDepartmentsByDepartments(int departments_name) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM reviews WHERE departments_name = :departments_name")
                    .addParameter("departments_name", departments_name)
                    .executeAndFetch(Departments.class);
        }
    }*/

    @Override
    public void deleteByDepartment_id(int department_id) {
        String sql = "DELETE from departments WHERE department_id=:department_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("department_id", department_id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from departments";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
