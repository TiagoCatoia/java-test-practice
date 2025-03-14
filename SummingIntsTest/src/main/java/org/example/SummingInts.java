package org.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.max;

public class SummingInts {
    public List<Integer> add(List<Integer> left, List<Integer> right) {
        if (left == null || right == null)
            return null;

        // Reverses the numbers so the least significant digit is on the left
        Collections.reverse(left);
        Collections.reverse(right);

        LinkedList<Integer> result = new LinkedList<>();
        int carry = 0;

        // While there is a digit, keeps summing, taking carries into consideration
        for (int i = 0; i < max(left.size(), right.size()); i++) {
            int leftDigit = left.size() > i ? left.get(i) : 0;
            int rightDigit = right.size() > i ? right.get(i) : 0;

            // Throws an exception if the pre-condition does not hold
            if (leftDigit < 0 || leftDigit > 9 || rightDigit < 0 || rightDigit > 9)
                throw new IllegalArgumentException();

            // Sums the left digit with the right digit with the possible carry
            int sum = leftDigit + rightDigit + carry;

            // The digit should be a number between 0 and 9.
            // We calculate it by taking the rest of the division (the % operator) of the sum by 10.
            result.addFirst(sum % 10);

            // If the sum is greater than 10, carries the rest of the division to the next digit
            carry = sum / 10;
        }
        // if still have carry, add in the start of the list
        if (carry > 0) {
            result.addFirst(carry);
        }
        return result;
    }
}
