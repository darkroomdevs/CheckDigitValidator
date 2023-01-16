package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.util.ModuloUtil;
import com.github.darkroomdevs.checkdigit.validator.DigitValidator;

/**
 * This class checks whether a specific string represents a valid bank account of Banco do Brasil.
 * <p/>
 * Reference: https://www.bb.com.br/docs/pub/emp/empl/dwn/Orientacoes.pdf
 */
public final class BankAccountBBValidator implements DigitValidator {

    public static final BankAccountBBValidator INSTANCE = new BankAccountBBValidator();

    private BankAccountBBValidator() {
    }

    /**
     * Checks whether the specified string represents a valid bank account of Banco do Brasil.
     *
     * @param bankAccount The sequence to verify.
     * @return {@code true} if the sequence is a valid bankAccount; {@code false} otherwise.
     */
    public boolean valid(String bankAccount) {
        if (bankAccount == null) {
            return false;
        }

        bankAccount = bankAccount.replace("X", "0");

        if (!bankAccount.matches("\\d{1,9}")) {
            return false;
        }

        int bankAccountSize = bankAccount.length();
        String lastDigit = String.valueOf(bankAccount.charAt(bankAccountSize - 1));
        String digits = bankAccount.substring(0, bankAccountSize - 1);

        return ModuloUtil.compute(digits, bankAccountSize + 1).orElse("").equals(lastDigit);
    }
}
