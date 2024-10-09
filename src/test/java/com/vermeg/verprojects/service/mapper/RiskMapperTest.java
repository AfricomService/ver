package com.vermeg.verprojects.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RiskMapperTest {

    private RiskMapper riskMapper;

    @BeforeEach
    public void setUp() {
        riskMapper = new RiskMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(riskMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(riskMapper.fromId(null)).isNull();
    }
}
