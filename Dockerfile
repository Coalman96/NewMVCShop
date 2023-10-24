# 기본 이미지로부터 시작
FROM openjdk:17-alpine

# Tomcat 9 다운로드 및 설치
RUN apk add --no-cache curl && \
    curl -O https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.82/bin/apache-tomcat-9.0.82.tar.gz && \
    tar xvf apache-tomcat-9.0.82.tar.gz && \
    mv apache-tomcat-9.0.82 /usr/local/tomcat && \
    rm -f apache-tomcat-9.0.82.tar.gz

# Tomcat 환경 변수 설정
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH

# Spring Boot 애플리케이션 WAR 파일을 복사
COPY build/libs/ROOT.war $CATALINA_HOME/webapps/

# 포트 8080을 노출
EXPOSE 8080

# Tomcat 시작
CMD ["catalina.sh", "run"]
