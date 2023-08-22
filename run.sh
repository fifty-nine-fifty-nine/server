#!/bin/bash

# Gradle을 사용하여 프로젝트 빌드
./gradlew clean build

# 빌드 성공 여부 확인
if [ $? -ne 0 ]; then
    echo "Build failed!"
    exit 1
fi

# 빌드된 JAR 파일 실행
java -jar build/libs/server-0.0.1-SNAPSHOT.jar

echo "Application is running!"
