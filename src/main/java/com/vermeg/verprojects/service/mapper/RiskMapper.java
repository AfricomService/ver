package com.vermeg.verprojects.service.mapper;


import com.vermeg.verprojects.domain.*;
import com.vermeg.verprojects.service.dto.RiskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Risk} and its DTO {@link RiskDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RiskMapper extends EntityMapper<RiskDTO, Risk> {



    default Risk fromId(Long id) {
        if (id == null) {
            return null;
        }
        Risk risk = new Risk();
        risk.setId(id);
        return risk;
    }
}
