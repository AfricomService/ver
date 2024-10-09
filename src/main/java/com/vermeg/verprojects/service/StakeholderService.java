package com.vermeg.verprojects.service;

import com.vermeg.verprojects.domain.Stakeholder;
import com.vermeg.verprojects.repository.StakeholderRepository;
import com.vermeg.verprojects.service.dto.StakeholderDTO;
import com.vermeg.verprojects.service.mapper.StakeholderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Stakeholder}.
 */
@Service
@Transactional
public class StakeholderService {

    private final Logger log = LoggerFactory.getLogger(StakeholderService.class);

    private final StakeholderRepository stakeholderRepository;

    private final StakeholderMapper stakeholderMapper;

    public StakeholderService(StakeholderRepository stakeholderRepository, StakeholderMapper stakeholderMapper) {
        this.stakeholderRepository = stakeholderRepository;
        this.stakeholderMapper = stakeholderMapper;
    }

    /**
     * Save a stakeholder.
     *
     * @param stakeholderDTO the entity to save.
     * @return the persisted entity.
     */
    public StakeholderDTO save(StakeholderDTO stakeholderDTO) {
        log.debug("Request to save Stakeholder : {}", stakeholderDTO);
        Stakeholder stakeholder = stakeholderMapper.toEntity(stakeholderDTO);
        stakeholder = stakeholderRepository.save(stakeholder);
        return stakeholderMapper.toDto(stakeholder);
    }

    /**
     * Get all the stakeholders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<StakeholderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Stakeholders");
        return stakeholderRepository.findAll(pageable)
            .map(stakeholderMapper::toDto);
    }



    /**
     *  Get all the stakeholders where Task is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<StakeholderDTO> findAllWhereTaskIsNull() {
        log.debug("Request to get all stakeholders where Task is null");
        return StreamSupport
            .stream(stakeholderRepository.findAll().spliterator(), false)
            .filter(stakeholder -> stakeholder.getTask() == null)
            .map(stakeholderMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one stakeholder by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<StakeholderDTO> findOne(Long id) {
        log.debug("Request to get Stakeholder : {}", id);
        return stakeholderRepository.findById(id)
            .map(stakeholderMapper::toDto);
    }

    /**
     * Delete the stakeholder by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Stakeholder : {}", id);
        stakeholderRepository.deleteById(id);
    }
}
