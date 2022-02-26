package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import DB.PostgreDataBase;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class Company {
    Scanner scanner;
    ArrayList<Task> tasks;
    ArrayList<Employee> employees;
    private PostgreDataBase db;

    public Company () {
        this.scanner = new Scanner(System.in);
        this.tasks = new ArrayList();
        this.employees = new ArrayList();
        this.db = new PostgreDataBase();
    }

    public void addTask (Task b) {
        tasks.add(b);
        addTaskRecord(b);
    }

    public void addEmployee (Employee s) {
        employees.add(s);
        addEmployeeRecord(s);
    }

    public boolean addTaskRecord (Task task) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO task(taskID,deadline,task) VALUES (?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, task.getTaskID());
            statement.setString(2, task.getDate());
            statement.setString(3, task.getTask());
            statement.execute();
            boolean b = true;
            return b;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

        return false;
    }

    public boolean addEmployeeRecord (Employee s) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO employees(Name,EmployeeID,Profession,Experience,Age) VALUES (?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, s.getName());
            statement.setInt(2, s.getEmployeeID());
            statement.setString(3, s.getProfession());
            statement.setInt(4, s.getExperience());
            statement.setInt(5, s.getAge());
            statement.execute();
            boolean b = true;
            return b;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

        return false;
    }

    public boolean addEmployeesTask (int EmployeeId, String task) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO employeesTask(EmployeeID,task) VALUES (?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, EmployeeId);
            statement.setString(2, task);
            statement.execute();
            boolean b = true;
            return b;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

        return false;
    }
    public void deleteTask(int taskId){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "DELETE FROM task WHERE taskID=?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, taskId);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
            }
            System.out.println("task was deleted!");
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public boolean getEmployees() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM employees";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                Employee stud = new Employee(rs.getString("Name"),rs.getInt("EmployeeID"), rs.getString("Profession"), rs.getInt("Experience"),rs.getInt("Age"));
                if (!this.employees.contains(stud)) {
                    this.employees.add(stud);
                }
            }

            boolean b = true;
            return b;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

        return false;
    }

    public boolean getTask () {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT * FROM task";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                Task task = new Task(rs.getInt("taskID"),rs.getString("deadline"), rs.getString("task"));
                if (!this.tasks.contains(task)) {
                    this.tasks.add(task);
                }
            }

            boolean b = true;
            return b;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

        return false;
    }

    public boolean getEmployeeTask () {
        Connection con = null;

        try {
            con = this.db.getConnection();
            String sql = "SELECT * FROM employeesTask";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                for(int i = 0; i < employees.size(); ++i) {
                    if ((employees.get(i)).getEmployeeID() == rs.getInt("EmployeeID")) {
                        for(int j = 0; j < tasks.size(); ++j) {
                            if ((tasks.get(j)).getTask().equals(rs.getString("task"))) {
                                (employees.get(i)).employeetask(tasks.get(j));
                            }
                        }
                    }
                }
            }

            boolean b = true;
            return b;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

        return false;
    }

    public void addTaskToEmployee () {
        if(tasks.size()>0&&employees.size()>0){
            System.out.println("List of tasks: ");

            int i;
            for(i = 0; i < tasks.size(); ++i) {
                System.out.println(i + 1 + "." + (tasks.get(i)).getTask());
            }

            System.out.println("Please select task's number: ");
            int check = scanner.nextInt();
            if (check > 0) {
                System.out.println("List of employees: ");

                for(i = 0; i < employees.size(); ++i) {
                    System.out.println(i+1 + "." + (employees.get(i)).getName());
                }

                System.out.println("Select employee's number");
                int scheck = scanner.nextInt();
                if ((true)) {
                    (employees.get(scheck - 1)).employeetask(tasks.get(check - 1));
                    //getid
                    addEmployeesTask((employees.get(scheck - 1)).getEmployeeID(), (tasks.get(check - 1)).getTask());
                } else {
                    System.out.println("Sorry but we don't have this task");
                }
            } else {
                System.out.println("checker lower than 0");
            }
        }else{
            System.out.println("Don't have any tasks or employees");
        }

    }

    public void returnTaskFromEmployee () {
        if(tasks.size()>0&&employees.size()>0){
            System.out.println("List of employees: ");

            int i;
            for(i = 0; i < employees.size(); ++i) {
                System.out.println(i + 1 + "." + (employees.get(i)).getName());
            }

            System.out.println("Please choose employee task number");
            int nextInt = scanner.nextInt();
            if (nextInt > 0) {
                System.out.println("List of employee's  tasks : ");

                for(i = 0; i < (employees.get(nextInt - 1)).borrowedTasks.size(); ++i) {
                    System.out.println(i + 1 + "." + ((employees.get(nextInt - 1)).borrowedTasks.get(i)).getTask());
                }

                if ((employees.get(nextInt - 1)).borrowedTasks.size() >= 1) {
                    System.out.println("Select tasks number which you wanna return: ");
                }
                int sch = scanner.nextInt();
                if (sch > 0) {
                    PrintStream printStream = System.out;
                    Task title = (employees.get(nextInt - 1)).borrowedTasks.get(sch - 1);
                    printStream.println(title.getTask() + " returned");
                    ((Employee) employees.get(nextInt - 1)).removeEmployeesTask(tasks.get(sch - 1));
                }
            }
        }
        else{
            System.out.println("Don't have any tasks or employees");
        }


    }



    public void printTaskInfo () {
        System.out.println("Tasks: ");

        for(int i = 0; i < tasks.size(); ++i) {
            PrintStream print = System.out;
            String taskinfo = (tasks.get(i)).getTask();
            String deadline = (tasks.get(i)).getDate();
            int id = (tasks.get(i)).getTaskID();
            print.println("Task info: " + taskinfo + " || Task's deadline: "+ deadline +"|| Task's id: "+id);
//            print = System.out;
//            Object o = tasks.get(i);
//            print.println("Task info: " + ((Task)o).getTask());
        }

    }

    public void printEmployeesInfo () {
        System.out.println("Among of all employees : " + employees.size());

        for(int i = 0; i < employees.size(); ++i) {
            PrintStream println = System.out;
            println.print("Employee's fullname: " + (employees.get(i)).getName() + " || ");
            println = System.out;
            Object o = employees.get(i);
            println.println("Employee's profession: " + ((Employee)o).getProfession());
        }

    }

    public void printEmployeesTask () {
        for(int i = 0; i < employees.size(); ++i) {
            PrintStream stream = System.out;
            Object name = employees.get(i);
            stream.print(((Employee)name).getName() + " have : ");
            for(int j = 0; j < (employees.get(i)).borrowedTasks.size(); ++j) {
                stream = System.out;
                Task title = (employees.get(i)).borrowedTasks.get(j);
                stream.print(title.getTask() + " || ");
            }
            System.out.println(" ");
        }

        System.out.println(" ");
    }
}