#!/bin/sh
mvn clean package && docker build -t com.example.ahorcado/Ahorcado .
docker rm -f Ahorcado || true && docker run -d -p 9080:9080 -p 9443:9443 --name Ahorcado com.example.ahorcado/Ahorcado