package com.vermeg.verprojects.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.vermeg.verprojects.domain.Stakeholder} entity.
 */
public class StakeholderDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long stakeholderId;

    @NotNull
    private String name;

    @NotNull
    private String role;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStakeholderId() {
        return stakeholderId;
    }

    public void setStakeholderId(Long stakeholderId) {
        this.stakeholderId = stakeholderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StakeholderDTO)) {
            return false;
        }

        return id != null && id.equals(((StakeholderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StakeholderDTO{" +
            "id=" + getId() +
            ", stakeholderId=" + getStakeholderId() +
            ", name='" + getName() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
