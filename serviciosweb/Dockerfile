FROM        maven:3.6.3-openjdk-8 as compiler
COPY        ./ /root/guia
WORKDIR     /root/guia/
RUN         mvn clean package -DskipTests

FROM        openjdk:8-jdk
RUN         mkdir /root/app
COPY        --from=compiler /root/guia/target/serviciosweb-1.jar /root/app/serviciosweb-1.jar
ENTRYPOINT  ["java", "-jar", "/root/app/serviciosweb-1.jar"]
EXPOSE      8080
