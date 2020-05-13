package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentsTest {

    @Test
    public void testEquals_returnBothInstancesTrue() {
        Departments departments = setUpDepartment();
        Departments departments1 = setUpDepartment();
        assertEquals(departments, departments1);
    }

    @Test
    public void getName_savesDepartmentNameCorrectly() {
        Departments departments = setUpDepartment();
        assertEquals("Finance Department",departments.getName());
    }

    @Test
    public void setName_changesName() {
        Departments departments = setUpDepartment();
        departments.setName("ICT");
        assertEquals("ICT", departments.getName());
    }

    @Test
    public void getDescription_savesDescriptionCorrectly() {
        Departments departments = setUpDepartment();
        assertEquals("We finance stuff", departments.getDescription());
    }

    @Test
    public void setDescription_changesDescription() {
        Departments departments = setUpDepartment();
        departments.setDescription("We are broke");
        assertEquals("We are broke", departments.getDescription());
    }

    @Test
    public void getNumOfEmployees_savesNumberOfEmployees() {
        Departments departments = setUpDepartment();
        assertEquals(3, departments.getNumOfEmployees());
    }

    @Test
    public void setNumOfEmployees_updatesNumberOfEmployees() {
        Departments departments = setUpDepartment();
        departments.setNumOfEmployees(5);
        assertEquals(5, departments.getNumOfEmployees());
    }

    //helper methods
    public Departments setUpDepartment(){
        return new Departments("Finance Department", "We finance stuff", 3);
    }
}