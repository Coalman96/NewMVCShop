# OpenJDK 17 이미지를 베이스로 사용
FROM tomcat:9.0.82-jdk17-temurin-focal

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew	# gradlew 실행 권한 부여

# Node.js 및 npm 설치
RUN apt-get update && apt-get install -y curl
RUN curl -fsSL https://deb.nodesource.com/gpgkey/nodesource.gpg.key | apt-key add -
RUN sh -c "echo deb https://deb.nodesource.com/setup_21.x $(lsb_release -cs) main > /etc/apt/sources.list.d/nodesource.list"
RUN sh -c "echo deb-src https://deb.nodesource.com/setup_21.x $(lsb_release -cs) main >> /etc/apt/sources.list.d/nodesource.list"
RUN apt-get update
RUN apt-get install -y nodejs

# Yarn 설치
RUN npm install -g yarn

WORKDIR src/main/reactfront
RUN yarn install
RUN yarn build

# Gradle을 사용하여 프로젝트 빌드
WORKDIR /usr/local/tomcat
RUN ./gradlew build

# Spring Boot 애플리케이션 WAR 파일을 복사
COPY build/libs/ROOT.war $CATALINA_HOME/webapps/
