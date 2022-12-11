#FROM openjdk:17-jdk
#COPY . /XMLparser-master
#WORKDIR . /XMLparser-master
#ENTRYPOINT ["java", "src\main\java\com\Main.java"]

FROM openjdk:17
COPY target/testTask-1.0-SNAPSHOT.jar xml-parser.jar
COPY information.xml .
ENTRYPOINT ["java", "-jar", "/xml-parser.jar", "config.ini"]