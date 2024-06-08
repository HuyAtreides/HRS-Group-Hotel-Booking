FROM openjdk:17-jdk-oracle

WORKDIR /usr/hrs

COPY ./build/libs/hotelbooking-0.0.1-SNAPSHOT.jar ./

CMD java -Dspring.profiles.active=prod -jar /usr/hrs/hotelbooking-0.0.1-SNAPSHOT.jar