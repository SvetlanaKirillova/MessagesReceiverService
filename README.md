## Messages Receiver Service(Kafka Producer)


## Server Parameters
- **host:** `localhost`
- **Port:** `8080`

## Configure Kafka Server

- **spring.kafka.bootstrap-servers:** `specify here kafka server host/port`
- **kafka.topic:** `kafka topic name`

## Configure PosgreSQL parameters in application.properties: 

- **your.db**  – postreSQL DataBase
- **db.user** – postgreSQL username
- **db.user.password** – posgreSQL user's password

## Rest Methods Docs


### Get All Messages

- **Endpoint:** `GET /api/messages/`
- **Description:** Retrieve a list of all messages.
- **Response:** Returns a list(max 10) of last added messages in the response body as a `MessageListDto`.

### Get Messages by Sender

- **Endpoint:** `GET /api/messages/{sender}`
- **Description:** Retrieve a list of messages sent by a specific sender.
- **Request Parameter:**
  - `{sender}`: The sender's name to filter messages by.
- **Response:** Returns a list of messages from the specified sender in the response body as a `MessageListDto`.

### Send a Message

- **Endpoint:** `POST /api/messages`
- **Description:** Save and send a new message.
- **Request Body:** A XML object containing message details in the format of a `MessageDto`.
- **Response:** Returns a success message if the message is received and sent to Kafka successfully.
