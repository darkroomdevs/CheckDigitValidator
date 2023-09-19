package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.util.ModuloUtil;
import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * This class checks whether a specific string represents a valid bank account number of Banco Bradesco
 * <p/>
 * Reference: <a href="https://banco.bradesco/assets/pessoajuridica/pdf/mpo_arquivos_layout_400P.pdf">Banco Bradesco Reference</a>
 */
public final class BankAccountBradescoValidator implements DigitValidator {

    public static final BankAccountBradescoValidator INSTANCE = new BankAccountBradescoValidator();

    private BankAccountBradescoValidator() {
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
        String pivot = "2765432";
        int rest = ModuloUtil.generateSum(StringUtils.leftPad(bankAccount.substring(0, bankAccountSize - 1), 7, "0"), pivot) % 11;
        char expectDigit = (rest == 0) ? '0' : (rest == 1) ? 'P' : Character.forDigit(11 - rest, 10);
        return digit == expectDigit;
    }
}
