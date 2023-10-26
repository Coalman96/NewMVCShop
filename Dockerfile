FROM adoptopenjdk:17-jre

# Tomcat 다운로드 및 설치
RUN mkdir usr/local/tomcat
WORKDIR usr/local/tomcat
RUN wget https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.82/bin/apache-tomcat-9.0.82.tar.gz
RUN tar -xvf apache-tomcat-9.0.82.tar.gz
RUN mv apache-tomcat-9.0.82/* .
RUN rmdir apache-tomcat-9.0.82
RUN rm apache-tomcat-9.0.82.tar.gz

# Gradle을 사용하여 프로젝트 빌드
RUN ./gradlew build

# Spring Boot 애플리케이션 WAR 파일을 복사
COPY build/libs/ROOT.war $CATALINA_HOME/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]
