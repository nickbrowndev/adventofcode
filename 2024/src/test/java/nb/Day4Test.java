package nb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day4Test {

    public static final String DATA = "day4input.txt";
    public static final String TEST_DATA = "day4testinput.txt";

    @Test
    public void shouldDoPart1Test() {

        Integer result = new Day4(TEST_DATA).doPartOne();

        assertEquals(18, result);
    }

    @Test
    public void shouldDoPart1() {

        Integer result = new Day4(DATA).doPartOne();

        System.out.println(result);
    }

    @Test
    public void shouldDoPart2Test() {
        Integer result = new Day4(TEST_DATA).doPartTwo();

        assertEquals(9, result);
    }

    @Test
    public void shouldDoPart2() {
        Integer result = new Day4(DATA).doPartTwo();

        System.out.println(result);
    }
}
