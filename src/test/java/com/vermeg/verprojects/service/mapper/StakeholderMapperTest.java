package com.vermeg.verprojects.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StakeholderMapperTest {

    private StakeholderMapper stakeholderMapper;

    @BeforeEach
    public void setUp() {
        stakeholderMapper = new StakeholderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(stakeholderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(stakeholderMapper.fromId(null)).isNull();
    }
}
