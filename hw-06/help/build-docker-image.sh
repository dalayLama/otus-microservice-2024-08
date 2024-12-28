#!/bin/bash

docker build -f Dockerfile.app -t jimbeam777/otus-auth:0.0.1 .
docker build -f Dockerfile.migration -t jimbeam777/otus-auth-migration:0.0.1 .

docker build -f Dockerfile.app -t jimbeam777/otus-gateway:0.0.1 .

docker build -f Dockerfile.app -t jimbeam777/otus-profile:0.0.1 .
docker build -f Dockerfile.migration -t jimbeam777/otus-profile-migration:0.0.1 .