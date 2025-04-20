package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


    //TODO hier weitere Tests erstellen
        @Test
    @DisplayName("Initial screen should display '0'")
    void testInitialScreenValue() {
        Calculator calc = new Calculator();

        String expected = "0";
        String actual = calc.readScreen();
        assertEquals(expected, actual, "Initial screen value should be '0'");
    }

    @Test
    @DisplayName("Single press of clear key resets screen to '0'")
    void testPressClearKeyOnce() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        String expected = "7";
        String actual = calc.readScreen();
        assertEquals(expected, actual, "Screen should display '7' after pressing 7 key");
    
        calc.pressClearKey();
        expected = "0";
        actual = calc.readScreen();
        assertEquals(expected,actual, "Screen should display '0' after pressing clear key once");
    }

    @Test
    @DisplayName("Double press of clear key resets calculator to initial state")
    void testPressClearKeyTwice() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("+");
        calc.pressClearKey();
        calc.pressClearKey();
        String expected = "0";
        String actual = calc.readScreen();

        assertEquals(expected, actual, "Screen should display '0' after pressing clear key twice");
    }

    @Test
    @DisplayName("Binary subtraction operation")
    void testBinaryOperationSubtraction() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(9);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();

        String expected = "6";
        String actual = calc.readScreen();
        assertEquals(expected, actual, "Screen should display '6' after 9 - 3");
    }

    @Test
    @DisplayName("Binary multiplication operation")
    void testBinaryOperationMultiplication() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(6);
        calc.pressBinaryOperationKey("x");
        calc.pressDigitKey(7);
        calc.pressEqualsKey();

        String expected = "42";
        String actual = calc.readScreen();
        assertEquals(expected, actual, "Screen should display '42' after 6 x 7");
    }

    @Test
    @DisplayName("Binary division operation")
    void testBinaryOperationDivision() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(8);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(2);
        calc.pressEqualsKey();

        String expected = "4";
        String actual = calc.readScreen();
        assertEquals(expected, actual, "Screen should display '4' after 8 / 2");
    }

    @Test
    @DisplayName("Inverse unary operation")
    void testUnaryOperationInverse() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(4);
        calc.pressUnaryOperationKey("1/x");

        String expected = "0.25";
        String actual = calc.readScreen();
        assertEquals(expected, actual, "Screen should display '0.25' after 1/4");
    }

    @Test
    @DisplayName("Pressing negative key changes sign of the number")
    void testPressNegativeKey() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(3);
        calc.pressNegativeKey();

        String expected = "-3";
        String actual = calc.readScreen();
        assertEquals(expected, actual, "Screen should display '-3' after pressing negative key");

        calc.pressNegativeKey();
        expected = "3";
        actual = calc.readScreen();
        assertEquals(expected, actual, "Screen should display '3' after pressing negative key again");
    }

// Aufgabe 2 
    @Test
    @DisplayName("Repeated equals key should repeat last operation with the last operand")
    void testRepeatedEqualsKey() {
    Calculator calc = new Calculator();
    calc.pressDigitKey(5);
    calc.pressBinaryOperationKey("+");
    calc.pressDigitKey(3);
    calc.pressEqualsKey(); // Ergebnis sollte 8 sein
    calc.pressEqualsKey(); // Ergebnis sollte 11 sein (8 + 3)
    String expected = "11";
    String actual = calc.readScreen();
    assertEquals(expected, actual, "Screen should display '11' after pressing '=' repeatedly with '+ 3' operation");
    }

    @Test
    @DisplayName("Percentage key applies correctly after binary operation, Percentage key without a prior binary operation throws an exception")
    void testPercentageAfterBinaryOperation() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(1);
        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("%"); // Sollte 10% von 200 berechnen und "20" anzeigen

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.pressEqualsKey();
        });
        assertEquals("220", calc.readScreen(), "Screen should display '220' after calculating 200 + 10%");
    }

}
