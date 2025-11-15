package nb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class Day3Test {

    public static final String DATA = "day3input.txt";
    public static final String TEST_DATA = "day3testinput.txt";

    public static Day3 testDay;
    public static Day3 day;

    @BeforeAll
    public static void setUp() {
        testDay = new Day3(TEST_DATA);
        day = new Day3(DATA);
    }

    @Test
    public void shouldDoPart1Test() {

        Integer result = testDay.doPartOne();

        assertEquals(161, result);
    }

    @Test
    public void shouldDoPart1() {

        Integer result = day.doPartOne();

        System.out.println(result);
    }

    @Test
    public void shouldDoPart2Test() {
        Integer result = testDay.doPartTwo();

        assertEquals(48, result);
    }

    @Disabled
    @Test
    public void shouldDoPart2() {
        Integer result = day.doPartTwo();

        System.out.println(result);
    }
}
