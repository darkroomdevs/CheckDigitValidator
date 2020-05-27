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
        if (StringUtils.isBlank(sequence) || maxWeight < 3) {
            return Optional.empty();
        }

        List<Integer> weights = generateWeights(maxWeight, minWeight, sequence.length());
        int sum = 0;
        for (int i = 0; i < sequence.length(); i++) {
            sum += Integer.parseInt(String.valueOf(sequence.charAt(i)))
                    * Integer.parseInt(String.valueOf(weights.get(i)));
        }

        int digit = sum * 10 % 11;
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
}
