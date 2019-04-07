#Dockerized Bookstrap Jenkins
A bootstrap dockerized jenkins setup for repeatable setup of Jenkins

##Helpful commands used during development
Docker command to clean up all docker containers
```bash
docker rm $(docker ps -a -q)
```

Docker command to clean up all docker images
```bash
docker rmi $(docker images -a -q)
```

Docker build with "password" passed in for default admin user
```bash
docker build --build-arg password=admin -t dockerized-bootstrap-jenkins:blog3 .
```

Docker run command
```bash
docker run -p 8080:8080 -p 50000:50000 dockerized-bootstrap-jenkins:blog3
```