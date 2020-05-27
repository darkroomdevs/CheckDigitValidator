package com.github.darkroomdevs.checkdigit.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ModuloUtilTest {

    @Test
    public void assertThatCNPJComputeSuccessful() {
        assertThat(ModuloUtil.compute("132916220001", 9, 2)).contains("6");
        assertThat(ModuloUtil.compute("1329162200016", 9, 2)).contains("8");
    }

    @Test
    public void assertThatCPFComputeSuccessful() {
        assertThat(ModuloUtil.compute("533342678")).contains("3");
        assertThat(ModuloUtil.compute("5333426783", 11)).contains("0");
    }

    @Test
    public void assertThatInvalidComputeEmpty() {
        assertThat(ModuloUtil.compute(" ", 9)).isNotPresent();
        assertThat(ModuloUtil.compute(null, 9)).isNotPresent();
        assertThat(ModuloUtil.compute("1234", 0)).isNotPresent();
        assertThat(ModuloUtil.compute("1234", 2)).isNotPresent();
    }
}
