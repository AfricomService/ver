package com.vermeg.verprojects.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.vermeg.verprojects.web.rest.TestUtil;

public class RiskDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskDTO.class);
        RiskDTO riskDTO1 = new RiskDTO();
        riskDTO1.setId(1L);
        RiskDTO riskDTO2 = new RiskDTO();
        assertThat(riskDTO1).isNotEqualTo(riskDTO2);
        riskDTO2.setId(riskDTO1.getId());
        assertThat(riskDTO1).isEqualTo(riskDTO2);
        riskDTO2.setId(2L);
        assertThat(riskDTO1).isNotEqualTo(riskDTO2);
        riskDTO1.setId(null);
        assertThat(riskDTO1).isNotEqualTo(riskDTO2);
    }
}
