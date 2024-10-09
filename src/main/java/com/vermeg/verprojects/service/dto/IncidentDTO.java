package com.vermeg.verprojects.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.vermeg.verprojects.domain.enumeration.IncidentSeverity;

/**
 * A DTO for the {@link com.vermeg.verprojects.domain.Incident} entity.
 */
public class IncidentDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long incidentId;

    @NotNull
    private String description;

    @NotNull
    private LocalDate discoveryDate;

    private IncidentSeverity severity;

    @NotNull
    private String status;

    private String responsible;

    private LocalDate mitigationDate;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(Long incidentId) {
        this.incidentId = incidentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDiscoveryDate() {
        return discoveryDate;
    }

    public void setDiscoveryDate(LocalDate discoveryDate) {
        this.discoveryDate = discoveryDate;
    }

    public IncidentSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(IncidentSeverity severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public LocalDate getMitigationDate() {
        return mitigationDate;
    }

    public void setMitigationDate(LocalDate mitigationDate) {
        this.mitigationDate = mitigationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IncidentDTO)) {
            return false;
        }

        return id != null && id.equals(((IncidentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IncidentDTO{" +
            "id=" + getId() +
            ", incidentId=" + getIncidentId() +
            ", description='" + getDescription() + "'" +
            ", discoveryDate='" + getDiscoveryDate() + "'" +
            ", severity='" + getSeverity() + "'" +
            ", status='" + getStatus() + "'" +
            ", responsible='" + getResponsible() + "'" +
            ", mitigationDate='" + getMitigationDate() + "'" +
            "}";
    }
}
