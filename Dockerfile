FROM maven:3.8.2-jdk-11 AS build

COPY . .
RUN mvn clean package -Pprod -DskipTests

FROM openjdk:11-jdk-slim
COPY --from=build /target/readingisgood-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=50500
EXPOSE 50500

ENTRYPOINT ["java","-jar","app.jar"]