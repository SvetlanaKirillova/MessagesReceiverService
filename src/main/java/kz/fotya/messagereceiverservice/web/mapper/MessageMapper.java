package kz.fotya.messagereceiverservice.web.mapper;

import kz.fotya.messagereceiverservice.domain.ReceivedMessage;
import kz.fotya.messagereceiverservice.web.model.MessageDto;
import org.mapstruct.Mapper;

@Mapper
public interface MessageMapper {
    MessageDto receivedMessageToDto(ReceivedMessage receivedMessage);
    ReceivedMessage dtoToReceivedMessage(MessageDto messageDto);
}
