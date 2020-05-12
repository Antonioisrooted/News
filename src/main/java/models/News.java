package models;

public class News {
    /* name, position in the company, their roles;  */
    private String general_news;
    private String department_news;
    private int id;


    public News(String general_news, String department_news) {
        this.general_news = general_news;
        this.department_news = department_news;
    }

    public News(String department_news) {
        this.department_news = department_news;
    }

    public String getGeneral_news() {
        return general_news;
    }

    public void setGeneral_news(String general_news) {
        this.general_news = general_news;
    }

    public String getDepartment_news() {
        return department_news;
    }

    public void setDepartment_news(String department_news) {
        this.department_news = department_news;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}