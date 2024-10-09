package com.vermeg.verprojects.web.rest;

import com.vermeg.verprojects.service.StakeholderService;
import com.vermeg.verprojects.web.rest.errors.BadRequestAlertException;
import com.vermeg.verprojects.service.dto.StakeholderDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.vermeg.verprojects.domain.Stakeholder}.
 */
@RestController
@RequestMapping("/api")
public class StakeholderResource {

    private final Logger log = LoggerFactory.getLogger(StakeholderResource.class);

    private static final String ENTITY_NAME = "stakeholder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StakeholderService stakeholderService;

    public StakeholderResource(StakeholderService stakeholderService) {
        this.stakeholderService = stakeholderService;
    }

    /**
     * {@code POST  /stakeholders} : Create a new stakeholder.
     *
     * @param stakeholderDTO the stakeholderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stakeholderDTO, or with status {@code 400 (Bad Request)} if the stakeholder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stakeholders")
    public ResponseEntity<StakeholderDTO> createStakeholder(@Valid @RequestBody StakeholderDTO stakeholderDTO) throws URISyntaxException {
        log.debug("REST request to save Stakeholder : {}", stakeholderDTO);
        if (stakeholderDTO.getId() != null) {
            throw new BadRequestAlertException("A new stakeholder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StakeholderDTO result = stakeholderService.save(stakeholderDTO);
        return ResponseEntity.created(new URI("/api/stakeholders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stakeholders} : Updates an existing stakeholder.
     *
     * @param stakeholderDTO the stakeholderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stakeholderDTO,
     * or with status {@code 400 (Bad Request)} if the stakeholderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stakeholderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stakeholders")
    public ResponseEntity<StakeholderDTO> updateStakeholder(@Valid @RequestBody StakeholderDTO stakeholderDTO) throws URISyntaxException {
        log.debug("REST request to update Stakeholder : {}", stakeholderDTO);
        if (stakeholderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StakeholderDTO result = stakeholderService.save(stakeholderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, stakeholderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /stakeholders} : get all the stakeholders.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stakeholders in body.
     */
    @GetMapping("/stakeholders")
    public ResponseEntity<List<StakeholderDTO>> getAllStakeholders(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("task-is-null".equals(filter)) {
            log.debug("REST request to get all Stakeholders where task is null");
            return new ResponseEntity<>(stakeholderService.findAllWhereTaskIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Stakeholders");
        Page<StakeholderDTO> page = stakeholderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stakeholders/:id} : get the "id" stakeholder.
     *
     * @param id the id of the stakeholderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stakeholderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stakeholders/{id}")
    public ResponseEntity<StakeholderDTO> getStakeholder(@PathVariable Long id) {
        log.debug("REST request to get Stakeholder : {}", id);
        Optional<StakeholderDTO> stakeholderDTO = stakeholderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stakeholderDTO);
    }

    /**
     * {@code DELETE  /stakeholders/:id} : delete the "id" stakeholder.
     *
     * @param id the id of the stakeholderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stakeholders/{id}")
    public ResponseEntity<Void> deleteStakeholder(@PathVariable Long id) {
        log.debug("REST request to delete Stakeholder : {}", id);
        stakeholderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
