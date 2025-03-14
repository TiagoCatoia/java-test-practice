package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.example.StringUtils.substringsBetween;
import static org.junit.jupiter.api.Assertions.fail;

class StringUtilsTest {
    @Test
    @DisplayName("Should result in null when str is null")
    void shouldResultInNullWhenStrIsNull(){
        assertThat(substringsBetween(null, "a", "b") == null);
    }

    @Test
    @DisplayName("Should result in empty array when str is empty")
    void shouldResultInEmptyArrayWhenStrIsEmpty(){
        assertThat(substringsBetween("", "a", "b").length == 0);
    }

    @Test
    @DisplayName("Should thrown NullPointerException when open is null")
    void shouldThrownNullPointerExceptionWhenOpenIsNull(){
        assertThatNullPointerException().isThrownBy(() -> substringsBetween("abcdefg", null, "f"));
    }

    @Test
    @DisplayName("Should result in null when open is empty")
    void shouldResultInNullWhenOpenIsEmpty(){
        assertThat(substringsBetween("abcdefg", "", "f") == null);
    }

    @Test
    @DisplayName("Should thrown NullPointerException when close is null")
    void shouldThrownNullPointerExceptionWhenCloseIsNull(){
        assertThatNullPointerException().isThrownBy(() -> substringsBetween("abcdefg", "b", null));
    }

    @Test
    @DisplayName("Should result in null when close is empty")
    void shouldResultInNullWhenCloseIsEmpty(){
        assertThat(substringsBetween("abcdefg", "b", "") == null);
    }
}