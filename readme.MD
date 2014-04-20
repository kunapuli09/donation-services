Steps After Checkout Project from GIT
*You need to have maven installed and set in your windows PATH to do these steps

1)Goto project properties in eclipse and edit M2_REPO eclipse variable to set your maven repository
  Maven  repository by default is in c:\Documents Settings\<windowsUser Name>\.m2\repository

Steps to Build and Deploy
1)Uncomment the line with "<property name="hibernate.hbm2ddl.auto" value="create" />"
   in the src/main/resources/META-INF/persistance.xml