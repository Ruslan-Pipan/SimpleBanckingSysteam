# SimpleBanckingSysteam
This project is a final task from ITVDN. The essence of the project was to consolidate skills in technologies: JavaEE, Servlet, JSF, Tomcat, Maven, Junit, Hibernate, JDBC, SQL, MySQL, PostgreSQL, Collections, Generics, Reflection, OOP princeples, SOLID principles.
***********************
About the project itself
It's  a simple imitation Banking system.
It has a registration form, user cabinet, and admin pane.

************************
The first task was to create a system that can use two Databases PostgreSQL and MySQL. 
The first system has will be connected to SQL databases through JDBC which has data about consumers and their accounts. Each consumer can have three different accounts as current account, credit account, and interest account. Implement a system of accounts through inheritance.
The second system has data about employees and their position, and salary, implement position through interfaces. It will be connected to PostgreSQL through Hibernate.

*************************
The second task was to create a system Conroler through Command Patterns.
Used GoF Command pattern, Command interfaces with single method execute(), CommandFactory with all commands, and separate command on each HTTP request.

***********************
The trid task was to rewrite a system Conroler through Command Patterns and create a new DispatcherServlet(like in Spring) with three annotations Controller, Get, Post to be implemented through Reflection.

Create DispatcherSerlet which has found all controls about marked @Controler annotation, and created a single object and put it to the cache. Classes marked @Conroler should have methods with HTTP requests and marked @Post or @Get.
Annotation @Controler should have the main path, @Post and @Get should have a path on specific requests. At runtime should call the query method, which has an HTTP request.

**********************
The fourth task covered with Junit tests, and fix found mistakes.
***********************
The fifth task consolidate knowledge of Java SE such as: Collections, Generics, SOLID principles, GoF patterns, OOP princeples.
