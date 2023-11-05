# OpenJDK 17 이미지를 베이스로 사용
FROM tomcat:9.0.82-jdk17-temurin-focal

RUN pwd

# WAR 파일을 복사하여 Tomcat 웹 애플리케이션 폴더로 이동
#COPY build/libs/ROOT.war /usr/local/tomcat/webapps/
COPY build/libs/MVCshop.jar /usr/local/tomcat/
EXPOSE 8001

CMD ["java", "-jar", "MVCshop.jar"]
