package models;

import java.util.Objects;

public class News {
    private String title;
    private String content;
    private int id;
    private int departmentId;

    public News(String title, String content){
        this.title = title;
        this.content = content;
    }

    public News(String title, String content, int departmentId){
        this.title = title;
        this.content = content;
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return Objects.equals(getTitle(), news.getTitle()) &&
                Objects.equals(getContent(), news.getContent());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getContent());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
