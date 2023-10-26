# OpenJDK 17 이미지를 베이스로 사용
FROM tomcat:9.0.82-jdk17-temurin-focal

#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle .
#COPY settings.gradle .
#COPY src src
RUN pwd
WORKDIR /
RUN chmod +x ./gradlew	# gradlew 실행 권한 부여

# Node.js 및 npm 설치
RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash -
RUN apt-get update && apt-get install -y nodejs

# Yarn 설치
RUN npm install -g yarn

WORKDIR src/main/reactfront
RUN yarn install
RUN yarn build

# Gradle을 사용하여 프로젝트 빌드
WORKDIR /
RUN ./gradlew build
RUN find / -type f -name "*.war" -exec ls -l {} \;
# WAR 파일을 복사하여 Tomcat 웹 애플리케이션 폴더로 이동
COPY build/libs/*.war usr/local/tomcat/webapps/

CMD ["./bin/catalina.sh", "run"]
