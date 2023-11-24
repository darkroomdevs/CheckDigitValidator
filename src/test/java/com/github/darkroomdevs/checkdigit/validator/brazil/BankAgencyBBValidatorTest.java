package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankAgencyBBValidatorTest {

    private DigitValidator digitValidator;

    @BeforeEach
    public void setup() {
        digitValidator = BankAgencyBBValidator.INSTANCE;
    }

    @Test
    void assertThatBanrisulBankAccountIsValid() {
        assertThat(digitValidator.valid("15849")).isTrue();
        assertThat(digitValidator.valid("29025")).isTrue();
        assertThat(digitValidator.valid("57401")).isTrue();
        assertThat(digitValidator.valid("31933")).isTrue();
    }

    @Test
    void assertThatBarinsulBankAccountIsInvalid() {
        assertThat(digitValidator.valid("")).isFalse();
        assertThat(digitValidator.valid("           ")).isFalse();
        assertThat(digitValidator.valid(null)).isFalse();
        assertThat(digitValidator.valid("1193615Y")).isFalse();
        assertThat(digitValidator.valid("7650298230013")).isFalse();
        assertThat(digitValidator.valid("11111111")).isFalse();
    }
}
