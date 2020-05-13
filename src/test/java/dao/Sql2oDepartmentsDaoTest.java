package dao;

import models.Departments;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oDepartmentsDaoTest {
    private static Sql2oNewsDao newsDao;
    private static Sql2oDepartmentsDao departmentsDao;
    private static Sql2oUsersDao usersDao;
    private static Connection connection;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/organisational_api_test";
        Sql2o sql2o = new Sql2o(connectionString, "adwesh", "password");
        newsDao = new Sql2oNewsDao(sql2o);
        departmentsDao = new Sql2oDepartmentsDao(sql2o);
        usersDao = new Sql2oUsersDao(sql2o);
        connection = sql2o.open();

    }

    @After
    public void tearDown(){
        System.out.println("CLEARING DATABASE!!!!!!!");
        newsDao.clearAll();
        departmentsDao.clearAll();
        usersDao.clearAll();
    }


    @AfterClass
    public static void shutDown() throws Exception {
        System.out.println("SHUTTING DOWN DATABASE!!!!!!");
        connection.close();
    }

    @Test
    public void save_departmentsSavedSuccessfully() {
        Departments departments = setUpDepartments();
        departmentsDao.save(departments);
        assertEquals(1, departmentsDao.findAll().size());
    }

    @Test
    public void findAll_returnsAllDepartments() {
        Departments departments = setUpDepartments();
        departmentsDao.save(departments);
        Departments departments1 = setUpDepartments();
        departmentsDao.save(departments1);
        assertEquals(2, departmentsDao.findAll().size());
    }

    @Test
    public void findById_returnsDepartmentBasedOnID() {
        Departments departments = setUpDepartments();
        departmentsDao.save(departments);
        int idOfDepartment = departments.getId();
        int idOfFoundDepartment = departmentsDao.findById(departments.getId()).getId();
        assertEquals(idOfDepartment, idOfFoundDepartment);
    }

    @Test
    public void delete_deletesADepartmentBasedOnID() {
        Departments departments = setUpDepartments();
        departmentsDao.save(departments);
        Departments departments1 = setUpDepartments();
        departmentsDao.save(departments1);
        assertEquals(2, departmentsDao.findAll().size());
        departmentsDao.delete(departments.getId());
        assertEquals(1, departmentsDao.findAll().size());
    }

    @Test
    public void clearAll_deletesAllDepartments() {
        Departments departments = setUpDepartments();
        departmentsDao.save(departments);
        Departments departments1 = setUpDepartments();
        departmentsDao.save(departments1);
        assertEquals(2, departmentsDao.findAll().size());
        departmentsDao.clearAll();
        assertEquals(0, departmentsDao.findAll().size());
    }

    //helper methods
    public Departments setUpDepartments(){
        return new Departments("Finance","We finance",6);
    }
}
