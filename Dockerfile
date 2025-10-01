
FROM gradle:8.7.0-jdk21 AS BUILD
WORKDIR /usr/app

COPY . .


RUN chmod +x ./gradlew

RUN ./gradlew build -x test


FROM eclipse-temurin:21-jdk
WORKDIR /app

COPY --from=BUILD /usr/app/build/libs/Sptrint1-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]