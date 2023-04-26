FROM maven:3.9.0 as build
COPY . .
RUN mvn clean package

FROM openjdk:17-alpine
COPY --from=build ./target/SeeTax-0.0.2.jar ./app/demo.jar
WORKDIR /app
ENTRYPOINT [ "java", "-jar", "demo.jar" ]
EXPOSE 8080
