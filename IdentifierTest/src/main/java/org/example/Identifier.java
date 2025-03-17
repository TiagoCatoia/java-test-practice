package org.example;

public class Identifier {
    public static boolean isValidIdentifier(String identifier) {
        if (identifier == null || identifier.isEmpty() || identifier.length() > 6) {
            return false;
        }

        if (!Character.isLetter(identifier.charAt(0))) {
            return false;
        }

        for (int i = 1; i < identifier.length(); i++) {
            char ch = identifier.charAt(i);
            if (!Character.isLetterOrDigit(ch)) {
                return false;
            }
        }

        return true;
    }
}