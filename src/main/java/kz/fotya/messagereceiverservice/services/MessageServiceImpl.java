package kz.fotya.messagereceiverservice.services;

import kz.fotya.messagereceiverservice.domain.ReceivedMessage;
import kz.fotya.messagereceiverservice.domain.ReceivedMessageRepository;
import kz.fotya.messagereceiverservice.web.mapper.MessageMapper;
import kz.fotya.messagereceiverservice.web.model.MessageDto;
import kz.fotya.messagereceiverservice.web.model.MessageListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    @Value("${kafka.topic}")
    private String topic;
    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ReceivedMessageRepository messagesRepository;

    private final MessageMapper messageMapper;


    @Override
    public MessageListDto getMessagesBySender(String sender) {
        List<ReceivedMessage> messages = messagesRepository.findBySender(sender);
        List<MessageDto> messageDtos = messages.stream()
                .map(messageMapper::receivedMessageToDto)
                .collect(Collectors.toList());
        return new MessageListDto(messageDtos);
    }

    @Override
    public MessageListDto getListOfMessages() {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        var index = messagesRepository.count() > 10 ? 10: messagesRepository.count();
        List<ReceivedMessage> messagesList = messagesRepository.findAll(sort).subList(0, (int) index);
        List<MessageDto> messageDtos = messagesList.stream()
                .map(messageMapper::receivedMessageToDto)
                .collect(Collectors.toList());

        return new MessageListDto(messageDtos);
    }

    @Override
    public void saveMessageToDB(MessageDto messageDto) {

        messagesRepository.save(messageMapper.dtoToReceivedMessage(messageDto));
        System.out.println("Saved message: " + messageDto.toString());
        System.out.println("Count of messages saved: "+ messagesRepository.count());
    }



    @Override
    public void sendMessageToKafka(MessageDto messageDto) {
        kafkaTemplate.send(topic, messageDto.toString());
    }


}
