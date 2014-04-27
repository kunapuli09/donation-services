Steps After Checkout Project from GIT
*You need to have maven installed and set in your windows PATH to do these steps

1)Goto project properties in eclipse and edit M2_REPO eclipse variable to set your maven repository
  Maven  repository by default is in c:\Documents Settings\<windowsUser Name>\.m2\repository

Steps to Build and Deploy
1)Uncomment the line with "<property name="hibernate.hbm2ddl.auto" value="create" />"
   in the src/main/resources/META-INF/persistance.xml
2) create a database donation in mysql
3) change db url/user/password settings in src/main/resources/db.properties and in src/main/webapp/WEB-INF/jetty-env.xml
4) run "mvn package jetty:run"
5 register a user "test@test.com"
6) should see all features related to pledges and payments