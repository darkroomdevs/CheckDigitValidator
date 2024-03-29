package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BankAccountBradescoValidatorTest {

    private DigitValidator digitValidator;

    @BeforeEach
    public void setup() {
        digitValidator = BankAccountBradescoValidator.INSTANCE;
    }

    @Test
    void assertThatBradescoBankAccountIsValid() {
        assertThat(digitValidator.valid("301357P")).isTrue();
        assertThat(digitValidator.valid("0558230P")).isTrue();
        assertThat(digitValidator.valid("07112556")).isTrue();
        assertThat(digitValidator.valid("02380692")).isTrue();
        assertThat(digitValidator.valid("1543578P")).isTrue();
        assertThat(digitValidator.valid("10047722")).isTrue();
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
