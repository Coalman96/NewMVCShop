# OpenJDK 17 이미지를 베이스로 사용
FROM tomcat:9.0.82-jdk17-corretto-al2


COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew	#gradlew 실행 권한 부여

# Gradle을 사용하여 프로젝트 빌드
RUN ./gradlew build

# Spring Boot 애플리케이션 WAR 파일을 복사
COPY build/libs/ROOT.war $CATALINA_HOME/webapps/

