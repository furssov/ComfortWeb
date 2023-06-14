#!/bin/bash

export PATH=/opt/apache-maven-3.8.5/bin/:$PATH

mvn clean package

cd target
mv ComfortWeb-0.0.1-SNAPSHOT.jar app.jar
mv ./app.jar /home/user/projects/idea/ComfortWeb/
cd..

docker compose up --build -d
