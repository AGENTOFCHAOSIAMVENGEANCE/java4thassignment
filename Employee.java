package com.company;

import java.util.ArrayList;

public class Employee {
    private String Name;
    private int EmployeeID;
    private String Profession;
    private int Experience;
    private int Age;
    ArrayList<Task> borrowedTasks = new ArrayList();

    public Employee (String Name, int EmployeeID, String Profession, int Experience, int Age) {
        this.Name = Name;
        this.EmployeeID = EmployeeID;
        this.Profession = Profession;
        this.Experience = Experience;
        this.Age = Age;
    }

    public String getName () {
        return Name;
    }

    public void setName (String name) {
        Name = name;
    }

    public int getEmployeeID () {
        return EmployeeID;
    }

    public void setEmployeeID (int employeeID) {
        EmployeeID = employeeID;
    }

    public String getProfession () {
        return Profession;
    }

    public void setProfession (String profession) {
        Profession = profession;
    }

    public int getExperience () {
        return Experience;
    }

    public void setExperience (int experience) {
        Experience = experience;
    }

    public int getAge () {
        return Age;
    }

    public void setAge (int age) {
        Age = age;
    }

    public ArrayList<Task> getBorrowedTasks () {
        return borrowedTasks;
    }

    public void setBorrowedTasks (ArrayList<Task> borrowedTasks) {
        this.borrowedTasks = borrowedTasks;
    }


    public void employeetask (Task task) {
        this.borrowedTasks.add(task);
    }

    public void removeEmployeesTask (Task task) {
        this.borrowedTasks.remove(task);
    }
}