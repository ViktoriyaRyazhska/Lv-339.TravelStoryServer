plugin location: maven projects - plugins - liquibase

    if you see error 'table already exists', run liquibase:changeLogSync

    don`t change existing changelog, it will cause compilation error (all changelogs
    are synced in table databasechangelog)

    databasechangeloglock table ensure only one instance of Liquibase
     is running at one time


-generate changelog first time(generates only columns)

	* run application

	* run liquibase:generateChangeLog


-generate changelog (generates only columns)

	* change entities

	* change property file name outputChangeLog in application.properties (e.g. from 1.0 to 1.1)

	* run application, wait while new tables will have created

	* run liquibase:generateChangeLog

-update db of latest changelog

    * run liquibase: update

    (liquibase:migrate is deprecated)






_______________
configuration


liquibase.properties (example):

url=jdbc:mysql://localhost/travelstory?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false
username=root
password=root
driver=com.mysql.jdbc.Driver
outputChangeLogFile=src/main/resources/db/changelog/db.changelog-1.0.xml
changeLogFile=src/main/resources/db/db.changelog-master.xml


application.properties:

spring.liquibase.change-log=classpath:db/db.changelog-master.xml


pom.xml:

     <dependencies>
 ...
         <dependency>
            <groupId>org.liquibase</groupId>
            <artifactId>liquibase-core</artifactId>
            <version>3.6.2</version>
            </dependency>
         <dependency>
             <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
            <version>8.0.12</version>
        </dependency>
      </dependencies>

         <plugins>
              <plugin>
                  <groupId>org.liquibase</groupId>
                    <artifactId>liquibase-maven-plugin</artifactId>
                 <version>3.6.2</version>
                 <configuration>
                     <propertyFile>src/main/resources/liquibase.properties</propertyFile>
                 </configuration>
              </plugin>
            ...
         </plugins>

