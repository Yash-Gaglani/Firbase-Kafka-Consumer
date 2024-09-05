
# Kafka Consumer Microservice for Firebase Notifications

This microservice is designed to offload notification processing from the main application by consuming messages from Apache Kafka and sending Firebase notifications to devices via their FCM (Firebase Cloud Messaging) token.

## Features

- Listens to Kafka topic (`firebase-notification` by default).
- Consumes messages containing `fcmToken` and `notification` details.
- Sends push notifications to specified devices using Firebase.
- Configurable via `application.properties` and `ApplicationConstants.java`.
- Designed to reduce load on the main application by handling notifications separately.

## Prerequisites

- Java 8+
- Spring Boot (for microservice framework)
- Apache Kafka (running on the specified `bootstrap-servers`)
- Firebase Project (with an FCM credential file)

### Dependencies

- Spring Kafka
- Firebase Admin SDK
- Jackson (for JSON deserialization)

## Configuration

### Kafka Properties

The Kafka-related configurations are stored in `application.properties`:

```properties
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=consumerGroup
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.enable-auto-commit=true
```

### Firebase Credentials

Store the Firebase service account credentials in the `resources` folder. For example, you might name the file `firebase-credentials.json`.

In your `ApplicationConstants.java`, specify the name of this file:

```java
public static final String FIREBASE_CREDENTIALS_FILE = "firebase-credentials.json";
```

### Application Constants

In `ApplicationConstants.java`, update the Kafka topic and consumer group settings:

```java
public static final String NOTIFICATION_TOPIC = "firebase-notification";
public static final String CONSUMER_GROUP = "firebase-consumer-group";
```

You can change these values to suit your requirements.

## Message Structure

The Kafka messages consumed by this service must follow this structure:

```json
{
  "fcmToken": "dP7Lf3FSMEP8oGw6MxuZiI:APA91bH3alkTDpOm575TArk5s22pRjpqNq8XR-6mRP4Cw6NzDhDMl_u7_j6Jz_JSy1JA4xwtxrFUUsAK_C_2WAg-6BNG753saff5y0fUPl0XjR8oZSNM5aAKXSQG1qxinxYR5TWlR00S",
  "notification": {
    "subject": "New Appointment",
    "content": "You have a new appointment.",
    "data": {
      "key": "value"
    }
  }
}
```

- `fcmToken`: The device token to which the notification will be sent.
- `notification`: The notification payload.
  - `subject`: The notification title.
  - `content`: The notification body.
  - `data`: Custom data to include in the notification (optional).

## Running the Application

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/your-repo/kafka-firebase-notification-service.git
    cd kafka-firebase-notification-service
    ```

2. **Add Firebase Credentials**:
   - Place your `firebase-credentials.json` file in the `src/main/resources` directory.

3. **Configure Kafka**:
   - Update the Kafka settings in `application.properties` as needed.

4. **Run the Application**:
   - Use Maven or Gradle to run the application:
     ```bash
     mvn spring-boot:run
     ```

The service will run on port `8081` by default.

## Endpoints

This microservice does not expose any REST endpoints, as it is a Kafka consumer and works based on Kafka messages.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
