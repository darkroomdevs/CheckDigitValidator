package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankAccountSantanderValidatorTest {

    private DigitValidator digitValidator;

    @BeforeEach
    public void setup() {
        digitValidator = BankAccountSantanderValidator.INSTANCE;
    }

    @Test
    void assertThatSantanderBankAccountIsValid() {
        assertThat(digitValidator.valid("018900010174179")).isTrue();
        assertThat(digitValidator.valid("091900135148317")).isTrue();
        assertThat(digitValidator.valid("038200139514923")).isTrue();
        assertThat(digitValidator.valid("467000010104146")).isTrue();
    }

    @Test
    void assertThatSantanderBankAccountIsInvalid() {
        assertThat(digitValidator.valid("")).isFalse();
        assertThat(digitValidator.valid("           ")).isFalse();
        assertThat(digitValidator.valid(null)).isFalse();
        assertThat(digitValidator.valid("1193615Y")).isFalse();
        assertThat(digitValidator.valid("11111111111111")).isFalse();
    }
}
