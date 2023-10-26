# OpenJDK 17 이미지를 베이스로 사용
FROM tomcat:9.0.82-jre17-temurin-focal

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew	#gradlew 실행 권한 부여

RUN apt-get update && apt-get install -y nodejs npm
RUN npm install -g yarn

# Gradle을 사용하여 프로젝트 빌드
RUN ./gradlew build

# Spring Boot 애플리케이션 WAR 파일을 복사
COPY build/libs/ROOT.war $CATALINA_HOME/webapps/

