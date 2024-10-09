package com.vermeg.verprojects.web.rest;

import com.vermeg.verprojects.VerprojectsApp;
import com.vermeg.verprojects.domain.Stakeholder;
import com.vermeg.verprojects.repository.StakeholderRepository;
import com.vermeg.verprojects.service.StakeholderService;
import com.vermeg.verprojects.service.dto.StakeholderDTO;
import com.vermeg.verprojects.service.mapper.StakeholderMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link StakeholderResource} REST controller.
 */
@SpringBootTest(classes = VerprojectsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StakeholderResourceIT {

    private static final Long DEFAULT_STAKEHOLDER_ID = 1L;
    private static final Long UPDATED_STAKEHOLDER_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired
    private StakeholderMapper stakeholderMapper;

    @Autowired
    private StakeholderService stakeholderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStakeholderMockMvc;

    private Stakeholder stakeholder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stakeholder createEntity(EntityManager em) {
        Stakeholder stakeholder = new Stakeholder()
            .stakeholderId(DEFAULT_STAKEHOLDER_ID)
            .name(DEFAULT_NAME)
            .role(DEFAULT_ROLE);
        return stakeholder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stakeholder createUpdatedEntity(EntityManager em) {
        Stakeholder stakeholder = new Stakeholder()
            .stakeholderId(UPDATED_STAKEHOLDER_ID)
            .name(UPDATED_NAME)
            .role(UPDATED_ROLE);
        return stakeholder;
    }

    @BeforeEach
    public void initTest() {
        stakeholder = createEntity(em);
    }

    @Test
    @Transactional
    public void createStakeholder() throws Exception {
        int databaseSizeBeforeCreate = stakeholderRepository.findAll().size();
        // Create the Stakeholder
        StakeholderDTO stakeholderDTO = stakeholderMapper.toDto(stakeholder);
        restStakeholderMockMvc.perform(post("/api/stakeholders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stakeholderDTO)))
            .andExpect(status().isCreated());

        // Validate the Stakeholder in the database
        List<Stakeholder> stakeholderList = stakeholderRepository.findAll();
        assertThat(stakeholderList).hasSize(databaseSizeBeforeCreate + 1);
        Stakeholder testStakeholder = stakeholderList.get(stakeholderList.size() - 1);
        assertThat(testStakeholder.getStakeholderId()).isEqualTo(DEFAULT_STAKEHOLDER_ID);
        assertThat(testStakeholder.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStakeholder.getRole()).isEqualTo(DEFAULT_ROLE);
    }

    @Test
    @Transactional
    public void createStakeholderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stakeholderRepository.findAll().size();

        // Create the Stakeholder with an existing ID
        stakeholder.setId(1L);
        StakeholderDTO stakeholderDTO = stakeholderMapper.toDto(stakeholder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStakeholderMockMvc.perform(post("/api/stakeholders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stakeholderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Stakeholder in the database
        List<Stakeholder> stakeholderList = stakeholderRepository.findAll();
        assertThat(stakeholderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStakeholderIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = stakeholderRepository.findAll().size();
        // set the field null
        stakeholder.setStakeholderId(null);

        // Create the Stakeholder, which fails.
        StakeholderDTO stakeholderDTO = stakeholderMapper.toDto(stakeholder);


        restStakeholderMockMvc.perform(post("/api/stakeholders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stakeholderDTO)))
            .andExpect(status().isBadRequest());

        List<Stakeholder> stakeholderList = stakeholderRepository.findAll();
        assertThat(stakeholderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = stakeholderRepository.findAll().size();
        // set the field null
        stakeholder.setName(null);

        // Create the Stakeholder, which fails.
        StakeholderDTO stakeholderDTO = stakeholderMapper.toDto(stakeholder);


        restStakeholderMockMvc.perform(post("/api/stakeholders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stakeholderDTO)))
            .andExpect(status().isBadRequest());

        List<Stakeholder> stakeholderList = stakeholderRepository.findAll();
        assertThat(stakeholderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRoleIsRequired() throws Exception {
        int databaseSizeBeforeTest = stakeholderRepository.findAll().size();
        // set the field null
        stakeholder.setRole(null);

        // Create the Stakeholder, which fails.
        StakeholderDTO stakeholderDTO = stakeholderMapper.toDto(stakeholder);


        restStakeholderMockMvc.perform(post("/api/stakeholders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stakeholderDTO)))
            .andExpect(status().isBadRequest());

        List<Stakeholder> stakeholderList = stakeholderRepository.findAll();
        assertThat(stakeholderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStakeholders() throws Exception {
        // Initialize the database
        stakeholderRepository.saveAndFlush(stakeholder);

        // Get all the stakeholderList
        restStakeholderMockMvc.perform(get("/api/stakeholders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stakeholder.getId().intValue())))
            .andExpect(jsonPath("$.[*].stakeholderId").value(hasItem(DEFAULT_STAKEHOLDER_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)));
    }
    
    @Test
    @Transactional
    public void getStakeholder() throws Exception {
        // Initialize the database
        stakeholderRepository.saveAndFlush(stakeholder);

        // Get the stakeholder
        restStakeholderMockMvc.perform(get("/api/stakeholders/{id}", stakeholder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stakeholder.getId().intValue()))
            .andExpect(jsonPath("$.stakeholderId").value(DEFAULT_STAKEHOLDER_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE));
    }
    @Test
    @Transactional
    public void getNonExistingStakeholder() throws Exception {
        // Get the stakeholder
        restStakeholderMockMvc.perform(get("/api/stakeholders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStakeholder() throws Exception {
        // Initialize the database
        stakeholderRepository.saveAndFlush(stakeholder);

        int databaseSizeBeforeUpdate = stakeholderRepository.findAll().size();

        // Update the stakeholder
        Stakeholder updatedStakeholder = stakeholderRepository.findById(stakeholder.getId()).get();
        // Disconnect from session so that the updates on updatedStakeholder are not directly saved in db
        em.detach(updatedStakeholder);
        updatedStakeholder
            .stakeholderId(UPDATED_STAKEHOLDER_ID)
            .name(UPDATED_NAME)
            .role(UPDATED_ROLE);
        StakeholderDTO stakeholderDTO = stakeholderMapper.toDto(updatedStakeholder);

        restStakeholderMockMvc.perform(put("/api/stakeholders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stakeholderDTO)))
            .andExpect(status().isOk());

        // Validate the Stakeholder in the database
        List<Stakeholder> stakeholderList = stakeholderRepository.findAll();
        assertThat(stakeholderList).hasSize(databaseSizeBeforeUpdate);
        Stakeholder testStakeholder = stakeholderList.get(stakeholderList.size() - 1);
        assertThat(testStakeholder.getStakeholderId()).isEqualTo(UPDATED_STAKEHOLDER_ID);
        assertThat(testStakeholder.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStakeholder.getRole()).isEqualTo(UPDATED_ROLE);
    }

    @Test
    @Transactional
    public void updateNonExistingStakeholder() throws Exception {
        int databaseSizeBeforeUpdate = stakeholderRepository.findAll().size();

        // Create the Stakeholder
        StakeholderDTO stakeholderDTO = stakeholderMapper.toDto(stakeholder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStakeholderMockMvc.perform(put("/api/stakeholders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stakeholderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Stakeholder in the database
        List<Stakeholder> stakeholderList = stakeholderRepository.findAll();
        assertThat(stakeholderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStakeholder() throws Exception {
        // Initialize the database
        stakeholderRepository.saveAndFlush(stakeholder);

        int databaseSizeBeforeDelete = stakeholderRepository.findAll().size();

        // Delete the stakeholder
        restStakeholderMockMvc.perform(delete("/api/stakeholders/{id}", stakeholder.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Stakeholder> stakeholderList = stakeholderRepository.findAll();
        assertThat(stakeholderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
