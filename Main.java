package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Company company = new Company();
        int choice = -1;
        while(choice != 0) {
            System.out.println("Welcome to the AITU Company");
            System.out.println("1. Add Task");
            System.out.println("2. Add Employee");
            System.out.println("3. Give task to Employee");
            System.out.println("4. Remove the task from Employee");
            System.out.println("5. Print tasks information");
            System.out.println("6. Print employees information");
            System.out.println("7. Print Employee's tasks");
            System.out.println("8. Delete task");
            System.out.println("0. Exit");
            choice = scan.nextInt();
            switch(choice) {
                case 1:
                    System.out.println("Write task's Int Id, Int Deadline(format YYYY-MM-DD) and String Task");
                    int taskid = scan.nextInt();
                    String deadline = scan.next();
                    String task1 = scan.next();
                    Task task = new Task(taskid,deadline, task1);
                    company.addTask(task);
                    break;
                case 2:
                    System.out.println("Write String name, Int Employee's id, String Profession,Int Experience and Int Age");
                    String name = scan.next();
                    int id = scan.nextInt();
                    String Profession = scan.next();
                    int Experience = scan.nextInt();
                    int Age = scan.nextInt();
                    Employee employee = new Employee(name, id, Profession, Experience,Age);
                    company.addEmployee(employee);
                    break;
                case 3:
                    company.addTaskToEmployee();
                    break;
                case 4:
                    company.returnTaskFromEmployee();
                    break;
                case 5:
                    company.printTaskInfo();
                    break;
                case 6:
                    company.printEmployeesInfo();
                    break;
                case 7:
                    company.printEmployeesTask();
                case 8:
                    company.printTaskInfo();
                    System.out.println("Write taskID");
                    int taskid1 = scan.nextInt();
                    company.deleteTask(taskid1);
                case 9:
            }
        }

    }
}