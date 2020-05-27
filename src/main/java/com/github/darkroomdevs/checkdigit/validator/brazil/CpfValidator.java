package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.util.ModuloUtil;
import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * This class checks whether a specific string represents a valid CPF.
 * <p/>
 * Reference: https://receita.economia.gov.br/interface/lista-de-servicos/cadastros/cpf
 */
public final class CpfValidator implements DigitValidator {

    public static final CpfValidator INSTANCE = new CpfValidator();

    private CpfValidator() {
    }

    /**
     * Checks whether the specified string represents a valid CPF.
     *
     * @param cpf The sequence to verify.
     * @return {@code true} if the sequence is a valid CPF; {@code false} otherwise.
     */
    public boolean valid(String cpf) {
        if (StringUtils.length(cpf) != 11
                || !cpf.matches("\\d+")
                || cpf.matches("0+|1+|2+|3+|4+|5+|6+|7+|8+|9+")) {
            return false;
        }

        return ModuloUtil.compute(cpf.substring(0, 9), 10).orElse("").equals(String.valueOf(cpf.charAt(9)))
                && ModuloUtil.compute(cpf.substring(0, 10), 11).orElse("").equals(String.valueOf(cpf.charAt(10)));
    }
}
