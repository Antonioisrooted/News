package dao;

import models.Departments;
import models.Users;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import static org.junit.Assert.*;

public class Sql2oUsersDaoTest {
    private static Sql2oUsersDao usersDao;
    private static Sql2oDepartmentsDao departmentsDao;
    private static Connection connection;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/organisational_api_test";
        Sql2o sql2o = new Sql2o(connectionString, "adwesh", "password");
        usersDao = new Sql2oUsersDao(sql2o);
        departmentsDao = new Sql2oDepartmentsDao(sql2o);
        connection = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("CLEARING DATABASE!!!!!!");
        usersDao.clearAll();
        departmentsDao.clearAll();
    }

    @AfterClass
    public static void shutDown(){
        System.out.println("SHUTTING DOWN DATABASE!");
        connection.close();
    }

    @Test
    public void save_savesUserInformationInTheDatabase() throws Exception {
        Users users = setUpUser();
        usersDao.save(users);
        assertEquals(1, usersDao.findAll().size());
    }

    @Test
    public void findAll_ReturnsAllUsers() {
        Users users = setUpUser();
        assertEquals(0, usersDao.findAll().size());
        usersDao.save(users);
        assertEquals(1, usersDao.findAll().size());
    }
    @Test
    public void findById_returnsExistingUsersBasedOnId() {
        Users users = setUpUser();
        usersDao.save(users);
        Users users1 = usersDao.findById(users.getId());
        assertEquals(users, users1);
    }
    @Test
    public void delete_deletesUserById(){
        Users users = setUpUser();
        usersDao.save(users);
        assertEquals(1, usersDao.findAll().size());
        int idOfFoundUser = usersDao.findById(users.getId()).getId();
        usersDao.delete(idOfFoundUser);
        assertEquals(0, usersDao.findAll().size());
    }
    @Test
    public void clearAll_DeletesEverything(){
        Users users = setUpUser();
        usersDao.save(users);
        Users users1 = setUpUser();
        usersDao.save(users1);
        Users users2 = setUpUser();
        usersDao.save(users2);
        assertEquals(3, usersDao.findAll().size());
        usersDao.clearAll();
        assertEquals(0, usersDao.findAll().size());
    }
    @Test
    public void getAllEmployeesInDepartment_returnsAllEmployeesInDepartment(){
        Departments departments = setUpDepartment();
        departmentsDao.save(departments);
        Departments departments1 = setUpDepartment();
        departmentsDao.save(departments1);
        Users users = setUpUserForDepartment(departments);
        usersDao.save(users);
        Users users1 = setUpUserForDepartment(departments);
        usersDao.save(users1);
        assertEquals(2, usersDao.allUsersInADepartment(departments.getId()).size());
    }

    //helpers
    public Users setUpUser(){
        return new Users("Deez Nuts","Deeez Nuts", "Master Chief",1);
    }
    public Departments setUpDepartment(){
        return new Departments("Finance Department","Finance", 20);
    }
    public Users setUpUserForDepartment(Departments departments){
        return new Users("Deez Nuts", "Deez","Master Chef", departments.getId());
    }
}
