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


* REST Controller for managing messages.

  @RestController
  @RequestMapping("/api/messages")
  public class MessageReceiverController {
  private final MessageService messageService;


  

    * Handles a GET request to retrieve a list of messages.

    * @return A {@link ResponseEntity} containing a {@link MessageListDto} with messages.
      
      @GetMapping("/")
      public ResponseEntity<MessageListDto> handleGetAllMessages() {

      // Return a ResponseEntity with the list of all messages.
      return ResponseEntity.ok(messagesList);
      }

    * Handles a GET request to retrieve a list of messages by sender.
    *
    * @param sender The sender's name to filter messages.
    * @return A {@link ResponseEntity} containing a {@link MessageListDto} with messages from the specified sender.
      */
      @GetMapping("/{sender}")
      public ResponseEntity<MessageListDto> handleGetMessageBySender(@PathVariable String sender) {

      // Return a ResponseEntity with the list of messages for requested sender.
      return ResponseEntity.ok(messageListDto);
      }



    * Handles a POST request to save and send a message.
    *
    * @param messageDto The {@link MessageDto} containing the message details.
    * @return A {@link ResponseEntity} with a success message.
      */
      @PostMapping
      public ResponseEntity<String> handlePost(@RequestBody MessageDto messageDto) {


      // Save the message to the database and send it to Kafka.
      messageService.saveMessageToDB(messageDto);
      messageService.sendMessageToKafka(messageDto);

      // Return a ResponseEntity with a success message.
      return ResponseEntity.ok("Message received and sent to Kafka");
      
