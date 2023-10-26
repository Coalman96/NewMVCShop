FROM eclipse-temurin:17-jdk-jammy

# 작업 디렉토리 설정
WORKDIR /app

# Gradle Wrapper 스크립트를 복사
COPY test3 /app

# Gradle Wrapper 스크립트에 실행 권한 부여
RUN chmod +x /app/gradlew

# Gradle을 사용하여 프로젝트 빌드
RUN /app/gradlew build

# Spring Boot 애플리케이션 WAR 파일을 복사
COPY build/libs/ROOT.war $CATALINA_HOME/webapps/

# 8080 포트 노출
EXPOSE 8080

# Tomcat 서비스 시작
CMD ["catalina.sh", "run"]