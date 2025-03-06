#!/bin/sh
set -e

MAVEN_VERSION=3.8.4
MAVEN_HOME=$(pwd)/.mvn/wrapper/maven-wrapper.jar

if [ ! -f "$MAVEN_HOME" ]; then
  mkdir -p .mvn/wrapper
  curl -o "$MAVEN_HOME" "https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar"
  curl -o ".mvn/wrapper/maven-wrapper.properties" "https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/0.5.6/maven-wrapper.properties"
fi

exec java -jar "$MAVEN_HOME" "$@"