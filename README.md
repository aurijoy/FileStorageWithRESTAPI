# FileStorageWithRESTAPI
Simple File Storage Application with REST APIs using SpringBoot



Authors Note:  9/16/2020

The project implements a basic Spring Web Security and implements File Storage using MySQL as the Database.
Configurations are listed in the application.properties file with server port as 8081 and database/datasource
url change them according to your environment.
 

Data Model: We have tried to approach the problem from two angles 

##1 Tight coupling of Entites with internal references between objects and join columns and tables. Here the data handling is checked e.g. existence of user etc. is checked by the data model itself using Joins etc.

Pros: Data model itself handles constraints and no need to push checks to application layer.
Cons: Obtaining user information from security context is infeasible at times
      We have to keep using Join queries in order to obtain intersecting information which maybe costly and might not be right for the use case.

##2 Loose Coupling of Entities they don't carry references to each other.

Pros  Updates are easier and less costly
	No need to carry out expensive join information.
Cons  Checks and constraint handling is pushed to application layer.

We have attempted 1 but reverted to 2 for ease of implementation for this project


## Starting the Project
Port is set to 8081
Configure datasource in application.properties
Datasource used here is MySQL version 8.0.13
Spring Bill of Materials is in the pom.xml
We have set a Database initialization in DBInit file. This populates the database with two users
User1
username: user1
password: user1

User2
username:user2
password: user2 
Please have a look. Please comment out DBInit file for subsequent server loadups after Database has already been created.

More users can be added using http://localhost:8081/add {8081 is the set server port for the application}

We have also tried to use data.sql file but for we did not want to turn hibernate.ddl OFF since we want hibernate to poplulate the 
database with the entities. This is keeping best practices in mind.
 
## How to test
For the purpose of this project in order to keep it simple we have removed the endpoints from authenticated paths to prevent cumbersome 
Postman authentications and since the concept of session is constrained by SpringSecurity. We have permitted the paths to unauthenticated
requests.

### Add a user
/add-> PostMapping 
Body as Raw JSON data e.g., 
 {
     "userName":"testuser",
     "password":"test",
     "email":"test@mail.com",
     "roles":"USER"
 }

### Login 
/user/login-> GetMapping
please enter credentials of the already present user from DBInit or any other user created.


### Showing All Files Pertaining to a specific user 
/getUserFiles-> PostMapping
{Authentication Disabled}
please choose Form Data in Body and include parameter: 
Key:username | Value:{already present username from Database}<Text>
Please choose a user already present in the list

### Uploading File for a specific User
/upload -> PostMapping
{Authentication Disabled}
please choose Form Data in Body and include parameter: 
Key:'username' | Value:{already present username from Database}<Text>
Key:'file' | Value:{file with properties described in application.properties}<File>
Please choose a user already present in the list

### Downloading File from a specific User's File set
]/download/{fileName} -> GetMapping
{Authentication Disabled}
For {fileName} please choose a file that already exists in the users file list.
Please choose a file already present in the list




------------------------------------------------------------------------------------

1 Features

 1.1  Security 

	The application uses basic Spring Security Based JPA authentication and Role based authorization. I have enbled WebSecurity configuration. 

  1.1.1 Authentication
 
	For the purpose of authentication I have we have implemented UserDetailService to interface our User repository with AuthenticationManagerBuilder and UserDetails to wrap around our User Entity


  1.1.2 Aurhorization

	For the purpose of this project I have used basic http path based authorization accoridng to Roles

  1.1.3 Adding User

	In this project we have attempted to add user only from paths with admin privilege /admin/adduser but strictly for testing purposes we have also added a /user/adduser method.


 1.2 FileServices

	The application provides basic File upload and Download services and handles it through REST API calls.

  1.2.1 FileUpload 

	The path to file upload id /user/uploadFileDB
	I have implemented basic Multipart file based transfer of Files for the upload controleer

  1.2.2 FileDownload

	For the download the path is /user/download/{filename}   where filename is the name of the file already present in the Repository.
	It returns the path of the file using ServeletUriComponentsBuilder


  1.2.3 Show All Files Pertaining to Current User

  1.2.3.1

	FOr the first attempt we tried to acquire the User from the UserDetailsService principl context but this approach turned out to be infeasible.
	This happened because we are trying to get User information from SecurityContextHolder which we didn't know wouldn't allow me to create security contexts on the Fly.


  1.2.3.2 

	For the second attempt we are going to change the design to accept username in the /upload Path so we cn use that to populate our database. This remove the constraint of acquiring
	User information from User Detail Service.



  1.2.3.4

	While we have not implemented sharing an application state in this case the file URL we have implemented
	 a download option which returns the file URL from the database.



2 URL Pathsand REST Endpoints

	We have a basic Path /hello to display Helloworld
	/upload
	/add
	/download
	/getUserFiles

 2.1 UserPaths
	/user** paths require authentication including


	GETMAPPING-> /user/login

	For the purpose of keeping testing simple we have removed paths from /user/**
	and put them directly in main path.



 2.2 AdminPaths   

	/admin** paths require authentication include

	POSTMAPPING--> /admin/adduser  



3 Pom.xml

	The necessary dependencies have been added in pom.xml using SpringInitializer please refer to it if additional dependencies need to added.

4 Configuration

	I have modified application.properties in the Resources Folder

 4.1 Database configuration

	I have set Database url path to a Demo MySQL instance at port 3306 please change it connect to your MySQL instance

	spring.datasource.url= spring.datasource.url=jdbc:mysql://localhost:3306/demo


	I also left availability for auto-creation of database

	spring.datasource.url=jdbc:mysql://localhost:3306/demo?createDatabaseIfNotExist=true


 4.2 Hibernate configuration

I have set the Hibernate properties as 
	spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect

Also in order to update database already existing I have left 
	spring.jpa.hibernate.ddl-auto = update


 4.3 Multipart File Configuration

I have written Multipart File properties as :
	
	Enable multipart uploads
	spring.servlet.multipart.enabled=true
	Threshold after which files are written to disk.
	spring.servlet.multipart.file-size-threshold=2KB
	Max file size.
	spring.servlet.multipart.max-file-size=200MB
	Max Request Size
	spring.servlet.multipart.max-request-size=215MB

 4.4 Default Server configuration

	I have changed server port from default 8080 to 80801
	server.port=8081

 4.5
	I have also enabled WebSecurity configuration for SpringSecurity 


 5 Database

	We have used MySQL as our Database for all our functionalities

  5.1 Entites 
      User- Basic User Entity to interface with USerDetail
      Role- Basic Role entity to interface with UserDetailsService
      FileDocument->  FileDocument is a table which tables wraps over the actual file and provides some additional information and methods.

  5.2 Database configuration 

	See 4.1

  5.3 Join Logic 
	We have attempted Joins but abandoned them in favor of direct queries since direct queries are more optimal given the use case.


  5.4 Optimization Logic  
	We will try to optimize the database by sorting the index.
