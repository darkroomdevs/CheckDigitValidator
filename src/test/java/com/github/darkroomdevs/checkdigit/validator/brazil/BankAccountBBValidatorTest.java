package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankAccountBBValidatorTest {

    private DigitValidator digitValidator;

    @BeforeEach
    public void setup() {
        digitValidator = BankAccountBBValidator.INSTANCE;
    }

    @Test
    void assertThatBBBankAccountIsValid() {
        assertThat(digitValidator.valid("11666153")).isTrue();
        assertThat(digitValidator.valid("1248654X")).isTrue();
        assertThat(digitValidator.valid("399477")).isTrue();
    }

    @Test
    void assertThatBBBankAccountIsInvalid() {
        assertThat(digitValidator.valid("")).isFalse();
        assertThat(digitValidator.valid("           ")).isFalse();
        assertThat(digitValidator.valid(null)).isFalse();
        assertThat(digitValidator.valid("1193615Y")).isFalse();
        assertThat(digitValidator.valid("76502982300")).isFalse();
        assertThat(digitValidator.valid("11111111111")).isFalse();
    }
}
