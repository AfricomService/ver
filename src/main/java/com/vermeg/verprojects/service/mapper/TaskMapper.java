package com.vermeg.verprojects.service.mapper;


import com.vermeg.verprojects.domain.*;
import com.vermeg.verprojects.service.dto.TaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Task} and its DTO {@link TaskDTO}.
 */
@Mapper(componentModel = "spring", uses = {StakeholderMapper.class, ProjectMapper.class, ResourceMapper.class})
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {

    @Mapping(source = "stakeholder.id", target = "stakeholderId")
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "resource.id", target = "resourceId")
    TaskDTO toDto(Task task);

    @Mapping(source = "stakeholderId", target = "stakeholder")
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "removeComments", ignore = true)
    @Mapping(source = "projectId", target = "project")
    @Mapping(source = "resourceId", target = "resource")
    Task toEntity(TaskDTO taskDTO);

    default Task fromId(Long id) {
        if (id == null) {
            return null;
        }
        Task task = new Task();
        task.setId(id);
        return task;
    }
}
