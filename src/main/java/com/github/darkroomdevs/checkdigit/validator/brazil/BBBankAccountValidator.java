package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.util.ModuloUtil;
import com.github.darkroomdevs.checkdigit.validator.DigitValidator;

/**
 * This class checks whether a specific string represents a valid bank Account of Banco do Brasil.
 * <p/>
 * Reference: https://www.bb.com.br/docs/pub/emp/empl/dwn/Orientacoes.pdf
 */
public final class BBBankAccountValidator implements DigitValidator {

    public static final BBBankAccountValidator INSTANCE = new BBBankAccountValidator();

    private BBBankAccountValidator() {
    }

    /**
     * Checks whether the specified string represents a valid bank Account of Banco do Brasil.
     *
     * @param bankAccount The sequence to verify.
     * @return {@code true} if the sequence is a valid CPF; {@code false} otherwise.
     */
    public boolean valid(String bankAccount) {
        if (bankAccount == null) {
            return false;
        }
        if (bankAccount.endsWith("X")) {
            bankAccount = bankAccount.substring(0, bankAccount.length() - 1) + "0";
        }
        if (bankAccount.length() > 9
                || !bankAccount.matches("\\d+")) {
            return false;
        }
        int bankAccountSize = bankAccount.length();
        return ModuloUtil.compute(bankAccount.substring(0, bankAccountSize - 1), bankAccountSize + 1).orElse("").equals(String.valueOf(bankAccount.charAt(bankAccountSize - 1)));
    }
}
