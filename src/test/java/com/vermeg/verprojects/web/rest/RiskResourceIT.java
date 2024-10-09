package com.vermeg.verprojects.web.rest;

import com.vermeg.verprojects.VerprojectsApp;
import com.vermeg.verprojects.domain.Risk;
import com.vermeg.verprojects.repository.RiskRepository;
import com.vermeg.verprojects.service.RiskService;
import com.vermeg.verprojects.service.dto.RiskDTO;
import com.vermeg.verprojects.service.mapper.RiskMapper;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vermeg.verprojects.domain.enumeration.RiskStatus;
/**
 * Integration tests for the {@link RiskResource} REST controller.
 */
@SpringBootTest(classes = VerprojectsApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RiskResourceIT {

    private static final Long DEFAULT_RISK_ID = 1L;
    private static final Long UPDATED_RISK_ID = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PROBABILITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_PROBABILITY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_IMPACT = new BigDecimal(1);
    private static final BigDecimal UPDATED_IMPACT = new BigDecimal(2);

    private static final RiskStatus DEFAULT_STATUS = RiskStatus.OPEN;
    private static final RiskStatus UPDATED_STATUS = RiskStatus.MITIGATED;

    @Autowired
    private RiskRepository riskRepository;

    @Autowired
    private RiskMapper riskMapper;

    @Autowired
    private RiskService riskService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskMockMvc;

    private Risk risk;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Risk createEntity(EntityManager em) {
        Risk risk = new Risk()
            .riskId(DEFAULT_RISK_ID)
            .description(DEFAULT_DESCRIPTION)
            .probability(DEFAULT_PROBABILITY)
            .impact(DEFAULT_IMPACT)
            .status(DEFAULT_STATUS);
        return risk;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Risk createUpdatedEntity(EntityManager em) {
        Risk risk = new Risk()
            .riskId(UPDATED_RISK_ID)
            .description(UPDATED_DESCRIPTION)
            .probability(UPDATED_PROBABILITY)
            .impact(UPDATED_IMPACT)
            .status(UPDATED_STATUS);
        return risk;
    }

    @BeforeEach
    public void initTest() {
        risk = createEntity(em);
    }

    @Test
    @Transactional
    public void createRisk() throws Exception {
        int databaseSizeBeforeCreate = riskRepository.findAll().size();
        // Create the Risk
        RiskDTO riskDTO = riskMapper.toDto(risk);
        restRiskMockMvc.perform(post("/api/risks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(riskDTO)))
            .andExpect(status().isCreated());

        // Validate the Risk in the database
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeCreate + 1);
        Risk testRisk = riskList.get(riskList.size() - 1);
        assertThat(testRisk.getRiskId()).isEqualTo(DEFAULT_RISK_ID);
        assertThat(testRisk.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRisk.getProbability()).isEqualTo(DEFAULT_PROBABILITY);
        assertThat(testRisk.getImpact()).isEqualTo(DEFAULT_IMPACT);
        assertThat(testRisk.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createRiskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = riskRepository.findAll().size();

        // Create the Risk with an existing ID
        risk.setId(1L);
        RiskDTO riskDTO = riskMapper.toDto(risk);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskMockMvc.perform(post("/api/risks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(riskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Risk in the database
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRiskIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = riskRepository.findAll().size();
        // set the field null
        risk.setRiskId(null);

        // Create the Risk, which fails.
        RiskDTO riskDTO = riskMapper.toDto(risk);


        restRiskMockMvc.perform(post("/api/risks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(riskDTO)))
            .andExpect(status().isBadRequest());

        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = riskRepository.findAll().size();
        // set the field null
        risk.setDescription(null);

        // Create the Risk, which fails.
        RiskDTO riskDTO = riskMapper.toDto(risk);


        restRiskMockMvc.perform(post("/api/risks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(riskDTO)))
            .andExpect(status().isBadRequest());

        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRisks() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get all the riskList
        restRiskMockMvc.perform(get("/api/risks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(risk.getId().intValue())))
            .andExpect(jsonPath("$.[*].riskId").value(hasItem(DEFAULT_RISK_ID.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].probability").value(hasItem(DEFAULT_PROBABILITY.intValue())))
            .andExpect(jsonPath("$.[*].impact").value(hasItem(DEFAULT_IMPACT.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getRisk() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        // Get the risk
        restRiskMockMvc.perform(get("/api/risks/{id}", risk.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(risk.getId().intValue()))
            .andExpect(jsonPath("$.riskId").value(DEFAULT_RISK_ID.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.probability").value(DEFAULT_PROBABILITY.intValue()))
            .andExpect(jsonPath("$.impact").value(DEFAULT_IMPACT.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingRisk() throws Exception {
        // Get the risk
        restRiskMockMvc.perform(get("/api/risks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRisk() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        int databaseSizeBeforeUpdate = riskRepository.findAll().size();

        // Update the risk
        Risk updatedRisk = riskRepository.findById(risk.getId()).get();
        // Disconnect from session so that the updates on updatedRisk are not directly saved in db
        em.detach(updatedRisk);
        updatedRisk
            .riskId(UPDATED_RISK_ID)
            .description(UPDATED_DESCRIPTION)
            .probability(UPDATED_PROBABILITY)
            .impact(UPDATED_IMPACT)
            .status(UPDATED_STATUS);
        RiskDTO riskDTO = riskMapper.toDto(updatedRisk);

        restRiskMockMvc.perform(put("/api/risks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(riskDTO)))
            .andExpect(status().isOk());

        // Validate the Risk in the database
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeUpdate);
        Risk testRisk = riskList.get(riskList.size() - 1);
        assertThat(testRisk.getRiskId()).isEqualTo(UPDATED_RISK_ID);
        assertThat(testRisk.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRisk.getProbability()).isEqualTo(UPDATED_PROBABILITY);
        assertThat(testRisk.getImpact()).isEqualTo(UPDATED_IMPACT);
        assertThat(testRisk.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingRisk() throws Exception {
        int databaseSizeBeforeUpdate = riskRepository.findAll().size();

        // Create the Risk
        RiskDTO riskDTO = riskMapper.toDto(risk);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskMockMvc.perform(put("/api/risks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(riskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Risk in the database
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRisk() throws Exception {
        // Initialize the database
        riskRepository.saveAndFlush(risk);

        int databaseSizeBeforeDelete = riskRepository.findAll().size();

        // Delete the risk
        restRiskMockMvc.perform(delete("/api/risks/{id}", risk.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Risk> riskList = riskRepository.findAll();
        assertThat(riskList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
