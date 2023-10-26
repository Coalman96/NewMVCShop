# OpenJDK 17 이미지를 베이스로 사용
FROM eclipse-temurin:17-jdk-jammy

RUN whereis java
RUN find /usr/lib/jvm/java*


ENV JAVA_HOME=/opt/java/openjdk/bin PATH=/opt/java/openjdk/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
RUN chmod +x ./gradlew	#gradlew 실행 권한 부여

# Gradle을 사용하여 프로젝트 빌드
RUN ./gradlew build

# Spring Boot 애플리케이션 WAR 파일을 복사
COPY build/libs/ROOT.war $CATALINA_HOME/webapps/

# 8080 포트 노출
EXPOSE 8080

# Tomcat 서비스 시작
CMD ["catalina.sh", "run"]
