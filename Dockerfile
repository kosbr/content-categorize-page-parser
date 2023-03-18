FROM openjdk:17-jdk-alpine
COPY build/libs/page-parser.jar /deployments/page-parser.jar
USER 185
ENV JAVA_OPTS="-Xmx512m"
ENTRYPOINT ["java","-jar","/deployments/page-parser.jar"]