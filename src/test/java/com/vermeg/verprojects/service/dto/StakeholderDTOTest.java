package com.vermeg.verprojects.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vermeg.verprojects.web.rest.TestUtil;

public class StakeholderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StakeholderDTO.class);
        StakeholderDTO stakeholderDTO1 = new StakeholderDTO();
        stakeholderDTO1.setId(1L);
        StakeholderDTO stakeholderDTO2 = new StakeholderDTO();
        assertThat(stakeholderDTO1).isNotEqualTo(stakeholderDTO2);
        stakeholderDTO2.setId(stakeholderDTO1.getId());
        assertThat(stakeholderDTO1).isEqualTo(stakeholderDTO2);
        stakeholderDTO2.setId(2L);
        assertThat(stakeholderDTO1).isNotEqualTo(stakeholderDTO2);
        stakeholderDTO1.setId(null);
        assertThat(stakeholderDTO1).isNotEqualTo(stakeholderDTO2);
    }
}
