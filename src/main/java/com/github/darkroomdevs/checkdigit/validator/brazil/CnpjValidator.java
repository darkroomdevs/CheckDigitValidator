package com.github.darkroomdevs.checkdigit.validator.brazil;

import com.github.darkroomdevs.checkdigit.util.ModuloUtil;
import com.github.darkroomdevs.checkdigit.validator.DigitValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * This class checks whether a specific string represents a valid CNPJ.
 * <p/>
 * Reference: https://receita.economia.gov.br/contato/fale-conosco/empresa/cnpj/cnpj-cadastro-nacional-de-pessoa-juridica
 */
public final class CnpjValidator implements DigitValidator {

    public static final CnpjValidator INSTANCE = new CnpjValidator();

    private CnpjValidator() {
    }

    /**
     * Checks whether the specified string represents a valid CNPJ.
     *
     * @param cnpj The sequence to verify.
     * @return {@code true} if the sequence is a valid CNPJ; {@code false} otherwise.
     */
    @Override
    public boolean valid(String cnpj) {
        if (StringUtils.length(cnpj) != 14 || !cnpj.matches("\\d+")) {
            return false;
        }

        return ModuloUtil.compute(cnpj.substring(0, 12), 9).orElse("").equals(String.valueOf(cnpj.charAt(12)))
                && ModuloUtil.compute(cnpj.substring(0, 13), 9).orElse("").equals(String.valueOf(cnpj.charAt(13)));
    }
}
