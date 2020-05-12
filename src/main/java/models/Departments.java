package models;

public class Departments {

    private String department_name;
    private String department_description;
    private int number_of_employees;
    private int department_id;
    private int id;
    /*Department name, description, number of employees, */
    public Departments(String department_name, String department_description, int number_of_employees, int department_id) {
        this.department_name = department_name;
        this.department_description = department_description;
        this.number_of_employees = number_of_employees;
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getDepartment_description() {
        return department_description;
    }

    public void setDepartment_description(String department_description) {
        this.department_description = department_description;
    }

    public int getNumber_of_employees() {
        return number_of_employees;
    }

    public void setNumber_of_employees(int number_of_employees) {
        this.number_of_employees = number_of_employees;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}