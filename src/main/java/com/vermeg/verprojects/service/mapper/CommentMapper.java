package com.vermeg.verprojects.service.mapper;


import com.vermeg.verprojects.domain.*;
import com.vermeg.verprojects.service.dto.CommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comment} and its DTO {@link CommentDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {

    @Mapping(source = "task.id", target = "taskId")
    CommentDTO toDto(Comment comment);

    @Mapping(source = "taskId", target = "task")
    Comment toEntity(CommentDTO commentDTO);

    default Comment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(id);
        return comment;
    }
}
