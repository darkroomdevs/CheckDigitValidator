package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankAccountBanrisulValidatorTest {

    private DigitValidator digitValidator;

    @BeforeEach
    public void setup() {
        digitValidator = BankAccountBanrisulValidator.INSTANCE;
    }

    @Test
    void assertThatCitibankBankAccountIsValid() {
        assertThat(digitValidator.valid("3585076718")).isTrue();
        assertThat(digitValidator.valid("3518223725")).isTrue();
        //assertThat(digitValidator.valid("40168518511")).isTrue();
    }

    @Test
    void assertThatCitibankBankAccountIsInvalid() {
        assertThat(digitValidator.valid("")).isFalse();
        assertThat(digitValidator.valid("           ")).isFalse();
        assertThat(digitValidator.valid(null)).isFalse();
        assertThat(digitValidator.valid("1193615Y")).isFalse();
        assertThat(digitValidator.valid("7650298230013")).isFalse();
        assertThat(digitValidator.valid("11111111")).isFalse();
    }
}
