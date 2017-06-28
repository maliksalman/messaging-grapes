# Messaging spring-boot applications

## 1. grapes-source

This sample spring-boot application produces 'grape' messages into RabbitMQ topic(s) using spring-cloud-streams abstraction. It demonstrates how to setup serialization for date/time objects to JSON - which is the format it produces the 'grape' messages in. Also demenstrates producing multiple types of message on a single topic. Use the `grapes-sink` app to consume the 'grape' messages.      

### 1.1 Building

```
./gradlew clean build
```

### 1.2 Running

```
java -jar build/libs/grapes-source-0.0.1-SNAPSHOT.jar
```

To format dates properly and skip empty elements while generating messages, the app includes custom Jackson JSON configuration which is enabled by default. To demonstrate how unwieldy the messages become without it, it can be disabled through a config paramter. Remember to disable the same config on the sink application as well. To run with this configuration disabled, run like so:

```
java -Djackson.config.enabled=false -jar build/libs/grapes-source-0.0.1-SNAPSHOT.jar
```

### 1.3 Triggering messages

After the the source and sink applications are running, trigger this REST endpoint to send one or more messages in the 'single' channel. Play around with the start and end index numbers to effect how many messages are sent. However many messages are sent, they are all of the same kind since that is what the 'single' channel is for. 

Call `curl -X POST localhost:8080/generate/{start}/{end}`. For example, `curl -X POST localhost:8080/generate/101/102`

Trigger this REST endpoint to send a message in the 'multi' channel. Play around with the color to change what kind of message is sent. The sink application only knows how to handle `red` and `green` grape messages. Any other color grapes will be received but ignored. 

Call `curl -X POST localhost:8080/generate/{color}`. For example, `curl -X POST localhost:8080/generate/red`

## 2. grapes-sink

This sample spring-boot application consumes 'grape' messages from RabbitMQ topic(s) using the spring-cloud-streams abstraction. It shows how to properly consume messages using either the `MessageHandler` or `@StreamListener` mechanisms exposed by spring-cloud-streams. It also demonstrates how to setup deserialization for date/time objects from JSON - which is the format it expects the 'grape' messages on the topics to be in. In addition, it also shows how to use headers to consumer multiple type of events from a single topic. Use the `grapes-source` to generate different kind of 'grape' messages.      

### 2.1 Building

```
./gradlew clean build
```

### 2.2 Running

```
java -jar build/libs/grapes-sink-0.0.1-SNAPSHOT.jar
```

To deal with dates properly and be a bit more forgiving with missing attribues and objects while deserializing messages, the app includes custom Jackson JSON configuration which is enabled by default. To match the similarly configured source application, the jackson configuration can be disabled through a config paramter. To run it this way, do this:

```
java -Djackson.config.enabled=false -jar build/libs/grapes-sink-0.0.1-SNAPSHOT.jar
```

There are two ways of consuming the 'single' grape messages. By default, the `@StreamListener` mechanism is used. The functionality is exactly the same with `MessageHandler` and `@StreamListener`. The later approach is just a bit more concise and easier to read. If you want to instead use the `MessageHandler` mechanism to consume the 'single' grape messages, run like so:

```
java -Duse.message.listener=false -jar build/libs/grapes-sink-0.0.1-SNAPSHOT.jar
```

All 'multi' grape messages are handled using the `@StreamListener` mechanism regardless of `use.message.listener` configuration since it was easier to do it this way.
