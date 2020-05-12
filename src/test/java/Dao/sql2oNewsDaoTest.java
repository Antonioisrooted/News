package Dao;
import org.sql2o.Sql2o;

import models.News;
import org.sql2o.Connection;
import org.junit.*;
import static org.junit.Assert.*;

public class sql2oNewsDaoTest {

    private sql2oNewsDao newsDao;
    private Connection conn;
    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/news_test";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "H@mmert0n-25%");
        newsDao = new sql2oNewsDao(sql2o); //ignore me for now
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }
    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        News news = new News("kk","kk");
        newsDao.add(news);
        assertEquals(1, newsDao.getAll().size());

    }
}