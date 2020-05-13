package models;

import java.util.Objects;

public class Departments {
    private String name;
    private String description;
    private int numOfEmployees;
    public int id;
    public Departments(String name, String description, int numOfEmployees){
        this.name = name;
        this.description = description;
        this.numOfEmployees = numOfEmployees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departments)) return false;
        Departments that = (Departments) o;
        return numOfEmployees == that.numOfEmployees &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, numOfEmployees);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumOfEmployees() {
        return numOfEmployees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumOfEmployees(int numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }
}
