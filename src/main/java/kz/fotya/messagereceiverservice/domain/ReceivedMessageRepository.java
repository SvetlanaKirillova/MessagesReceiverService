package kz.fotya.messagereceiverservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceivedMessageRepository extends JpaRepository<ReceivedMessage, Long> {

    @Query("SELECT msg FROM ReceivedMessage msg WHERE msg.sender = ?1")
    List<ReceivedMessage> findBySender(String sender);

}
