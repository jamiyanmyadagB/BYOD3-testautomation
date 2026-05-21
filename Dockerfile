FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /workspace

COPY . .

CMD ["mvn", "-f", "grade-tests/pom.xml", "test"]