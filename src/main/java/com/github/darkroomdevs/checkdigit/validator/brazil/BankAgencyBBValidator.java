package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.util.ModuloUtil;
import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * This class checks whether a specific string represents a valid bank account number of Banco Santander.
 * <p/>
 * Reference: <a href="http://hostmarcasite.com.br/fluir/vendor/eduardokum/laravel-boleto/manuais/Regras%20Validacao%20Conta%20Corrente%20VI_EPS.pdf">Banco do Brasil.</a>
 */
public final class BankAgencyBBValidator implements DigitValidator {

    public static final BankAgencyBBValidator INSTANCE = new BankAgencyBBValidator();

    private BankAgencyBBValidator() {
    }

    /**
     * Checks whether the specified string represents a valid agency and bank account number of Banco do Brasil.
     *
     * @param bankAgency The sequence to verify.
     * @return {@code true} if the sequence is a valid bank account number; {@code false} otherwise.
     */
    public boolean valid(String bankAgency) {
        if (StringUtils.isBlank(bankAgency)
                || (StringUtils.length(bankAgency) > 5)
                || !bankAgency.matches("\\d+[0-9X]")
                || bankAgency.matches("0+|1+|2+|3+|4+|5+|6+|7+|8+|9+")) {
            return false;
        }

        int bankAgencySize = bankAgency.length();
        char digit = bankAgency.charAt(bankAgencySize - 1);
        return ModuloUtil.compute(bankAgency.substring(0, bankAgencySize - 1)).orElse("")
                .equals(String.valueOf(digit));
    }
}
