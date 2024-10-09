package com.vermeg.verprojects.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.vermeg.verprojects.domain.Resource} entity.
 */
public class ResourceDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long resourceId;

    @NotNull
    private String name;

    @NotNull
    private String role;

    @NotNull
    private BigDecimal hourlyCost;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
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

    public BigDecimal getHourlyCost() {
        return hourlyCost;
    }

    public void setHourlyCost(BigDecimal hourlyCost) {
        this.hourlyCost = hourlyCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceDTO)) {
            return false;
        }

        return id != null && id.equals(((ResourceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResourceDTO{" +
            "id=" + getId() +
            ", resourceId=" + getResourceId() +
            ", name='" + getName() + "'" +
            ", role='" + getRole() + "'" +
            ", hourlyCost=" + getHourlyCost() +
            "}";
    }
}
