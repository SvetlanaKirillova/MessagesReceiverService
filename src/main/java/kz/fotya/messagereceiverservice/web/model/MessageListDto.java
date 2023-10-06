package kz.fotya.messagereceiverservice.web.model;

import org.springframework.data.domain.PageImpl;

import java.util.List;

public class MessageListDto extends PageImpl<MessageDto> {
    public MessageListDto(List<MessageDto> content) {
        super(content);
    }

}
