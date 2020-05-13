import static spark.Spark.*;
import com.google.gson.Gson;
import dao.Sql2oDepartmentsDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUsersDao;
import models.Departments;
import models.News;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        } String connectionString = "jdbc:postgresql://ec2-52-6-143-153.compute-1.amazonaws.com:5432/d7b94st8651giq";
        Sql2o sql2o = new Sql2o(connectionString, "yhzkuupfywxbpm", "2b4b5862093ed77da79b75c50561aea1836045a5adf67bce73f1c251c8de249a");
        return 4567;
    }

    public static void main(String[]args){
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        Sql2oNewsDao newsDao;
        Sql2oUsersDao usersDao;
        Sql2oDepartmentsDao departmentsDao;
        Connection connection;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/organisational";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");
        newsDao = new Sql2oNewsDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        departmentsDao = new Sql2oDepartmentsDao(sql2o);
        connection = sql2o.open();

        //CREATE: Add a new department
        post("/department/new", "application/json",(request, response) -> {
            Departments departments = gson.fromJson(request.body(), Departments.class);
            departmentsDao.save(departments);
            response.status(201);
            return gson.toJson(departments);
        });

        //READ: Display all departments
        get("/departments", "application/json", (request, response) -> {
            return gson.toJson(departmentsDao.findAll());
        });
        //READ: Display department by ID
        get("/departments/:id", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            return gson.toJson(departmentsDao.findById(departmentId));
        });

        //CREATE: Add a user to a department
        post("department/:departmentId/user/new","application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            Users users = gson.fromJson(request.body(), Users.class);
            users.setDepartmentId(departmentId);
            usersDao.save(users);
            response.status(201);
            return gson.toJson(users);
        });
        //READ: Display all users
        get("/users", "application/json", (request, response) -> {
            return gson.toJson(usersDao.findAll());
        });

        //READ: Display a user
        get("user/:id","application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            return gson.toJson(usersDao.findById(userId));
        });

        //READ: Display all users in a department
        get("department/:id/users", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            Departments departments = departmentsDao.findById(departmentId);
            List<Users> allUsers = usersDao.allUsersInADepartment(departmentId);
            return gson.toJson(allUsers);
        });

        //CREATE: Add news, relates to all departments
        post("/news/new","application/json", (request, response) -> {
            News news = gson.fromJson(request.body(), News.class);
            newsDao.save(news);
            response.status(201);
            return gson.toJson(news);
        });
        //READ: View all news
        get("/","application/json", (request, response) -> {
            return gson.toJson(newsDao.allNews());
        });

        //READ: Display news by ID
        get("/news/:id","application/json",(request, response) -> {
            int newsId = Integer.parseInt(request.params("id"));
            return gson.toJson(newsDao.findById(newsId));
        });

        //CREATE: Add news belonging to a department
        post("department/:departmentId/news/new", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            News news = gson.fromJson(request.body(), News.class);
            news.setDepartmentId(departmentId);
            newsDao.save(news);
            response.status(201);
            return gson.toJson(news);
        });
        //GET: View all news belonging to a department
        get("department/:id/news", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            Departments departments = departmentsDao.findById(departmentId);
            List<News> allNews = newsDao.allNewsInDepartment(departmentId);
            return gson.toJson(allNews);
        });


//FILTERS
        after((request, response) ->{
            response.type("application/json");
        });

    }
}
