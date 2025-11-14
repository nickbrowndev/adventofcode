package nb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Day1Test {

    public static final String DATA = "day1input.txt";
    public static final String TEST_DATA = "day1testinput.txt";

    public static Day1 testDay;
    public static Day1 day;

    @BeforeAll
    public static void setUp() {
        testDay = new Day1(TEST_DATA);
        day = new Day1(DATA);
    }

    @Test
    public void shouldDoPart1Test() {

        Integer result = testDay.doPartOne();

        assertEquals(11, result);
    }

    @Test
    public void shouldDoPart1() {

        Integer result = day.doPartOne();

        System.out.println(result);
    }

    @Test
    public void shouldDoPart2Test() {
        Integer result = testDay.doPartTwo();

        assertEquals(31, result);
    }
    @Test
    public void shouldDoPart2() {
        Integer result = day.doPartTwo();

        System.out.println(result);
    }
}
