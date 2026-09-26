package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * This class checks whether a specific string represents a valid bank account number of Banco Banrisul
 * <p/>
 * Reference: <a href="https://www.banrisul.com.br/bob/data/Manual_Debito_Automatico_versao_05.pdf">Banco Banrisul</a>
 */
public final class BankAccountBanrisulValidator implements DigitValidator {

    public static final BankAccountBanrisulValidator INSTANCE = new BankAccountBanrisulValidator();

    private BankAccountBanrisulValidator() {
    }

    /**
     * Checks whether the specified string represents a valid branch and bank account number of Banco Banrisul
     *
     * @param bankAccount The sequence to verify.
     * @return {@code true} if the sequence is a valid bank account number; {@code false} otherwise.
     */
    public boolean valid(String bankAccount) {
        if (StringUtils.isBlank(bankAccount)
                || (StringUtils.length(bankAccount) > 11)
                || !bankAccount.matches("\\d{2,}")
                || bankAccount.matches("0+|1+|2+|3+|4+|5+|6+|7+|8+|9+")) {
            return false;
        }

        String pivot = "324765432";
        int bankAccountSize = bankAccount.length();
        char digit = bankAccount.charAt(bankAccountSize - 1);
        String body = bankAccount.substring(0, bankAccountSize - 1);
        int sum = 0;
        for (int i = 0; i < body.length(); i++) {
            sum += (body.charAt(i) - '0') * (pivot.charAt(i) - '0');
        }
        int resto = sum % 11;
        char expected = resto == 0 ? '0' : resto == 1 ? '6' : (char) ('0' + (11 - resto));
        return digit == expected;
    }
}
