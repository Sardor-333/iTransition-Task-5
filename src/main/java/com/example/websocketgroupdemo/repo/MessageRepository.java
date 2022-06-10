package com.example.websocketgroupdemo.repo;

import com.example.websocketgroupdemo.entity.Message;
import com.example.websocketgroupdemo.projection.MessageProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(
            nativeQuery = true,
            value = """
                    select m.id as id,
                           m.title as title,
                           m.body as body,
                           m.sender_id as senderId,
                           m.receiver_id as receiverId,
                           to_char(m.created_at, 'DD Mon, YYYY | HH24:MI') as sentAt
                    from messages m
                    where m.id = :messageId
                    """
    )
    MessageProjection getMessageProjection(@Param("messageId") Long messageId);

    @Query(
            nativeQuery = true,
            value = "select m.id                                      as id, " +
                    "                           m.title as title, " +
                    "                           m.body as body, " +
                    "                           m.sender_id                               as senderId,\n" +
                    "                           m.receiver_id                             as receiverId,\n" +
                    "                           to_char(m.created_at, 'DD Mon, YYYY | HH24:MI') as sentAt\n" +
                    "                    from messages m\n" +
                    "                    where m.chat_id = :chatId\n" +
                    "                    order by m.created_at"
    )
    List<MessageProjection> getAllMessagesByChatId(@Param("chatId") UUID chatId);
}