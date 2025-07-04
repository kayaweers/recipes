# Recipes app

## How to run?
Postgres database:
- Start the podman machine (podman machine start [machineName])
- go to /recipes/postgres folder, run: podman compose up
- (only for new container) go to pgadmin (http://localhost:5431), log in with credentials (docker-compose file)
- (only for new container) create server ('postgres'), create schema ('recipes')

Application: 
- Start application: mvn spring-boot:run (will run liquibase scripts)

## How to access?
- application is running at: localhost:8080
- check if application is running: localhost:8080/recipes/info

