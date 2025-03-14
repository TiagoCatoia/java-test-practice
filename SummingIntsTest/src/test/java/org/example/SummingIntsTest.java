package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList ;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

class SummingIntsTest {
    private SummingInts summingInts = new SummingInts();

    @Test
    @DisplayName("Should return sum when right list between 0 and 9")
    void shouldReturnSumWhenRightListBetween0And9(){
        List<Integer> left = new ArrayList <>(List.of(2,3));
        List<Integer> right = new ArrayList <>(List.of(4,2));
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected sum of %s and %s", left, right).isEqualTo(List.of(6,5));
    }

    @Test
    @DisplayName("Should return sum when left list between 0 and 9")
    void shouldReturnSumWhenLeftListBetween0And9(){
        List<Integer> left = new ArrayList <>(List.of(2,3));
        List<Integer> right = new ArrayList <>(List.of(5,2));
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected sum of %s and %s", left, right).isEqualTo(List.of(7,5));
    }
    
    @Test
    @DisplayName("Should return sum when right sum is greater than 9")
    void shouldReturnSumWhenRightSumIsGreaterThan9(){
        List<Integer> left = new ArrayList <>(List.of(2,6));
        List<Integer> right = new ArrayList <>(List.of(6,6));
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected sum of %s and %s", left, right).isEqualTo(List.of(9,2));
    }

    @Test
    @DisplayName("Should return sum when left sum is greater than 9")
    void shouldReturnSumWhenLeftSumIsGreaterThan9(){
        List<Integer> left = new ArrayList <>(List.of(6,2));
        List<Integer> right = new ArrayList <>(List.of(6,6));
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected sum of %s and %s", left, right).isEqualTo(List.of(1,2,8));
    }

    @Test
    @DisplayName("Should return sum when right list between 0 and 9 and result List is greater than 2 numbers")
    void shouldReturnSumWhenRightListBetween0And9AndResultListIsGreaterThan2Numbers(){
        List<Integer> left = new ArrayList <>(List.of(2,6));
        List<Integer> right = new ArrayList <>(List.of(7,6));
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected sum of %s and %s", left, right).isEqualTo(List.of(1,0,2));
    }
    
    @Test
    @DisplayName("Should return sum when both left and right sums are greater than 9")
    void shouldReturnSumWhenInRightAndLeftSumIsGreaterThan9(){
        List<Integer> left = new ArrayList <>(List.of(6,8));
        List<Integer> right = new ArrayList <>(List.of(6,8));
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected sum of %s and %s", left, right).isEqualTo(List.of(1,3,6));
    }

