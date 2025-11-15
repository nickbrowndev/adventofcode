package nb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class Day2Test {

    public static final String DATA = "day2input.txt";
    public static final String TEST_DATA = "day2testinput.txt";

    public static Day2 testDay;
    public static Day2 day;

    @BeforeAll
    public static void setUp() {
        testDay = new Day2(TEST_DATA);
        day = new Day2(DATA);
    }

    @Test
    public void shouldDoPart1Test() {

        Integer result = testDay.doPartOne();

        assertEquals(2, result);
    }

    @Test
    public void shouldDoPart1() {

        Integer result = day.doPartOne();

        System.out.println(result);
    }

    @Disabled
    @Test
    public void shouldDoPart2Test() {
        Integer result = testDay.doPartTwo();

        assertEquals(4, result);
    }

    @Test
    public void shouldDoPart2() {
        Integer result = day.doPartTwo();

        System.out.println(result);
    }
}
