## ⚽ **Football Team Management** ⚽

Java Spring Boot backend API of system for managing Football Teams, Players and Transfers

Java 17, Maven, Spring Boot, Hibernate, PostgreSQL

## :computer: **How to run the project**
1. Download [Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) and [Maven](https://maven.apache.org/install.html).
2. Open your terminal (cmd) and check Java installation by `java -version` and Maven `mvn -version`
3. Clone repository: Open your terminal (cmd) and use `https://github.com/MishaHMK/FootballManager.git`.
4. Download and install [PostgreSql](https://www.postgresql.org/download/).
5. Open your terminal (cmd) and create PostgreSQL user `psql -U User`.
6. Create a database `CREATE DATABASE DB_NAME;`
7. In src/main/recources/application.properties put proper PostgreSQL db data:
   <img width="883" height="302" alt="image" src="https://github.com/user-attachments/assets/3ea9450d-81f1-40b3-a3e2-a9992d38496a" />

8. Сompile project `mvn clean install` 
9. Run project `java -jar target/FootballManager-0.0.1-SNAPSHOT.jar`

## Test how project works: [![Run in Postman](https://run.pstmn.io/button.svg)](https://www.postman.com/team66-9067/michhmk-public/collection/5zle125/football-management-api) 

You can also import my Postman collection and modify for your needs
