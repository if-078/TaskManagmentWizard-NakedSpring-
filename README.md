#To run application you need:
-create schema called "tmw" on mysql server;
-run sql script on path \src\test\resources\create_db.sql to create tables;
-run sql script on path \src\test\resources\set_dafault_values.sql;
-deploy by embedded tomcat plugin:
    from console mvn tomcat7:run-war;
- in browser go to url: http://localhost:8585/
-user email for authentication is petro@gmail.com and pass 1111;




		
