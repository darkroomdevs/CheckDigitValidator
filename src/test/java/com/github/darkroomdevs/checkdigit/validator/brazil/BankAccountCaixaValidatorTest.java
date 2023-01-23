package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankAccountCaixaValidatorTest {

    private DigitValidator digitValidator;

    @BeforeEach
    public void setup() {
        digitValidator = BankAccountCaixaValidator.INSTANCE;
    }

    @Test
    void assertThatCaixaBankAccountIsValid() {
        assertThat(digitValidator.valid("2004001000004486")).isTrue();
        assertThat(digitValidator.valid("0246022518435198")).isTrue();
    }

    @Test
    void assertThatCaixaBankAccountIsInvalid() {
        assertThat(digitValidator.valid("")).isFalse();
        assertThat(digitValidator.valid("           ")).isFalse();
        assertThat(digitValidator.valid(null)).isFalse();
        assertThat(digitValidator.valid("1193615Y")).isFalse();
        assertThat(digitValidator.valid("765029823001100")).isFalse();
        assertThat(digitValidator.valid("11111111")).isFalse();
    }
}
