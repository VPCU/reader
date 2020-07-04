package com.groupt.reader.repository;

import com.groupt.reader.dto.MessageDto;
import com.groupt.reader.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findAllByUid(Long uid);
    List<MessageEntity> findAllByRvId(Long rvid);
    @Query("select new com.groupt.reader.dto.MessageDto(m, u) " +
            "from MessageEntity m, UserEntity u " +
            "where m.uid = ?1 and u.uid = m.rvId")
    List<MessageDto> getMessageDtoByUid(Long uid);
    @Query("select new com.groupt.reader.dto.MessageDto(m, u) " +
            "from MessageEntity m, UserEntity u " +
            "where m.rvId = ?1 and u.uid = m.uid")
    List<MessageDto> getMessageDtoByRvId(Long rvid);
}
