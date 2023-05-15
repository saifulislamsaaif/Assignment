# Assignment

## Dependency

JDK 17 must be installed in local computer.

`docker` and `docker-compose` must be installed in local computer in order to run this project

## Build instruction

Go to individual projects under root folder and run the following command:

```bash
./gradlew jibDockerBuild
```
## Run instruction

open a terminal/powershell on the root folder and run the following command:

```bash
docker-compose -f docker-compose.yml up -d
```

## Testing instruction
Import Assignment.postman_collection.json in Postman or https://www.postman.com/saifulislamsaaif/workspace/assignment/
