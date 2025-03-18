package org.example.Identifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.Identifier.Identifier.isValidIdentifier;

class IdentifierTest {

    @Tag("UnitTest")
    @ParameterizedTest
    @MethodSource("provideValidIdentifiers")
    @DisplayName("Should return true if identifier has between 1 and 6 characters and starts with a letter")
    void shouldReturnTrueIfIdentifierHasValidLengthAndStartsWithLetter(String identifier) {
        assertThat(isValidIdentifier(identifier))
                .as("identifier '%s' should be valid (true)", identifier)
                .isTrue();
    }

    public static Stream<Arguments> provideValidIdentifiers() {
        return Stream.concat(
                Stream.concat(
                        IntStream.rangeClosed('a', 'z').mapToObj(c -> Arguments.of(String.valueOf((char) c))),
                        IntStream.rangeClosed('A', 'Z').mapToObj(c -> Arguments.of(String.valueOf((char) c)))
                ),
                Stream.of(
                        Arguments.of("A1"),
                        Arguments.of("Bac"),
                        Arguments.of("Car2"),
                        Arguments.of("Dog10"),
                        Arguments.of("Zebra"),
                        Arguments.of("Xyz12A"),
                        Arguments.of("Apples")
                )
        );
    }

    @Tag("UnitTest")
    @ParameterizedTest
    @MethodSource("provideInvalidIdentifiersStartingWithNumber")
    @DisplayName("Should return false if identifier starts with a number")
    void shouldReturnFalseIfIdentifierStartsWithNumber(String identifier){
        assertThat(isValidIdentifier(identifier))
                .as("identifier '%s' should be invalid (false)", identifier)
                .isFalse();
    }

    public static Stream<Arguments> provideInvalidIdentifiersStartingWithNumber() {
        return Stream.of(
                Arguments.of("1"),
                Arguments.of("2x"),
                Arguments.of("3Do"),
                Arguments.of("4Dog"),
                Arguments.of("5678A"),
                Arguments.of("65789A"),
                Arguments.of("7Apple"),
                Arguments.of("8Tee56"),
                Arguments.of("9Hello"),
                Arguments.of("0Start")
        );
    }

    @Tag("UnitTest")
    @ParameterizedTest
    @MethodSource("provideInvalidIdentifiersContainingSpecialChars")
    @DisplayName("Should return false if identifier contains any character that is not a letter or number")
    void shouldReturnFalseIfIdentifierContainsAnyCharacterThatIsNotALetterOrNumber(String identifier){
        assertThat(isValidIdentifier(identifier))
                .as("identifier '%s' should be invalid (false)", identifier)
                .isFalse();
    }

    public static Stream<Arguments> provideInvalidIdentifiersContainingSpecialChars() {
        return Stream.of(
                Arguments.of("@abc"),
                Arguments.of("#user"),
                Arguments.of("!test"),
                Arguments.of(" $data"),
                Arguments.of("ab@c"),
                Arguments.of("us#er"),
                Arguments.of("te!st"),
                Arguments.of("sp ace"),
                Arguments.of("abc@"),
                Arguments.of("user#"),
                Arguments.of("test!"),
                Arguments.of("data$"),
                Arguments.of("trailing "),
                Arguments.of("mix@#code"),
                Arguments.of(" spaceBoth "),
                Arguments.of("123@abc"),
                Arguments.of("!@#$%^&*")
        );
    }

    @Tag("UnitTest")
    @ParameterizedTest
    @ValueSource(strings = {
            "abcdefg",
            "Brazilian",
            "Mountains1",
            "Xyz12345",
            "Testing123",
            "SuperLong"
    })
    @DisplayName("Should return false if identifier is longer than 6 characters")
    void shouldReturnFalseIfIdentifierIsLongerThan6(String identifier){
        assertThat(isValidIdentifier(identifier)).as("identifier '%s' should be invalid (false)", identifier).isFalse();
    }

    @Tag("UnitTest")
    @Test
    @DisplayName("Should return false if identifier is null")
    void shouldReturnFalseIfIdentifierIsNull() {
        assertThat(isValidIdentifier(null))
                .as("identifier should be invalid (false) if it is null")
                .isFalse();
    }
}
