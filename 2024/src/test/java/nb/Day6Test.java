package nb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class Day6Test {

    public static final String DATA = "day6input.txt";
    public static final String TEST_DATA = "day6testinput.txt";

    @Disabled
    @Test
    public void shouldDoPart1Test() {

        Integer result = new Day6(TEST_DATA).doPartOne();

        assertEquals(41, result);
    }

    @Disabled
    @Test
    public void shouldDoPart1() {

        Integer result = new Day6(DATA).doPartOne();

        System.out.println(result);
    }

    @Disabled
    @Test
    public void shouldDoPart2Test() {
        Integer result = new Day6(TEST_DATA).doPartTwo();

        assertEquals(-1, result);
    }

    @Disabled
    @Test
    public void shouldDoPart2() {
        Integer result = new Day6(DATA).doPartTwo();

        System.out.println(result);
    }
}
