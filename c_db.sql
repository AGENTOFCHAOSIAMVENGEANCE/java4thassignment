create table employees(
	Name varchar(50),
	EmployeeID int,
	Profession varchar(50),
	Experience int,
	Age int
);

create table employeesTask(
	EmployeeID int,
	task varchar(100)	
);


create table task(
	taskID int,
	deadline varchar(50),
	task varchar(100)
);

select * from task
select * from employeesTask
select * from employees



