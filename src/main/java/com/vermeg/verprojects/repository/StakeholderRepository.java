package com.vermeg.verprojects.repository;

import com.vermeg.verprojects.domain.Stakeholder;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Stakeholder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StakeholderRepository extends JpaRepository<Stakeholder, Long> {
}
