package com.vermeg.verprojects.service.mapper;


import com.vermeg.verprojects.domain.*;
import com.vermeg.verprojects.service.dto.IncidentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Incident} and its DTO {@link IncidentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface IncidentMapper extends EntityMapper<IncidentDTO, Incident> {



    default Incident fromId(Long id) {
        if (id == null) {
            return null;
        }
        Incident incident = new Incident();
        incident.setId(id);
        return incident;
    }
}
