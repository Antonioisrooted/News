package Dao;

import models.Departments;
import models.Employees;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class sql2oEmployeesDao implements EmployeesDao{
    private final Sql2o sql2o;
    public sql2oEmployeesDao (Sql2o sql2o) {this.sql2o = sql2o; }

    @Override
    public void add(Employees employees) {
        String sql = "INSERT INTO employees( employee_name, position_in_company, employee_id, role, department) VALUES (:employee_name :position_in_company :employee_id :role :department)";
        try(Connection con  = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(employees)
                    .executeUpdate()
                    .getKey();
            employees.setId(id);
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public List<Employees> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM employees")
                    .executeAndFetch(Employees.class);
        }
    }

    @Override
    public void addEmployeesToDepartments(Employees employees, Departments departments) {
        String sql = "INSERT INTO department_employees (departments_id, employees_id) VALUES (:departments_id, :employees_id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departments_id", departments.getId())
                    .addParameter("employees_id", employees.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Departments> getAllDepartmentsForEmployees (int department_id) {
        List<Departments> departments = new ArrayList<>();
        String joinQuery = "SELECT employees_id FROM departments_employees WHERE employees_id= :employees_id";

        try (Connection con = sql2o.open()) {
            List<Integer> allDepartments_Ids = con.createQuery(joinQuery)
                    .addParameter("department_Id", department_id)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer departments_Id : allDepartments_Ids){
                String departmentsQuery = "SELECT * FROM departments WHERE id = :departments_Id";
                departments.add(
                        con.createQuery(departmentsQuery)
                                .addParameter("departments_id", department_id)
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
    public void deleteByEmployee_id(int employee_id) {
        String sql = "DELETE from employees WHERE employee_id=:employee_id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("employee_id", employee_id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }