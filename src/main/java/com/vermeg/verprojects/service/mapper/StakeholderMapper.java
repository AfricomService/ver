package com.vermeg.verprojects.service.mapper;


import com.vermeg.verprojects.domain.*;
import com.vermeg.verprojects.service.dto.StakeholderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Stakeholder} and its DTO {@link StakeholderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StakeholderMapper extends EntityMapper<StakeholderDTO, Stakeholder> {


    @Mapping(target = "task", ignore = true)
    Stakeholder toEntity(StakeholderDTO stakeholderDTO);

    default Stakeholder fromId(Long id) {
        if (id == null) {
            return null;
        }
        Stakeholder stakeholder = new Stakeholder();
        stakeholder.setId(id);
        return stakeholder;
    }
}
