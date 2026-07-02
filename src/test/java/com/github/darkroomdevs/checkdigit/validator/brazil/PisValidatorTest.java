package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PisValidatorTest {

    private DigitValidator digitValidator;

    @BeforeEach
    public void setup() {
        digitValidator = PisValidator.INSTANCE;
    }

    @Test
    public void assertThatCPFIsValid() {
        assertThat(digitValidator.valid("16342610674")).isTrue();
        assertThat(digitValidator.valid("92035019290")).isTrue();
    }

    @Test
    public void assertThatCPFIsInvalid() {
        assertThat(digitValidator.valid("")).isFalse();
        assertThat(digitValidator.valid("           ")).isFalse();
        assertThat(digitValidator.valid(null)).isFalse();
        assertThat(digitValidator.valid("16342610673")).isFalse();
        assertThat(digitValidator.valid("92035019291")).isFalse();
        assertThat(digitValidator.valid("33333333333")).isFalse();
    }
}
