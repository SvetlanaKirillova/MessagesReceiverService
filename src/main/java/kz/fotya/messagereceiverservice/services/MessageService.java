package kz.fotya.messagereceiverservice.services;

import kz.fotya.messagereceiverservice.web.model.MessageDto;
import kz.fotya.messagereceiverservice.web.model.MessageListDto;

public interface MessageService {
    public MessageListDto getMessagesBySender(String sender);

    void saveMessageToDB(MessageDto messageDto);
    void sendMessageToKafka(MessageDto messageDto);

    MessageListDto getListOfMessages();
}
