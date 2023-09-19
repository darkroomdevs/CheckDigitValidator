package com.github.darkroomdevs.checkdigit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

/**
 * This class compute the check digit of a sequence through a slightly more complex method thet take the
 * weighted sum of the digits, modulo 10, with different weights for each number position.
 * <p/>
 * A check digit is a form of redundancy check used for error detection on identification numbers,
 * such as bank account numbers, which are used in an application where they will at least sometimes
 * be input manually. It is analogous to a binary parity bit used to check for errors in computer-generated
 * data. It consists of one or more digits computed by an algorithm from the other digits (or letters)
 * in the sequence input.
 * <p/>
 * With a check digit, one can detect simple errors in the input of a series of characters (usually digits)
 * such as a single mistyped digit or some permutations of two successive digits.
 */
public final class ModuloUtil {

    public static final int MOD10 = 10;
    public static final int MOD11 = 11;

    private ModuloUtil() {
    }

    /**
     * Compute the check digit with weights of 2, 3, 4, 5, 6, 7, 8, 9 and 10.
     *
     * @param sequence The sequence to compute the check digit.
     * @return The check digit of the specified sequence.
     */
    public static Optional<String> compute(String sequence) {
        return compute(sequence, 10, 2);
    }

    /**
     * Compute the check digit with weights from 2 to specified max (increment by 1).
     *
     * @param sequence The sequence to compute the check digit.
     * @param maxWeight The maximum weight.
     * @return The check digit of the specified sequence.
     */
    public static Optional<String> compute(String sequence, int maxWeight) {
        return compute(sequence, maxWeight, 2);
    }

    /**
     * Compute the check digit with weights from {@code minWeight} to {@code maxWeight} (increment by 1).
     *
     * @param sequence The sequence to compute the check digit.
     * @param maxWeight The maximum weight.
     * @param minWeight The minimum weight.
     * @return The check digit of the specified sequence.
     */
    public static Optional<String> compute(String sequence, int maxWeight, int minWeight) {
        return compute(sequence, MOD11, maxWeight, minWeight);
    }

    /**
     * Compute the check digit with weights from {@code minWeight} to {@code maxWeight} (increment by 1).
     *
     * @param sequence The sequence to compute the check digit.
     * @param mod Type of the module.
     * @param maxWeight The maximum weight.
     * @param minWeight The minimum weight.
     * @return The check digit of the specified sequence.
     */
    public static Optional<String> compute(String sequence, int mod, int maxWeight, int minWeight) {
        if (StringUtils.isBlank(sequence) || maxWeight < 3) {
            return Optional.empty();
        }

        List<Integer> weights = generateWeights(maxWeight, minWeight, sequence.length());
        return compute(sequence, mod, weights);
    }

    /**
     * Compute the check digit with weights from {@code pivot}.
     *
     * @param sequence The sequence to compute the check digit.
     * @param pivot List of weights.
     * @return The check digit of the specified sequence.
     */
    public static Optional<String> compute(String sequence, String pivot) {
        return compute(sequence, MOD11, pivot);
    }

    /**
     * Compute the check digit with weights from {@code pivot}.
     *
     * @param sequence The sequence to compute the check digit.
     * @param mod Type of the module.
     * @param pivot List of weights.
     * @return The check digit of the specified sequence.
     */
    public static Optional<String> compute(String sequence, int mod, String pivot) {
        if (StringUtils.isBlank(sequence) || StringUtils.isBlank(pivot)) {
            return Optional.empty();
        }

        List<Integer> weights = generateWeights(pivot);
        return compute(sequence, mod, weights);
    }

    private static Optional<String> compute(String sequence, int mod, List<Integer> weights) {
        int sum = 0;
        for (int i = 0; i < sequence.length(); i++) {
            sum += Integer.parseInt(String.valueOf(sequence.charAt(i))) * weights.get(i);
        }

        return generateDV(sum, mod);
    }

    /**
     * Calculates the weighted sum of a sequence of digits using the given pivot weights.
     *
     * @param sequence The input sequence of digits as a string.
     * @param pivot    The pivot weights as a string.
     * @return The weighted sum of the sequence.
     */
    public static Integer generateSum(String sequence, String pivot) {
        List<Integer> weights = generateWeights(pivot);
        int sum = 0;
        for (int i = 0; i < sequence.length(); i++) {
            sum += (char) (Integer.parseInt(String.valueOf(sequence.charAt(i))) * weights.get(i));
        }
        return sum;
    }

    private static Optional<String> generateDV(int sum, int mod) {
        int digit;
        if (mod == MOD11) digit = sum * 10 % 11;
        else digit = 10 - (sum % 10);

        if (digit == 10) digit = 0;
        return Optional.of(String.valueOf(digit));
    }

    private static List<Integer> generateWeights(int maxWeight, int minWeight, int size) {
        List<Integer> weights = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int weight = i + minWeight;
            if (weight > maxWeight) weight = weight % maxWeight + 1;
            weights.add(0, weight);
        }

        return weights;
    }

    private static List<Integer> generateWeights(String pivot) {
        List<Integer> weights = new ArrayList<>();
        for (int i = 0; i < pivot.length(); i++) {
            weights.add(Integer.parseInt(String.valueOf(pivot.charAt(i))));
        }

        return weights;
    }
}
