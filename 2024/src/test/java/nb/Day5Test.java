package nb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day5Test {

    public static final String DATA = "day5input.txt";
    public static final String TEST_DATA = "day5testinput.txt";

    @Test
    public void shouldDoPart1Test() {

        Integer result = new Day5(TEST_DATA).doPartOne();

        assertEquals(143, result);
    }

    @Test
    public void shouldDoPart1() {

        Integer result = new Day5(DATA).doPartOne();

        System.out.println(result);
    }

    @Test
    public void shouldDoPart2Test() {
        Integer result = new Day5(TEST_DATA).doPartTwo();

        assertEquals(123, result);
    }

    @Test
    public void shouldDoPart2() {
        Integer result = new Day5(DATA).doPartTwo();

        System.out.println(result);
    }
}
