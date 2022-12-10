
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


class HorseTest {


    @Nested
    public class FirstConstructorArgumentTest {

        @Test
        @DisplayName("Should not create Horse when name is null.")
        void shouldThrowsIllegalArgumentExceptionWhenFirstArgumentIsNull() {
            assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0));
        }

        @Test
        @DisplayName("Verifying that the thrown IllegalArgumentException contains the desired message.")
        void messageShouldBeNameCannotBeNull() {
            Exception exception;
            exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0));
            assertEquals(exception.getMessage(), "Name cannot be null.");
        }

        @ParameterizedTest()
        @DisplayName("Should throws IllegalArgumentException")
        @ValueSource(strings = {"", " ", "   ", "\n", "\n\n"})
        void shouldThrowsExceptionWhenPassingAnEmptyStringOrWhitespaceCharacters(String name) {
            assertThrows(IllegalArgumentException.class, () -> new Horse(name, 0));
        }

        @ParameterizedTest
        @DisplayName("Should throw an IllegalArgumentException with an desired message when we pass empty strings")
        @ValueSource(strings = {"", " ", "\n", "\n\n", "   "})
        void messageShouldBeNameCannotBeBlack(String name) {
            Exception exception;
            exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 0));
            assertEquals(exception.getMessage(), "Name cannot be blank.");
        }
    }

    @Nested
    public class SecondConstructorArgumentTest {

        @ParameterizedTest
        @ValueSource(doubles = {-1.0, -2.0, -3.0, -4.0, -5.0,})
        void shouldThrowsIllegalArgumentExceptionWhenSpeedIsNegative(double speed) {
            assertThrows(IllegalArgumentException.class, () -> new Horse("NoName", speed));
        }

        @Test
        void messageShouldBeSpeedCannotBeNegative() {
            Exception exception;
            exception = assertThrows(IllegalArgumentException.class, () -> new Horse("NoName", -1.0));
            assertEquals(exception.getMessage(), "Speed cannot be negative.");
        }
    }

    @Nested
    public class ThirdConstructorArgumentTest {

        @ParameterizedTest
        @ValueSource(doubles = {-1.0, -2.0, -3.0})
        void shouldThrowsIllegalArgumentExceptionWhenDistanceIsNegative(double distance) {
            assertThrows(IllegalArgumentException.class, () -> new Horse("NoName", 1, distance));
        }

        @Test
        void messageShouldBeDistanceCannotBeNegative() {
            Exception exception;
            exception = assertThrows(IllegalArgumentException.class, () ->
                    new Horse("NoName", 1, -1.0));
            assertEquals(exception.getMessage(), "Distance cannot be negative.");
        }
    }

    @Test
    void getNameTest() {

    }

    @Test
    void getSpeedTest() {

    }

    @Test
    void getDistanceTest() {

    }

    @Test
    void moveTest() {
    }

    @Test
    void getRandomDoubleTest() {
    }
}