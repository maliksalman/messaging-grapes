# Messaging spring-boot applications

## 1. grapes-source

This sample spring-boot application produces 'grape' messages into RabbitMQ topic(s) using spring-cloud-stream abstraction. Also demonstrates producing multiple types of message on a single topic. Use the `grapes-sink` app to consume the 'grape' messages.

### 1.1 Building

```
./gradlew clean build
```

### 1.2 Running

```
java -jar build/libs/grapes-source-0.0.1-SNAPSHOT.jar
```

### 1.3 Triggering messages

After the the source and sink applications are running, trigger this REST endpoint to send one or more messages in the 'single' channel. Play around with the start and end index numbers to effect how many messages are sent. However many messages are sent, they are all of the same kind since that is what the 'single' channel is for. Call `curl -X POST localhost:8080/generate/{start}/{end}`. For example, `curl -X POST localhost:8080/generate/101/102`

Trigger this REST endpoint to send a message in the 'multi' channel. Play around with the color to change what kind of message is sent. The sink application only knows how to handle `red` and `green` grape messages. Any other color grapes will be received but ignored. Call `curl -X POST localhost:8080/generate/{color}`. For example, `curl -X POST localhost:8080/generate/red`

## 2. grapes-sink

This sample spring-boot application consumes 'grape' messages from RabbitMQ topic(s) using the spring-cloud-stream abstraction.  In addition, it also shows how to use headers to consumer multiple type of events from a single topic. Use the `grapes-source` to generate different kind of 'grape' messages.      

### 2.1 Building

```
./gradlew clean build
```

### 2.2 Running

```
java -jar build/libs/grapes-sink-0.0.1-SNAPSHOT.jar
```

## 3. Running pre-requisites

These sample applications need a running instance of RabbitMQ or Kafka to function. Since the apps use spring-cloud-stream abstraction, it can potentially work with any of the spring-cloud-stream 'binders' but was only tested with RabbitMQ and Kafka. 

## 3.1 Running with RabbitMQ (in docker)

The following command will start RabbitMQ runnning on port 5672 with the admin console availabe at [http://localhost:15672](http://localhost:15672)

```
docker run --rm -d -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

The username/password to access this RabbitMQ dashboard instance would be `guest`/`guest`

## 3.2 Running with Kafka (in docker)

Equally convenient way to test the applications is with running Kafka/Zookeeper in docker. The following command will start Kafka/Zookeeper running on ports 9092 (kafka) and 2181 (zookeeper). When running this way, start the applications with `-Dspring.profiles.active=kafka` flag. Also, there is no UI to interact/debug with kafka. 

```
docker run --rm -d -p 2181:2181 -p 9092:9092 -e ADVERTISED_HOST={{{YOUR_HOSTNAME_HERE}}} maliksalman/kafka-dev:2.4.1
```
