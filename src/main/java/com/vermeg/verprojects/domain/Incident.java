package com.vermeg.verprojects.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import com.vermeg.verprojects.domain.enumeration.IncidentSeverity;

/**
 * A Incident.
 */
@Entity
@Table(name = "incident")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Incident implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "incident_id", nullable = false)
    private Long incidentId;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "discovery_date", nullable = false)
    private LocalDate discoveryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "severity")
    private IncidentSeverity severity;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "mitigation_date")
    private LocalDate mitigationDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIncidentId() {
        return incidentId;
    }

    public Incident incidentId(Long incidentId) {
        this.incidentId = incidentId;
        return this;
    }

    public void setIncidentId(Long incidentId) {
        this.incidentId = incidentId;
    }

    public String getDescription() {
        return description;
    }

    public Incident description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDiscoveryDate() {
        return discoveryDate;
    }

    public Incident discoveryDate(LocalDate discoveryDate) {
        this.discoveryDate = discoveryDate;
        return this;
    }

    public void setDiscoveryDate(LocalDate discoveryDate) {
        this.discoveryDate = discoveryDate;
    }

    public IncidentSeverity getSeverity() {
        return severity;
    }

    public Incident severity(IncidentSeverity severity) {
        this.severity = severity;
        return this;
    }

    public void setSeverity(IncidentSeverity severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public Incident status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponsible() {
        return responsible;
    }

    public Incident responsible(String responsible) {
        this.responsible = responsible;
        return this;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public LocalDate getMitigationDate() {
        return mitigationDate;
    }

    public Incident mitigationDate(LocalDate mitigationDate) {
        this.mitigationDate = mitigationDate;
        return this;
    }

    public void setMitigationDate(LocalDate mitigationDate) {
        this.mitigationDate = mitigationDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Incident)) {
            return false;
        }
        return id != null && id.equals(((Incident) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Incident{" +
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
