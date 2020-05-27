package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CpfValidatorTest {

    private DigitValidator digitValidator;

    @BeforeEach
    public void setup() {
        digitValidator = CpfValidator.INSTANCE;
    }

    @Test
    public void assertThatCPFIsValid() {
        assertThat(digitValidator.valid("53334267830")).isTrue();
        assertThat(digitValidator.valid("76502982301")).isTrue();
    }

    @Test
    public void assertThatCPFIsInvalid() {
        assertThat(digitValidator.valid("")).isFalse();
        assertThat(digitValidator.valid("           ")).isFalse();
        assertThat(digitValidator.valid(null)).isFalse();
        assertThat(digitValidator.valid("53334267831")).isFalse();
        assertThat(digitValidator.valid("76502982300")).isFalse();
        assertThat(digitValidator.valid("11111111111")).isFalse();
    }
}
