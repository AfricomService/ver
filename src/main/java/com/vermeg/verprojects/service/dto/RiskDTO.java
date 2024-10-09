package com.vermeg.verprojects.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import com.vermeg.verprojects.domain.enumeration.RiskStatus;

/**
 * A DTO for the {@link com.vermeg.verprojects.domain.Risk} entity.
 */
public class RiskDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long riskId;

    @NotNull
    private String description;

    private BigDecimal probability;

    private BigDecimal impact;

    private RiskStatus status;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRiskId() {
        return riskId;
    }

    public void setRiskId(Long riskId) {
        this.riskId = riskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getProbability() {
        return probability;
    }

    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }

    public BigDecimal getImpact() {
        return impact;
    }

    public void setImpact(BigDecimal impact) {
        this.impact = impact;
    }

    public RiskStatus getStatus() {
        return status;
    }

    public void setStatus(RiskStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RiskDTO)) {
            return false;
        }

        return id != null && id.equals(((RiskDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RiskDTO{" +
            "id=" + getId() +
            ", riskId=" + getRiskId() +
            ", description='" + getDescription() + "'" +
            ", probability=" + getProbability() +
            ", impact=" + getImpact() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
