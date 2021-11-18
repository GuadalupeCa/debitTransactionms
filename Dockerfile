FROM openjdk:11
WORKDIR /debitTransactionms
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
EXPOSE 8085
CMD ["./mvnw", "spring-boot:run"]