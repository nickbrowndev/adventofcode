package nb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {


    private List<String> lines;
    private List<int[]> allValues;
    private enum Direction {ASC, DESC};

    public Day2(String path) {
        lines = new DataLoader().getLinesFromFileResource(path);

        //lines.forEach(System.out::println);
        allValues = lines.stream().map(line -> Arrays.stream(line.split("\\s")).mapToInt(Integer::parseInt).toArray()).collect(Collectors.toList());
    }

    public Integer doPartOne() {
      
        int count = 0;
        for (int[] values : allValues) {
            count += isSafe(values) ? 1 : 0;
            //System.out.println(count);
        }
        
        return count;
    }

    public Integer doPartTwo() {

        int count = 0;
        for (int[] values : allValues) {
            if (isSafe2(values)) {
                count++;
                continue;
            } 

            // Brute force it by checking the list with each value removed
            List<Integer> valuesList = new ArrayList<>(Arrays.stream(values).boxed().toList());
            for (int i = 0; i < valuesList.size(); i++) {
                List<Integer> modifiedValues = new ArrayList<>(valuesList);
                modifiedValues.remove(i);

                if (isSafe(modifiedValues)) {
                    count++;
                    break;
                }
            }
            
            System.out.println("result: " + count);
        }
        
        return count;
    }

    private boolean isSafe(List<Integer> values) {
        return isSafe(values.stream().mapToInt(i -> i).toArray());
    }
    
    private boolean isSafe(int[] values) {

        Direction previousDirection = null;

            for (int i = 1; i < values.length; i++) {

                int previousValue = values[i - 1];
                int currentValue = values[i];

            //System.out.println(previousValue + " " + currentValue);
            
                Direction direction = getDirection(previousValue, currentValue);
                if (previousDirection == null) {
                    previousDirection = direction;
                }

            //System.out.println("Direction " + direction + " " + previousDirection);
                if (checkBounds(previousValue, currentValue) && direction == previousDirection) {
                    //System.out.println("continuing");
                    continue;
                } else { 
                    //System.out.println("ending");
                    return false;
                }
            }


        return true;
    }
    
    private boolean isSafe2(int[] values) {

        Direction previousDirection = null;
        boolean safe = true;

        for (int i = 1; i < values.length; i++) {

            int previousValue = values[i - 1];
            int currentValue = values[i];

            System.out.println(previousValue + " " + currentValue);
        
            Direction direction = getDirection(previousValue, currentValue);
            if (previousDirection == null) {
                previousDirection = direction;
            }

            System.out.println("Direction " + direction + " " + previousDirection);
            if (checkBounds(previousValue, currentValue) && direction == previousDirection) {
                System.out.println("continuing");
                continue;
            } else { 
                System.out.println("ending");
                safe = false;
                break;
            }
        }

        return safe;


    }

    private Check checkValues(int value1, int value2) {

        boolean boundsValid = checkBounds(value1, value2);
        Direction direction = getDirection(value1, value2);

        return new Check(boundsValid, direction);
    }

    private record Check(boolean valid, Direction direction) {}

    private Direction getDirection(int previousValue, int value) {
        return value > previousValue ? Direction.ASC : Direction.DESC;
    }

    private boolean checkBounds(int previousValue, int value) {
        int comparison = value - previousValue;
        return !(comparison == 0 || Math.abs(comparison) > 3);
    }
}


