use master
go

if(db_id('RecruitmentProcessSystem') is not null)
begin
	drop database RecruitmentProcessSystem
end
go

create database RecruitmentProcessSystem
go

use RecruitmentProcessSystem
go

--Thông tin phòng ban
create table Department(
	id varchar(5) primary key,
	name nvarchar(50) not null,
	[description] nvarchar(50)
)
go

--Thông tin tập đoàn HR(Admin)
create table Administrator(
	id varchar(5) not null primary key,
	username varchar(30) not null,
	[password] varchar(30) not null,
	name nvarchar(50) not null,
	id_department varchar(5) foreign key references Department(id)
)
go

--Thông tin người phong vấn
create table Interviewer(
	id varchar(5) not null primary key,
	name nvarchar(50) not null,
	id_department varchar(5) foreign key references Department(id),
	username varchar(30) not null,
	[password] varchar(30) not null,
	birthday date not null,
	email nvarchar(100) not null,
	sex nvarchar(50) not null,
	[address] nvarchar(100) not null,
	phone nvarchar(50)
)
go

--Thông tin Ứng viên tham gia phỏng vấn
create table Applicant(
	id varchar(5) not null primary key,
	name nvarchar(50) not null,
	brithday date not null,
	email varchar(100) not null,
	sex nvarchar(50) not null,
	[address] nvarchar(100) not null,
	phone varchar(50),
	highest_degree nvarchar(50),
	university nvarchar(100),
	date_of_creation date not null,
	date_of_passing date,
	skill nvarchar(50),
	username nvarchar(50) not null,
	[password] nvarchar(50) not null,
	[status] varchar(20) not null
)
go

--Thông tin vị trí ứng tuyển
create table Vacancy(
	id varchar(5) not null primary key,
	id_department varchar(5) foreign key references Department(id),
	title nvarchar(100) not null,
	date_of_creation date not null,
	number_of_applicant int not null,
	[status] varchar(20) not null
)
go

--Thông tin buổi phỏng vấn
create table Interview(
	id varchar(5) primary key,
	date_of_start_interview date not null,
	date_of_end_interview date not null,
	id_vacancy varchar(5) foreign key references Vacancy(id),
	id_interviewer varchar(5) foreign key references Interviewer(id)
)
go

--Thông tin kết quả phỏng vấn
create table InterviewResult(
	id int primary key identity(100,1),
	id_interview varchar(5) foreign key references Interview(id),
	id_applicant varchar(5) foreign key references Applicant(id),
	[status] varchar(20) not null
)
go
