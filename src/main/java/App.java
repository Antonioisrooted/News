import Dao.*;
import com.google.gson.Gson;
import models.Departments;
import models.News;
import models.Employees;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;

public class App {

    public static  void main(String[] args) {
        sql2oDepartmentsDao departmentsDao;
        sql2oEmployeesDao employeesDao;
        sql2oNewsDao newsDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/news";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");

        departmentsDao = new sql2oDepartmentsDao(sql2o);
        employeesDao = new sql2oEmployeesDao(sql2o);
        newsDao = new sql2oNewsDao(sql2o);
        conn = sql2o.open();
        post("/departments/new", "application/json",(req, res) -> {
            Departments departments = gson.fromJson(req.body(), Departments.class);
            departmentsDao.add(departments);
            res.status(201);
            res.type("application/json");
            return gson.toJson(departments);
        });

        post("/employees/new", "application/json",(req, res) -> {
            Employees employees = gson.fromJson(req.body(), Employees.class);
            employeesDao.add(employees);
            res.status(201);
            res.type("application/json");
            return gson.toJson(employees);
        });
        post("/news/new", "application/json",(req, res) -> {
            News news = gson.fromJson(req.body(), News.class);
            newsDao.add(news);
            res.status(201);
            res.type("application/json");
            return gson.toJson(news);
        });
        get("/departments", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(departmentsDao.getAll());
        });
        get("/employees", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(departmentsDao.getAll());
        });
        get("/news", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(departmentsDao.getAll());
        });
        after((req, res) ->{
            res.type("application/json");
        });
    }
}
