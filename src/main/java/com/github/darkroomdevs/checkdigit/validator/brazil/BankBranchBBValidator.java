package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.util.ModuloUtil;
import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * This class checks whether a specific string represents a valid bank account number of Banco Santander.
 * <p/>
 * Reference: <a href="http://hostmarcasite.com.br/fluir/vendor/eduardokum/laravel-boleto/manuais/Regras%20Validacao%20Conta%20Corrente%20VI_EPS.pdf">Banco do Brasil.</a>
 */
public final class BankBranchBBValidator implements DigitValidator {

    public static final BankBranchBBValidator INSTANCE = new BankBranchBBValidator();

    private BankBranchBBValidator() {
    }

    /**
     * Checks whether the specified string represents a valid branch and bank account number of Banco do Brasil.
     *
     * @param bankBranch The sequence to verify.
     * @return {@code true} if the sequence is a valid bank account number; {@code false} otherwise.
     */
    public boolean valid(String bankBranch) {
        if (StringUtils.isBlank(bankBranch)
                || (StringUtils.length(bankBranch) > 5)
                || !bankBranch.matches("\\d+[0-9X]")
                || bankBranch.matches("0+|1+|2+|3+|4+|5+|6+|7+|8+|9+")) {
            return false;
        }

        int bankBranchSize = bankBranch.length();
        char digit = bankBranch.charAt(bankBranchSize - 1);
        return ModuloUtil.compute(bankBranch.substring(0, bankBranchSize - 1)).orElse("")
                .equals(String.valueOf(digit));
    }
}
