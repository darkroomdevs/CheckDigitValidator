package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.util.ModuloUtil;
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
     * Checks whether the specified string represents a valid agency and bank account number of Banco Banrisul
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
        return ModuloUtil.compute(bankAccount.substring(0, bankAccountSize - 1), pivot).orElse("")
                .equals(String.valueOf(digit == '6' ? '1' : digit));
    }
}
