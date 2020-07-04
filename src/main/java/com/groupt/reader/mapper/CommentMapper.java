package com.groupt.reader.mapper;

import com.groupt.reader.dto.CommentDto;
import com.groupt.reader.model.CommentEntity;
import com.groupt.reader.model.UserEntity;

public class CommentMapper {
    public static CommentDto commentToCommentDto(CommentEntity commentEntity) {
        CommentDto dto = new CommentDto();
        UserEntity creator = commentEntity.getCreator();
        dto.setId(commentEntity.getCmtId());
        dto.setContent(commentEntity.getContent());
        dto.setCreateTime(commentEntity.getCreateTime());
        dto.setCreatorId(creator.getUid());
        dto.setCreatorUserName(creator.getUname());
        dto.setCreatorUserNick(creator.getNick());
        dto.setCreatorUserResume(creator.getResume());
        dto.setFatherCommentId(commentEntity.getFtCmtId());
        dto.setImgSrc(creator.getImgSrc());
        return dto;
    }
}
