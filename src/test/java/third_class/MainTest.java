package third_class;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.third_class.Main.*;


public class MainTest {
    @Test
    public void testTranslateText() {
        // Arrange
        var dictionary = Map.of(
                "apple", "alma",
                "pear", "körte",
                "plum", "szilva"
        );
        var text = "apple pear plum";
        var translatedText = translateText(dictionary, text);
        var expected = "alma körte szilva";
        assertEquals(translatedText, expected);
    }
    @ParameterizedTest
    @CsvSource({"""
            5,5,5
            5,6,5
            5,7,11
            """})
    void testSummer(int a, int b, int expected) {
        assertEquals(summer(a, b), expected);
    }

    @Test
    public void testFirstNPrimes() {
        var primes = firstNPrimes(5);
        var expected = new int[]{2, 3, 5, 7, 11};
        for (int i = 0; i < primes.size(); i++) {
            assert primes.get(i) == expected[i];
        }
        assertEquals(primes.size(), expected.length);
    }
    @Test
    public void testFirstNPrimesThrowsError() {
        try {
            firstNPrimes(-1);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "n must be greater than 0");
        }
    }
    @Test
    public void testFirstNPrimesThrowsError2() {
        assertThrows(IllegalArgumentException.class, () -> firstNPrimes(-1));
    }
    @ParameterizedTest
    @CsvSource({"MONDAY,3"})
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(Weekday day, int n) {
        assertEquals(getWeekday(day, n), Weekday.THURSDAY);
    }

}
