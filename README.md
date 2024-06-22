# ISM

ISM is an microservices for management of student, classrooms, and grades with additional roles, and user login functionalities.
## Features

- **Student Management:** Efficiently add, update, and delete student records with ease.
- **User Management:** Seamlessly manage user accounts and permissions for enhanced control.
- **Classroom Management:** Easily add, update, and delete and classroom records with ease.
- **Grade Management:** Easily add, update, and delete and grades level for each classrooms records with ease.

## Technologies Used

- **Java 17**
- **Spring Boot:** A Java backend framework facilitating rapid development of Java applications. The dependencies I use for this stacks: Eureka for server discovery, Config for SpringBoot Config Servers, and Gateway for API-Gateway
- **PostgreSQL:** Utilized as the primary relational database management system for storing data.
- **Docker:** Leveraged for containerization, enabling seamless deployment and scaling of the application across various environments.

## Prerequisites
1. Java 17
2. Maven
3. Docker with Docker Compose
4. Active Internet Connection

## How To Run

1. Initialize the docker configs:
```
    $ chmod +x start.sh
    $ ./start.sh 
```
There will be interactive user input (just press y/Y) to configure the docker environment if you are first time running the script. 
I recommend you to run the script with `./start.sh` because you have to configure specific application-local.properties for each project before you run the app locally.

2. Start the stacks:
```
    $ ./start.sh 
```
I recommend you to wait for about 2-3 minutes for the server to start up. because it will take some time for the microservices to start up.

3. Stop the Apps:
```
    $ ./start.sh stop
```
4. Cleanup the docker environment
```
    $ ./start.sh teardown
```
## API Collection

- **Postman Collection:** Postman collection can be found on postman folders

## Dev Notes
 This Microservies already tested on Linux, Linux WSL and Mac.If you are running Windows, I recommend you to use WSL or any other Linux-based environment to run bash script.

 
 Also, I provide the database schema and sample data for the project in the db/migrations folder, but I recommend to just use the bash script, because it not only will create the containers, but also generate the schema (without data).


 If you have an issue with containers installation, just remove .docker folder and start again.

## Future Tasks

- **Code Refactoring:** Enhance codebase by cleaning redundant or outdated code segments and optimizing performance.
- **Endpoint Management:** Streamline and organize endpoint prefixes to improve clarity and maintainability.
- **Add Services:** Add services to manage exams, homeroom teachers, student attendances, and more.

### Additional Tech Stacks (Future Expansion)

- **Message Queue:** Utilized for sending and receiving messages between microservices, and allows for asynchronous processing.
- **Kubernetes:** Adopting Kubernetes for container orchestration will enhance scalability, reliability, and deployment automation. Kubernetes provides a platform for managing containerized applications across clusters of hosts. Further exploration is necessary to understand Kubernetes concepts, such as pods, services, and deployments, and their application in deploying and managing microservices-based applications.

Feel free to reach out for further discussions or contributions to the project's development. Your feedback and collaboration are highly appreciated.

