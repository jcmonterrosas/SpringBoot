FROM        maven:3.6.3-openjdk-8 as compiler
COPY        ./ /root/users
WORKDIR     /root/users/
RUN         mvn clean package -DskipTests

FROM        openjdk:8-jdk
RUN         mkdir /root/app
COPY        --from=compiler /root/users/target/users-1.jar /root/app/users-1.jar
ENTRYPOINT  ["java", "-jar", "/root/app/users-1.jar"]
EXPOSE      8080
