@echo off
setlocal

set MAVEN_VERSION=3.8.4
set BASE_URL=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MAVEN_VERSION%/apache-maven-%MAVEN_VERSION%-bin.zip
set MAVEN_HOME=%~dp0\.mvn\wrapper\maven-wrapper.jar

if not exist "%MAVEN_HOME%" (
    mkdir ".mvn\wrapper"
    powershell -Command "Invoke-WebRequest -Uri https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar -OutFile '%MAVEN_HOME%'"
    powershell -Command "Invoke-WebRequest -Uri https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/0.5.6/maven-wrapper.properties -OutFile '.mvn\wrapper\maven-wrapper.properties'"
)

java -jar "%MAVEN_HOME%" %*