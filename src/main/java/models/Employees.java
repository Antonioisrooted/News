package models;

public class Employees {
    /* employee name, position in company , role, department */
    private String employee_name;
    private String position_in_company;
    private int employee_id;
    private String role;
    private String department;
    private int id;

    public Employees(String employee_name, String position_in_company,int employee_id, String role, String department) {
        this.employee_name = employee_name;
        this.position_in_company = position_in_company;
        this.employee_id = employee_id;
        this.role = role;
        this.department = department;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getPosition_in_company() {
        return position_in_company;
    }

    public void setPosition_in_company(String position_in_company) {
        this.position_in_company = position_in_company;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
