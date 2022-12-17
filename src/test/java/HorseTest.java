
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    private Horse horse;

    @BeforeEach
    void init() {
        horse = new Horse("Julius", 10.3, 11.3);
    }

    @AfterEach
    void clear() {
        horse = null;
    }

    @Nested
    public class FirstConstructorArgumentTest {


        @ParameterizedTest
        @NullSource
        void whenPassingNullThrowsIllegalArgumentExceptionWithMessageTest(String name) {
            assertAll(() -> {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 11.4));
                assertEquals("Name cannot be null.", exception.getMessage());
            });
        }

        @ParameterizedTest()
        @ValueSource(strings = {"", " ", "   ", "\n", "\n\n"})
        void whenPassingEmptyStringOrSpacesThrowsIllegalArgumentExceptionWithMessageTest(String name) {
            assertAll(() -> {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 11.4));
                assertEquals("Name cannot be blank.", exception.getMessage());
            });
        }

    }

    @Nested
    public class SecondConstructorArgumentTest {

        @ParameterizedTest
        @ValueSource(doubles = {-0.1, -5.0, -345.3})
        void whenPassingNegativeValueThrowsIllegalArgumentExceptionWithMessageTest(double speed) {
            assertAll(() -> {
                Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("NoName", speed));
                assertEquals("Speed cannot be negative.", exception.getMessage());
            });
        }

    }

    @Nested
    public class ThirdConstructorArgumentTest {

        @ParameterizedTest
        @ValueSource(doubles = {-0.1, -4.34, -456.3})
        void whenPassingNegativeValueThrowsIllegalArgumentExceptionWithMessageTest(double distance) {
            assertAll(() -> {
                Exception exception;
                exception = assertThrows(IllegalArgumentException.class, () -> new Horse("NoName", 40, distance));
                assertEquals("Distance cannot be negative.", exception.getMessage());
            });
        }

    }

    @Test
    void mustCorrectlyGetNameTest() {
        String expected = "Julius";
        assertEquals(expected, horse.getName());
    }

    @Test
    void mustCorrectlyGetSpeedTest() {
        double expected = 10.3;
        assertEquals(expected, horse.getSpeed());
    }

    @Test
    void mustCorrectlyGetDistanceAndReturnZeroIfObjectWasCreatedWithTwoParametersTest() {
        assertAll(() -> {
            double expected = 11.3;
            assertEquals(expected, horse.getDistance());
        }, () -> {
            double expected = 0.0;
            assertEquals(expected, new Horse("NoName", 6.7).getDistance());
        });
    }

    @ParameterizedTest
    @CsvSource({"0.2, 0.9"})
    void methodMoveMustUseGetRandomDoubleTest(double min, double max) {
        try (MockedStatic<Horse> mock = Mockito.mockStatic(Horse.class)) {
            horse.move();
            mock.verify(() -> Horse.getRandomDouble(min, max));
        }
    }

    @ParameterizedTest
    @CsvSource({"0.2, 0.9"})
    void methodMoveMustCorrectlyCalculateDistanceTest(double min, double max) {
        try (MockedStatic<Horse> mock = Mockito.mockStatic(Horse.class)) {
            mock.when(() -> Horse.getRandomDouble(min, max)).thenReturn(5.5);
            assertEquals(5.5, Horse.getRandomDouble(min, max));
            mock.verify(() -> Horse.getRandomDouble(min, max));
        }
    }
}