package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.util.ModuloUtil;
import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * This class checks whether a specific string represents a valid bank account number of Banco do Brasil.
 * <p/>
 * Reference: https://www.bb.com.br/docs/pub/emp/empl/dwn/Orientacoes.pdf
 */
public final class BankAccountBBValidator implements DigitValidator {

    public static final BankAccountBBValidator INSTANCE = new BankAccountBBValidator();

    private BankAccountBBValidator() {
    }

    /**
     * Checks whether the specified string represents a valid bank account number of Banco do Brasil.
     *
     * @param bankAccount The sequence to verify.
     * @return {@code true} if the sequence is a valid bank account number; {@code false} otherwise.
     */
    public boolean valid(String bankAccount) {
        if (StringUtils.isBlank(bankAccount)
                || (StringUtils.length(bankAccount) > 9)
                || !bankAccount.matches("\\d+[0-9X]")
                || bankAccount.matches("0+|1+|2+|3+|4+|5+|6+|7+|8+|9+")) {
            return false;
        }

        int bankAccountSize = bankAccount.length();
        char digit = bankAccount.charAt(bankAccountSize - 1);
        return ModuloUtil.compute(bankAccount.substring(0, bankAccountSize - 1), bankAccountSize + 1).orElse("")
                .equals(String.valueOf(digit == 'X' ? '0' : digit));
    }
}
