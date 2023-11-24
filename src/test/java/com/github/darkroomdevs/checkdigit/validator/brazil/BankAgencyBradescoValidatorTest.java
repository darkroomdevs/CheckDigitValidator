package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankAgencyBradescoValidatorTest {

    private DigitValidator digitValidator;

    @BeforeEach
    public void setup() {
        digitValidator = BankAgencyBradescoValidator.INSTANCE;
    }

    @Test
    void assertThatBradescoBankAccountIsValid() {
        assertThat(digitValidator.valid("21130")).isTrue();
        assertThat(digitValidator.valid("79804")).isTrue();
        assertThat(digitValidator.valid("08796")).isTrue();
        assertThat(digitValidator.valid("04057")).isTrue();
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
