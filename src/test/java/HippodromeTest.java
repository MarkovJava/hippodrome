import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {

    @ParameterizedTest
    @NullSource
    void shouldTrowsIllegalArgumentExceptionWhenPassingNullToConstructorTest(List<Horse> horses) {
        assertAll(() -> {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
            String expected = "Horses cannot be null.";
            String actual = exception.getMessage();
            assertEquals(expected, actual);
        });
    }

    @ParameterizedTest
    @EmptySource
    void checkThatConstructorThrowsIllegalArgumentExceptionWhenAnEmptyListIsPassed(List<Horse> horses) {
        assertAll(() -> {
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> new Hippodrome(horses));
            String expected = "Horses cannot be empty.";
            String actual = exception.getMessage();
            assertEquals(expected, actual);
        });
    }
    
}
