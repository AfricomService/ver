package com.vermeg.verprojects.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

import com.vermeg.verprojects.domain.enumeration.RiskStatus;

/**
 * A Risk.
 */
@Entity
@Table(name = "risk")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Risk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "risk_id", nullable = false)
    private Long riskId;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "probability", precision = 21, scale = 2)
    private BigDecimal probability;

    @Column(name = "impact", precision = 21, scale = 2)
    private BigDecimal impact;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RiskStatus status;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRiskId() {
        return riskId;
    }

    public Risk riskId(Long riskId) {
        this.riskId = riskId;
        return this;
    }

    public void setRiskId(Long riskId) {
        this.riskId = riskId;
    }

    public String getDescription() {
        return description;
    }

    public Risk description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getProbability() {
        return probability;
    }

    public Risk probability(BigDecimal probability) {
        this.probability = probability;
        return this;
    }

    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }

    public BigDecimal getImpact() {
        return impact;
    }

    public Risk impact(BigDecimal impact) {
        this.impact = impact;
        return this;
    }

    public void setImpact(BigDecimal impact) {
        this.impact = impact;
    }

    public RiskStatus getStatus() {
        return status;
    }

    public Risk status(RiskStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(RiskStatus status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Risk)) {
            return false;
        }
        return id != null && id.equals(((Risk) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Risk{" +
            "id=" + getId() +
            ", riskId=" + getRiskId() +
            ", description='" + getDescription() + "'" +
            ", probability=" + getProbability() +
            ", impact=" + getImpact() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
