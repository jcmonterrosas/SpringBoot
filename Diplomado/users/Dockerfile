FROM        maven:3.6.3-openjdk-8 as compiler
COPY        ./ /root/users
WORKDIR     /root/users/
RUN         mvn clean package -DskipTests

FROM        openjdk:8-jdk
RUN         mkdir /root/app
COPY        --from=compiler /root/users/target/users-0.0.1-SNAPSHOT.jar /root/app/users-0.0.1-SNAPSHOT.jar
ENTRYPOINT  ["java", "-jar", "/root/app/users-0.0.1-SNAPSHOT.jar"]
EXPOSE      8080