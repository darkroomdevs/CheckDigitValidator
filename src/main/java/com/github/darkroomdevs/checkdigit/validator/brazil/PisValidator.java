package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.util.ModuloUtil;
import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * This class checks whether a specific string represents a valid PIS.
 */
public final class PisValidator implements DigitValidator {

    public static final PisValidator INSTANCE = new PisValidator();

    private PisValidator() {
    }

    /**
     * Checks whether the specified string represents a valid PIS.
     *
     * @param pis The sequence to verify.
     * @return {@code true} if the sequence is a valid PIS; {@code false} otherwise.
     */
    public boolean valid(String pis) {
        if (StringUtils.length(pis) != 11
                || !pis.matches("\\d+")
                || pis.matches("0+|1+|2+|3+|4+|5+|6+|7+|8+|9+")) {
            return false;
        }

        return ModuloUtil.compute(pis.substring(0, 10), 11).orElse("").equals(String.valueOf(pis.charAt(10)));
    }
}
