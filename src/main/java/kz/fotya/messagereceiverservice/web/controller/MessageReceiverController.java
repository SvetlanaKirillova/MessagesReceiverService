package kz.fotya.messagereceiverservice.web.controller;

import kz.fotya.messagereceiverservice.services.MessageService;
import kz.fotya.messagereceiverservice.web.model.MessageDto;
import kz.fotya.messagereceiverservice.web.model.MessageListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageReceiverController {
    private final MessageService messageService;


    public MessageReceiverController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public ResponseEntity<MessageListDto> handleGetAllMessages(){
        MessageListDto messagesList = messageService.getListOfMessages();

        return ResponseEntity.ok(messagesList);

    }

    @GetMapping("/{sender}")
    public ResponseEntity<MessageListDto> handleGetMessageBySender(@PathVariable String sender){
        MessageListDto messageListDto = messageService.getMessagesBySender(sender);

        return ResponseEntity.ok(messageListDto);

    }

    @PostMapping
    public ResponseEntity<String> handlePost(@RequestBody MessageDto messageDto){
        System.out.println("Request received" + messageDto);
        messageService.saveMessageToDB(messageDto);
        messageService.sendMessageToKafka(messageDto);
        return ResponseEntity.ok("Message received and sent to Kafka");
    }
}
