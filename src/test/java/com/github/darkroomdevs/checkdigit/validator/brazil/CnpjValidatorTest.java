package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CnpjValidatorTest {

    private DigitValidator digitValidator;

    @BeforeEach
    public void setup() {
        digitValidator = CnpjValidator.INSTANCE;
    }

    @Test
    public void assertThatCNPJIsValid() {
        assertThat(digitValidator.valid("13291622000168")).isTrue();
        assertThat(digitValidator.valid("67328780000174")).isTrue();
    }

    @Test
    public void assertThatCNPJIsInvalid() {
        assertThat(digitValidator.valid("")).isFalse();
        assertThat(digitValidator.valid("              ")).isFalse();
        assertThat(digitValidator.valid(null)).isFalse();
        assertThat(digitValidator.valid("13291622000188")).isFalse();
        assertThat(digitValidator.valid("67328780000104")).isFalse();
        assertThat(digitValidator.valid("11111111111111")).isFalse();
    }
}
