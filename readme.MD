donation-services
=================

SaaS for Donation Management. Show cases Domain Drive Development Web Application

Non-Profits Manage Member, Pledge and Payment information. A showcase for Martin Fowler's Smart Domain Model Principles.

Tech Stack:
Maven2.x/3.x, Spring MVC2.x, Hibernate JPA, MySQL5.x, Tomcat/Jetty


*You need to have maven installed and set in your windows PATH to do these steps

Git clone and follow these steps to Build and Deploy

1) Uncomment the line with "<property name="hibernate.hbm2ddl.auto" value="create" />"
   in the src/main/resources/META-INF/persistance.xml
2) create a database donation in mysql
3) change db url/user/password settings in src/main/resources/db.properties and in src/main/webapp/WEB-INF/jetty-env.xml
4) run "mvn package jetty:run"
5 register a user "test@test.com"
6) should see all features related to pledges and payments

Please contribute more to help non-profits manage their donations
