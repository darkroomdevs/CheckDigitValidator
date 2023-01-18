package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankAccountCitibankValidatorTest {

    private DigitValidator digitValidator;

    @BeforeEach
    public void setup() {
        digitValidator = BankAccountCitibankValidator.INSTANCE;
    }

    @Test
    void assertThatBradescoBankAccountIsValid() {
        assertThat(digitValidator.valid("97757898994")).isTrue();
        assertThat(digitValidator.valid("49709956515")).isTrue();
        assertThat(digitValidator.valid("40168518511")).isTrue();
    }

    @Test
    void assertThatBradescoBankAccountIsInvalid() {
        assertThat(digitValidator.valid("")).isFalse();
        assertThat(digitValidator.valid("           ")).isFalse();
        assertThat(digitValidator.valid(null)).isFalse();
        assertThat(digitValidator.valid("1193615Y")).isFalse();
        assertThat(digitValidator.valid("76502982300")).isFalse();
        assertThat(digitValidator.valid("11111111")).isFalse();
    }
}
