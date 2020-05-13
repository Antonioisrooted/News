import static spark.Spark.*;
import dao.*;
import com.google.gson.Gson;
import models.Departments;
import models.News;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class App {

    public static  void main(String[] args) {
        Sql2oDepartmentsDao departmentsDao;
        Sql2oUsersDao usersDao;
        Sql2oNewsDao newsDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/news";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");

        departmentsDao = new Sql2oDepartmentsDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        conn = sql2o.open();
        post("/departments/new", "application/json",(req, res) -> {
            Departments departments = gson.fromJson(req.body(), Departments.class);
            departmentsDao.add(departments);
            res.status(201);
            res.type("application/json");
            return gson.toJson(departments);
        });

        post("/users/new", "application/json",(req, res) -> {
            Users users = gson.fromJson(req.body(), Users.class);
            usersDao.add(users);
            res.status(201);
            res.type("application/json");
            return gson.toJson(users);
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