    @ParameterizedTest
    @MethodSource("provideDataToInvalidDataTest")
    @DisplayName("Should Throw IllegalArgumentException when left or right is lower than 0 or greater than 9")
    void shouldThrowIllegalArgumentExceptionWhenLeftOrRightIsLowerThan0OrGreaterThan9(List<Integer> left, List<Integer> right){
        assertThatThrownBy(() -> summingInts.add(left,right)).as("Expected Throw IllegalArgumentException when sum %s and %s", left, right).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> provideDataToInvalidDataTest() {
        return Stream.of(
                Arguments.of(new ArrayList <Integer>(List.of(-1, 2)), new ArrayList <Integer>(List.of(2, 3))),
                Arguments.of(new ArrayList <Integer>(List.of(1, -2)), new ArrayList <Integer>(List.of(2, 3))),
                Arguments.of(new ArrayList <Integer>(List.of(2, 1)), new ArrayList <Integer>(List.of(-1, 1))),
                Arguments.of(new ArrayList <Integer>(List.of(2, 1)), new ArrayList <Integer>(List.of(1, -1))),
                Arguments.of(new ArrayList <Integer>(List.of(-11, 2)), new ArrayList <Integer>(List.of(2, 3))),
                Arguments.of(new ArrayList <Integer>(List.of(2, 1)), new ArrayList <Integer>(List.of(1, -11))),
                Arguments.of(new ArrayList <Integer>(List.of(10, 2)), new ArrayList <Integer>(List.of(1, 2))),
                Arguments.of(new ArrayList <Integer>(List.of(1, 10)), new ArrayList <Integer>(List.of(2, 3))),
                Arguments.of(new ArrayList <Integer>(List.of(1, 2)), new ArrayList <Integer>(List.of(10, 3))),
                Arguments.of(new ArrayList <Integer>(List.of(1, 3)), new ArrayList <Integer>(List.of(3, 10))),
                Arguments.of(new ArrayList <Integer>(List.of(15, 2)), new ArrayList <Integer>(List.of(1, 2))),
                Arguments.of(new ArrayList <Integer>(List.of(1, 3)), new ArrayList <Integer>(List.of(3, 15)))
        );
    }
    
    @Test
    @DisplayName("Should return null when right is null")
    void shouldReturnNullWhenRightIsNull(){
        List<Integer> left = new ArrayList <>(List.of(1,2));
        List<Integer> right = null;
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected null when sum %s and %s", left, right).isNull();
    }

    @Test
    @DisplayName("Should return null when left is null")
    void shouldReturnNullWhenLeftIsNull(){
        List<Integer> left = null;
        List<Integer> right = new ArrayList <>(List.of(1,2));
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected null when sum %s and %s", left, right).isNull();
    }

    @Test
    @DisplayName("Should return left list when right list is empty")
    void shouldReturnLeftListWhenRightListIsEmpty(){
        List<Integer> left = new ArrayList <>(List.of(1,2));
        List<Integer> right = new ArrayList <>(List.of());
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected %s of %s and %s", left, left, right).isEqualTo(List.of(1,2));
    }

    @Test
    @DisplayName("Should return right list when left list is empty")
    void shouldReturnRightListWhenLeftListIsEmpty(){
        List<Integer> left = new ArrayList <>(List.of());
        List<Integer> right = new ArrayList <>(List.of(1,2));
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected %s of %s and %s", right, left, right).isEqualTo(List.of(1,2));
    }

    @Test
    @DisplayName("Should return empty when left and right is empty List")
    void shouldReturnAEmptyListWhenLeftAndRightIsAEmptyList(){
        List<Integer> left = new ArrayList <>(List.of());
        List<Integer> right = new ArrayList <>(List.of());
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected empty when sum %s and %s", left, right).isEqualTo(List.of());
    }

    @Test
    @DisplayName("Should return sum when right sum is 0")
    void shouldReturnSumListWhenRightSumIs0(){
        List<Integer> left = new ArrayList <>(List.of(1,0));
        List<Integer> right = new ArrayList <>(List.of(1,0));
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected sum of %s and %s", left, right).isEqualTo(List.of(2,0));
    }
    
    @Test
    @DisplayName("Should return sum list when left sum is 0")
    void shouldReturnSumListWhenLeftSumIs0(){
        List<Integer> left = new ArrayList <>(List.of(0,1));
        List<Integer> right = new ArrayList <>(List.of(0,1));
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected sum of %s and %s", left, right).isEqualTo(List.of(0,2));
    }

    @Test
    @DisplayName("Should return sum when right sum is 9")
    void shouldReturnSumListWhenRightSumIs9(){
        List<Integer> left = new ArrayList <>(List.of(1,0));
        List<Integer> right = new ArrayList <>(List.of(3,9));
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected sum of %s and %s", left, right).isEqualTo(List.of(4,9));
    }

    @Test
    @DisplayName("Should return sum list when left sum is 9")
    void shouldReturnSumListWhenLeftSumIs9(){
        List<Integer> left = new ArrayList <>(List.of(9,3));
        List<Integer> right = new ArrayList <>(List.of(0,2));
        List<Integer> result = summingInts.add(left,right);
        assertThat(result).as("Expected sum of %s and %s", left, right).isEqualTo(List.of(9,5));
    }
}