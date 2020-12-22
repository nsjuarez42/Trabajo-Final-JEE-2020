@echo off
call mvn clean package
call docker build -t com.example.ahorcado/Ahorcado .
call docker rm -f Ahorcado
call docker run -d -p 9080:9080 -p 9443:9443 --name Ahorcado com.example.ahorcado/Ahorcado