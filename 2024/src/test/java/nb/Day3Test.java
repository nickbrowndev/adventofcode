package nb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day3Test {

    public static final String DATA = "day3input.txt";
    public static final String TEST_DATA = "day3testinput.txt";
    public static final String TEST_DATA_PT2 = "day3pt2testinput.txt";

    @Test
    public void shouldDoPart1Test() {

        Integer result = new Day3(TEST_DATA).doPartOne();

        assertEquals(161, result);
    }

    @Test
    public void shouldDoPart1() {

        Integer result = new Day3(DATA).doPartOne();

        System.out.println(result);
    }

    @Test
    public void shouldDoPart2Test() {
        Integer result = new Day3(TEST_DATA_PT2).doPartTwo();

        assertEquals(48, result);
    }

    @Test
    public void shouldDoPart2() {
        Integer result = new Day3(DATA).doPartTwo();

        System.out.println(result);
    }
}
