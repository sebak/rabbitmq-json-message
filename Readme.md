# Spring Boot RabbitMQ Producer and Consumer JSON Message Workflow

The ***Producer*** is an application that sends messages to the RabbitMQ broker and the ***Consumer*** is an application that reads messages from the ***RabbitMQ broker***.

In this tutorial, we will implement below Spring Boot RabbitMQ flow:

![rabbitMQJSONMessage.jpeg](src%2Fmain%2Fresources%2Fimages%2FrabbitMQJSONMessage.jpeg)

### 2- Prerequisites
Docker - Install and set up RabbitMQ locally as a Docker container using Docker.  Check out my separate guide at Install RabbitMQ using Docker.

* 1-pull image of rabbitmq with is image name 3.13-management
```docker
docker pull rabbitmq:3.11.11-management
```

* 2- run the image
```docker
docker run -it --rm --name rabbit-server -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management
```
***-it (interactive mode), the name chosen is rabbit-server***
***-port 15672:17672 for web ui interface of rabbit mq accessible in localhost:15672***
***-port 5672:5672 for the broker rabbitmq***
***the default username is: guest  And password is: guest*** 

### 3- Add Maven Dependencies

```maven
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-amqp</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.amqp</groupId>
			<artifactId>spring-rabbit-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```
### 4- Connect Spring Boot Application with RabbitMQ

We will get a connection to RabbitMQ broker on port 5672 using the default username and password as “guest/guest”.
Spring boot autoconfiguration will automatically connect the Spring boot application with RabbitMQ using default configuration details but you can modify them as per the environment in an application.properties file:

```properties
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
```

### 5- Configure RabbitMQ in Spring Boot Application in RabbitMQConfig.java

### Make sure to add the following properties to the application.properties file
```properties
spring.rabbitmq.exchange.name=pw_exchange
spring.rabbitmq.queue.json.name=pw_json
spring.rabbitmq.routing.json.key=pw_routing_json_key
```
### 6- Create POJO Class to Serialize/Deserialize in User.java
### 7- Create RabbitMQ Producer in RabbitMQJsonProducer.java
We are going to use RabbitTemplate to convert and send a message using RabbitMQ. It is a helper class, like many other Template classes existing in Spring (such as JdbcTemplate, KafkaTemplate, etc).

### 8- Create REST API to Send JSON Message to the RabbitMQ producer in MessageJsonController.java
* Test REST API using Postman

```link
http://localhost:8080/api/v1/publish
```
***payload***
```json
"id": 1,
"firstName": "Ramesh",
"lastName": "Fadatare"
```
### 9- Create RabbitMQ Consumer in RabbitMQJsonConsumer.java
RabbitMQ Consumer is the service that will be responsible for reading JSON messages and processing them according to the needs of your own business logic.

We configure consumers using the ***@RabbitListener*** annotation. The only argument passed here is the queues' name. Consumers are not aware here of exchanges or routing keys.
The ***@RabbitListener*** will trigger a logic inside Spring to find a converter from JSON to that specific class.