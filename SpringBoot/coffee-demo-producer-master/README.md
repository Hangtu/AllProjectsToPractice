# coffee-demo-producer
This is an implementation of a producer using two different ways (plain kafka and spring kafka) in a real project.

## Prerequisites
 - Have [Java 8 JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed.
 - Have [Eclipse with Spring](https://spring.io/tools3/eclipse) installed.
 - Have [Docker](https://www.docker.com/) installed and running.
 - Have [Postman](https://www.getpostman.com/) installed.
 - Have the Kafka image installed. For this, run the following command in your Command Prompt or Terminal __after__ Docker is installed and running: ``` docker pull landoop/fast-data-dev ```

## Running the project step by step
### Running Kafka
Go to the __Kafka Producer Properties__ file located in the _src/main/resources/_ directory and change the _IP_ADDRESS_ at the __bootstrap.servers__ property on line 1 to __172.17.0.2__, which is Docker's default IP Address:
```
bootstrap.servers=IP_ADDRESS:7092
```
```
bootstrap.servers=172.17.0.2:7092
```
Next, run a container for the Kafka image we installed previously, using the same IP Address we used in the __Kafka Producer Properties__:
```
docker run --rm --name kafka -it -p 3181:3181 -p 3040:3040 -p 7081:7081 -p 7082:7082 -p 7083:7083 -p 7092:7092 -e ZK_PORT=3181 -e WEB_PORT=3040 -e REGISTRY_PORT=8081 -e REST_PORT=7082 -e CONNECT_PORT=7083 -e BROKER_PORT=7092 -e ADV_HOST=172.17.0.2 landoop/fast-data-dev
```
After the container starts running, you can check if it's working properly by accessing to http://localhost:3040/ in you preferred browser. Once you have Kafka running it is neccesary create the topics for the operation of the project, open another Command Prompt window or Terminal tab and execute the command:
```
docker exec -it kafka bash
```
This command will open the container's command line interface. After executing it, type the next command to create the necessary Kafka topics:
```
kafka-topics --zookeeper IP_ADDRESS:3181 --create --topic user_demo --partitions 3 --replication-factor 1
kafka-topics --zookeeper IP_ADDRESS:3181 --create --topic coffee_demo --partitions 3 --replication-factor 1
```
In this project _user_demo_ and _coffee_demo_ are completely necessary topics for the correct working of the project.

### Building and running the project container
While the Kafka container is running, create the JAR project file on Eclipse. For this, locate the __Gradle Task__ tab in the console and run the __Build__ task.

After generating the JAR file, open the Command Prompt or Terminal and move to the project directory, then build the Docker image with the next command:
```
docker build -t coffee-demo-producer-image .
```
This code above builds an image with the tag name _coffee-demo-producer-image_. After the image is successfully built, run the Docker container with the following command:
 ```
 docker run --name coffee-demo-producer -p 8080:8080 coffee-demo-producer-image
 ```
This runs a container named _coffee-demo-producer-container_ based on the _coffee-demo-ui-image_ image and uses the port __8080__ on both the local machine and the container.

To test that the container is running correctly, you can use Postman and and run the next examples:
#### User
```
http://localhost:8080/user/1
http://localhost:8080/user/delete/1
```
Or you can go to the project's Swagger page (http://localhost:8080/swagger-ui.html) to test the users' service.

#### Coffee
```
http://localhost:8080/rest/coffee/1
http://localhost:8080/rest/coffee/delete/1
```
