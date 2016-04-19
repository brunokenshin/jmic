# Java Mail Info Collector - JMIC
JMIC is a simple webapp to extract important information from emails using specific patterns.

Project uses Java 8, Spring Boot as the core framework application, gradle 2.12 as building tool, Maven Central repositories for downloading dependencies.

# Dependencies:
- org.springframework.boot:spring-boot-starter-mail
- org.springframework.boot:spring-boot-starter-web
- org.springframework.boot:spring-boot-starter-tomcat
- org.springframework.boot:spring-boot-starter-test

# Filters configuration:
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

# Important:
For google accounts maybe you need to active the "Less secure apps" option:
- https://www.google.com/settings/security/lesssecureapps

# References:
- http://www.compiletimeerror.com/2013/06/reading-email-using-javamail-api-example.html#.VvBXfHyrRD8
- http://www.oracle.com/technetwork/java/javamail/faq/index.html#gmail
- https://gusto77.wordpress.com/2012/04/03/extracting-text-from-email-message-with-javamail/
- http://stackoverflow.com/questions/870045/java-imap-fetch-messages-since-a-date
