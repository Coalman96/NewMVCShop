FROM openjdk:17-alpine

# Tomcat 9 다운로드 및 설치
RUN yum -y install wget && \
    wget --no-check-certificate https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.82/bin/apache-tomcat-9.0.82.tar.gz && \
    tar -xvf apache-tomcat-9.0.82.tar.gz -C /opt && \
    rm -f apache-tomcat-9.0.82.tar.gz

# Tomcat 환경 설정
ENV CATALINA_HOME /opt/apache-tomcat-9.0.82
ENV PATH $CATALINA_HOME/bin:$PATH

# Spring Boot 애플리케이션 WAR 파일을 복사
COPY build/libs/ROOT.war $CATALINA_HOME/webapps/

# 포트 8080 열기
EXPOSE 8080

# Tomcat 시작
CMD ["catalina.sh", "run"]