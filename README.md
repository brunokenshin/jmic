# Java Mail Info Collector - JMIC [![wercker status](https://app.wercker.com/status/2fd82aede5750d80a0e51d1df3e85835/s "wercker status")](https://app.wercker.com/project/bykey/2fd82aede5750d80a0e51d1df3e85835)
JMIC is a simple webapp to extract important information from emails using specific patterns.

Project uses Java 7, Spring Boot as core framework application, gradle 2.12 as building tool and Maven Central as dependencies repository.

It generates a '.war' file that can be depployed into a servlet-container like Tomcat. JMIC uses MySQL but you can change for another SQL database.

# Limitations:
- Must be only one persistance table for all fields
- Persistance table MUST have column "ID" (Primary Key) and it must be the ONLY "NOT NULL" column.
- Since mail fields are matched using regular expressions, user must know its syntax. Maybe even java particularities for it like special character escapes.

# Dependencies:
- mysql:mysql-connector-java
- com.h2database:h2:1.4.191
- org.apache.commons:commons-email:1.4
- org.apache.commons:commons-lang3:3.4
- org.springframework.boot:spring-boot-starter-jdbc
- org.springframework.boot:spring-boot-starter-mail
- org.springframework.boot:spring-boot-starter-web
- org.springframework.boot:spring-boot-starter-tomcat
- org.springframework.boot:spring-boot-starter-test

# Filters configuration:
These configurations defines which emails jmic will consider to do all the work. The more restricted search, faster will be the process.  

- subject
    + Sets keywords that lib should search at messages subject.
    + If null, it will search all message subjects
- sender
    + Sets the sender email that lib should search.
    + If null, it will search all senders.
- unread
    + Sets the read/unread mail flag.
    + 'True' will search for unread mails.
    + 'False' will search for read mails.
    + If null, it will search for both.
- daysago
    + Sets the how much days from now to past the app should search.
    + If null, it will search all the time.
    + PS: The bigger it is, more mails will catch and can let the system too slow. If let it null, will get all time emails and its VERY UNRECOMENDED!

# Fields configuration:
These are the fields that you want to get from the messages.

- fieldName
    + Represents the name of the field defined by his patterns.

- fieldStartPattern
    + Defines the pattern that mark the start of the wanted information.

- fieldEndPattern
    + Defines the pattern that mark the end of the wanted information.

# Important:
For google accounts maybe you need to active the "Less secure apps" option:
- https://www.google.com/settings/security/lesssecureapps

# References:
- http://www.compiletimeerror.com/2013/06/reading-email-using-javamail-api-example.html#.VvBXfHyrRD8
- http://www.oracle.com/technetwork/java/javamail/faq/index.html#gmail
- https://gusto77.wordpress.com/2012/04/03/extracting-text-from-email-message-with-javamail/
- http://stackoverflow.com/questions/870045/java-imap-fetch-messages-since-a-date

# Mirrors:
- https://gitlab.com/brunokenshin/jmic

# Build Information:
[![wercker status](https://app.wercker.com/status/2fd82aede5750d80a0e51d1df3e85835/m "wercker status")](https://app.wercker.com/project/bykey/2fd82aede5750d80a0e51d1df3e85835)
