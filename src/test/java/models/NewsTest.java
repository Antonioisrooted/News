package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    @Test
    public void testEquals_returnsBothInstancesTrue() {
        News news = new News("Babu Owino", "Babu Owino at B-Club");
        News news1 = new News("Babu Owino", "Babu Owino at B-Club");
        assertEquals(news, news1);
    }

    @Test
    public void getTitle_savesNewsTitleCorrectly() {
        News news = new News("Babu Owino", "Babu Owino at B-Club");
        assertEquals("Babu Owino", news.getTitle());
    }

    @Test
    public void setTitle_changesNewsTitleCorrectly() {
        News news = new News("Babu Owino", "Babu Owino at B-Club");
        news.setTitle("This is Sparta!");
        assertEquals("This is Sparta!", news.getTitle());

    }

    @Test
    public void getContent_savesNewsContentCorrectly() {
        News news = new News("Babu Owino", "Babu Owino at B-Club");
        assertEquals("Babu Owino at B-Club", news.getContent());
    }

    @Test
    public void setContent_changesNewsContent() {
        News news = new News("Babu Owino", "Babu Owino at B-Club");
        news.setContent("Fucc you mean!");
        assertEquals("Fucc you mean!", news.getContent());
    }
}