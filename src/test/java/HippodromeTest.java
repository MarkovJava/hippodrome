import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {

    private List<Horse> horses = new ArrayList<>();

    private List<Horse> horsesMock = new ArrayList<>();

    @BeforeEach
    void init() {
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(String.valueOf(i), i));
        }
        for (int i = 0; i < 50; i++) {
            horsesMock.add(Mockito.mock(Horse.class));
        }
    }

    @AfterEach
    void clear() {
        horses = null;
        horsesMock = null;
    }

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

    @Test
    void getHorsesTest() {
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void moveTest() {
        Hippodrome hippodrome = new Hippodrome(horsesMock);
        hippodrome.move();
        horsesMock.forEach(mockedHorse -> Mockito.verify(mockedHorse).move());
    }

}
