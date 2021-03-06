# SimpleBanckingSysteam

## Summary:
  This project is a final task from ITVDN. The essence of the project was to consolidate skills in technologies: JavaEE, Servlet, JSF, Tomcat, Maven, Junit, Hibernate, JDBC, SQL, MySQL, PostgreSQL, Collections, Generics, Reflection, OOP princeples, SOLID principles.

## About the project itself:
  It's  a simple imitation Banking system. It has a registration form, user cabinet, and admin pane. Implemented three roles of access, enryption of user data in the database, protocol SSL, certificates.

## Web application architecture:
![alt](https://github.com/Ruslan-Pipan/SimpleBanckingSysteam/blob/master/screenshot/web%20application%20architecture.JPG)

+ DispatcherServlet - Receives all requests from the users and redirects to the target controleer.

+ AdminController - Handles admin requests such as: get all users, get the user by email, get the lastest account transactions.

+ PersonController - Handles person requests such as: authenticatу, registration.

+ ConsumerController - Handles consumer requests such as: send money, get transcactions, get checking accounts, get saving accounts.

Business logic:

+ Entity - Find all entities such as: Consumer, Account, CheckingAccount, SavingAccount, Transaktion.

+ Service - Provide transactions between bank accounts, encryption and decryption of data from the database.

+ DAO - The only module that communicates with the database.

+ Handlers - The module in which the system of controllers is implemented. 

+ Verification - Checking user data for correctness such as: phone number, email, password.

+ Cryptography - Provides encryption and decryption data.

## Database architecture:
![alt](https://github.com/Ruslan-Pipan/SimpleBanckingSysteam/blob/master/screenshot/database%20architecture.JPG)

+ accounts - The table that contains the basic fields for accounts.

+ saving_acc - The table stored specific fields for saving accounts.

+ checkin_acc - The table stored specific fields for checkin accounts.

+ last_number_acc - The table in which the only record of the last bank account is stored.

+ consumers - The table where usears are stored.

+ transakctions - The table where account transactions are stored.

## Tasks: 

+ The first task was to create a system that can use Databases MySQL. 
The system has will be connected to SQL databases through JDBC which has data about consumers and their accounts. Each consumer can have three different accounts as current account, credit account, and interest account. Implement a system of accounts through inheritance.

+ The second task was to create a system Conroler through Command Patterns.
Used GoF Command pattern, Command interfaces with single method execute(), CommandFactory with all commands, and separate command on each HTTP request.

+ The third task was to rewrite a system Conroler through Command Patterns and create a new DispatcherServlet(like in Spring) with three annotations Controller, Get, Post to be implemented through Reflection.

+ The fourth task was to create DispatcherSerlet which has found all controls about marked @Controler annotation, and created a single object and put it to the cache. Classes marked @Conroler should have methods with HTTP requests and marked @Post or @Get.
Annotation @Controler should have the main path, @Post and @Get should have a path on specific requests. At runtime should call the query method, which has an HTTP request.

+ The fifth task system should have three security: "Person" should show only index pages where can be registered or sign-in. 
"Consumer" can enter the cabinet pages where will see his bills, select one of them and transfer money to someone.
"Admin" can enter the admin panel where hes is can to choose all consumers, the last transactions.

+ The sixth task covered with Junit tests, and fix found mistakes.

+ The seventh task consolidate knowledge of Java SE such as: Collections, Generics, SOLID principles, GoF patterns, OOP princeples.

## Folder structure:

![alt](https://github.com/Ruslan-Pipan/SimpleBanckingSysteam/blob/master/screenshot/program%20structure.JPG)

## Example:
+ An example of how I documented  my code:

![alt](https://github.com/Ruslan-Pipan/SimpleBanckingSysteam/blob/master/screenshot/example%20%20documented%20code.JPG)

+ An example of how I tested my code:

![alt](https://github.com/Ruslan-Pipan/SimpleBanckingSysteam/blob/master/screenshot/example%20%20tested%20code.JPG)

+ An example of my controller.

![alt](https://github.com/Ruslan-Pipan/SimpleBanckingSysteam/blob/master/screenshot/example%20controller.JPG)

## Web front-end:

![alt](https://github.com/Ruslan-Pipan/SimpleBanckingSysteam/blob/master/screenshot/front%20end.jpg)

1. Home page for authorization or registration.

1. Account page and manipulation with them.

1. Validation of data of the user to whom it is going to send money.

1. Admin panel.

## To summarize :
  On this project, I gained knowledge on designing MVC, DAO, GoF patterns. Consolidate in practice OOP, SOLID principles. Also got acquainted with SSL protocol with encryption and certificates.
