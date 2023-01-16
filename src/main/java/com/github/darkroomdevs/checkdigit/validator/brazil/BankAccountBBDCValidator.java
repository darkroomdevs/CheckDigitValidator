package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.util.ModuloUtil;
import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * This class checks whether a specific string represents a valid bank account number of Banco Bradesco
 * <p/>
 * Reference: <a href="http://hostmarcasite.com.br/fluir/vendor/eduardokum/laravel-boleto/manuais/Regras%20Validacao%20Conta%20Corrente%20VI_EPS.pdf">Banco Bradesco Reference</a>
 */
public final class BankAccountBBDCValidator implements DigitValidator {

    public static final BankAccountBBDCValidator INSTANCE = new BankAccountBBDCValidator();

    private BankAccountBBDCValidator() {
    }

    /**
     * Checks whether the specified string represents a valid bank account number of Banco Bradesco
     *
     * @param bankAccount The sequence to verify.
     * @return {@code true} if the sequence is a valid bank account number; {@code false} otherwise.
     */
    public boolean valid(String bankAccount) {
        if (StringUtils.isBlank(bankAccount)
                || (StringUtils.length(bankAccount) > 9)
                || !bankAccount.matches("\\d+[0-9P]")
                || bankAccount.matches("0+|1+|2+|3+|4+|5+|6+|7+|8+|9+")) {
            return false;
        }

        int bankAccountSize = bankAccount.length();
        char digit = bankAccount.charAt(bankAccountSize - 1);
        return ModuloUtil.compute(bankAccount.substring(0, bankAccountSize - 1), bankAccountSize + 1).orElse("")
                .equals(String.valueOf(digit == 'P' ? '0' : digit));
    }
}
