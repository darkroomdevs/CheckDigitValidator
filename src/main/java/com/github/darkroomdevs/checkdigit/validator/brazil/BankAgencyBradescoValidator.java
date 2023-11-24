package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.util.ModuloUtil;
import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * This class checks whether a specific string represents a valid bank account number of Banco Bradesco
 * <p/>
 * Reference: <a href="https://banco.bradesco/assets/pessoajuridica/pdf/mpo_arquivos_layout_400P.pdf">Banco Bradesco Reference</a>
 */
public final class BankAgencyBradescoValidator implements DigitValidator {

    public static final BankAgencyBradescoValidator INSTANCE = new BankAgencyBradescoValidator();

    private BankAgencyBradescoValidator() {
    }

    /**
     * Checks whether the specified string represents a valid bank agency number of Banco Bradesco
     *
     * @param bankAgency The sequence to verify.
     * @return {@code true} if the sequence is a valid bank account number; {@code false} otherwise.
     */
    public boolean valid(String bankAgency) {
        if (StringUtils.isBlank(bankAgency)
                || (StringUtils.length(bankAgency) > 5)
                || !bankAgency.matches("\\d+[0-9P]")
                || bankAgency.matches("0+|1+|2+|3+|4+|5+|6+|7+|8+|9+")) {
            return false;
        }

        int bankAgencySize = bankAgency.length();
        char digit = bankAgency.charAt(bankAgencySize - 1);
        return ModuloUtil.compute(StringUtils.leftPad(bankAgency.substring(0, bankAgencySize - 1), 7, "0")).orElse("")
                .equals(String.valueOf(digit == 'P' ? '0' : digit));
    }
}
