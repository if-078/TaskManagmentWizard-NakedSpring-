# TaskManagementWizard-NakedSpring-

Our link:
http://localhost:8585/

Used separate modules of spring framework.

How to run:
 1)CREATE MYSQL DB:
  CHANGE MYSQL_CONNECTION.PROPERTIES IN MAIN/RESOURCES.
  USE CREATE_DB.SQL SCRIPT TO CREATE TABLES(TEST/RESOURCES/SQL).

2)EXTEND PROJECT WITH YOUR ENTITY, DAO, SERVICE,CONTROLLER,UI.
  !CREATE RESOURCES IN RECEIVED PACKAGES!
  IN DAO AUTOWIRE DATASOURCE BEAN FROM MAINAPPCONFIG CLASS.

3)IN RESTCONTROLLER AUTOWIRE OBJECTMAPPER TO CONVERT OBJECT TO JSON AND
  CONVERSELY. 

4)DEPLOY:
  USE TOMCAT PLUGIN FROM MAVEN PROJECT PANEL, CHOOSE TOMCAT7:RUN.
		