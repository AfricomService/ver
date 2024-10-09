package com.vermeg.verprojects.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Stakeholder.
 */
@Entity
@Table(name = "stakeholder")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Stakeholder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "stakeholder_id", nullable = false)
    private Long stakeholderId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "role", nullable = false)
    private String role;

    @OneToOne(mappedBy = "stakeholder")
    @JsonIgnore
    private Task task;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStakeholderId() {
        return stakeholderId;
    }

    public Stakeholder stakeholderId(Long stakeholderId) {
        this.stakeholderId = stakeholderId;
        return this;
    }

    public void setStakeholderId(Long stakeholderId) {
        this.stakeholderId = stakeholderId;
    }

    public String getName() {
        return name;
    }

    public Stakeholder name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public Stakeholder role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Task getTask() {
        return task;
    }

    public Stakeholder task(Task task) {
        this.task = task;
        return this;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stakeholder)) {
            return false;
        }
        return id != null && id.equals(((Stakeholder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Stakeholder{" +
            "id=" + getId() +
            ", stakeholderId=" + getStakeholderId() +
            ", name='" + getName() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
