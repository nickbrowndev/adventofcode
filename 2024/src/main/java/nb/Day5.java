package nb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
/**
 * https://adventofcode.com/2024/day/5
 * https://adventofcode.com/2024/day/5#part2
 */

public class Day5 {


    private List<String> lines;

    private Data data;

    public Day5(String path) {
        lines = new DataLoader().getLinesFromFileResource(path);
        data = parseInput(lines);
    }

    private Data parseInput(List<String> lines) {
        Data data = new Data();
        boolean finishedRules = false;

        for (String line : lines) {

            if (line.isBlank()) {
                finishedRules = true;
            } else if (!finishedRules) {
                String[] parts = line.split("\\|");
                data.addRule(new Rule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            }
            else
            {
                String[] pages = line.split(",");
                int[] intPages = Arrays.stream(pages).mapToInt(Integer::parseInt).toArray();
                data.addPageOrder(new PageOrder(intPages));
            }
        }

        return data;
    }
    /**
     * https://adventofcode.com/2024/day/5
     */
    public Integer doPartOne() {

        int total = 0;

        for (PageOrder pageOrder : data.getPageOrder()) {
            if (pageOrder.test(data.getRules())) {
                System.out.println("Found valid: " + Arrays.toString(pageOrder.pageOrder()) + " adding " + pageOrder.getMidPage());
                total += pageOrder.getMidPage();
            } else {
                data.addIncorrectPageOrder(pageOrder);
            }
        }
        return total;
    }

    /**
     * https://adventofcode.com/2024/day/5#part2
     */
    public Integer doPartTwo() {

        // Need the list of incorrect to be populated. Ideally would do this 
        // together, but kept separate for cleanliness
        doPartOne();

        int total = 0;
        for (PageOrder pageOrder : data.getIncorrectPageOrder()) {
           while(true) {
           
                Optional<Rule> brokenRule = pageOrder.testAndReturn(data.getRules());

                if (brokenRule.isEmpty()) {

                    total += pageOrder.getMidPage();
                    System.out.println("Processed " + pageOrder.toString() + " total: " + total);
                    break;
                } else { 
                    pageOrder = pageOrder.applyRule(brokenRule.get());
                }
            } 
        }
        
        return total;
    }

    private class Data {

        private List<Rule> rules = new ArrayList<>();
        private List<PageOrder> pageOrder = new ArrayList<>();
        private List<PageOrder> incorrectOrders = new ArrayList<>();
        
        public Data() {
        }

        public void addRule(Rule rule) {
            this.rules.add(rule);
        }
        
        public void addPageOrder(PageOrder pageOrder) {
            this.pageOrder.add(pageOrder);
        }

        public void addIncorrectPageOrder(PageOrder pageOrder) {
            this.incorrectOrders.add(pageOrder);
        }

        public List<Rule> getRules() {
            return this.rules;
        }

        public List<PageOrder> getIncorrectPageOrder() {
            return this.incorrectOrders;
        }

        public List<PageOrder> getPageOrder() {
            return this.pageOrder;
        }
    }

    public record Rule(int priorPage, int afterPage) {

        public boolean test(int... pages) {

            boolean result = true;
            int priorIndex = -1;
            int afterIndex = -1;
            //System.out.println(Arrays.toString(pages));
            for (int i = 0; i < pages.length; i++) {

                //System.out.println(pages[i] + " " + priorPage + " " + afterPage);

                if ((priorIndex == -1) && (pages[i] == priorPage())) {
                    //System.out.println("Found prior " + pages[i]);
                    priorIndex = i;
                }

                if ((afterIndex == -1) && (pages[i] == afterPage())) {
                    //System.out.println("Found after " + pages[i]);
                    afterIndex = i;
                } 
            

                if (priorIndex != -1 && afterIndex != -1) {

                    result = priorIndex < afterIndex;
                    System.out.println("Found applicable rule: " + priorPage() + "[" + priorIndex + "] | " + afterPage() + "[" + afterIndex + "] " + result);
                    break;
                }

            }
            return result;
        }

        public String toString() {
            return this.priorPage() + "|" + this.afterPage();
        }
    }

    public record PageOrder(int... pageOrder) {
        public int getMidPage() {
            int mid = pageOrder().length/2;

            return pageOrder()[mid];
        }

        public boolean test(List<Rule> rules) {

            boolean result = true;

            for (Rule rule : rules) {
                result = result && rule.test(pageOrder());

                if (!result) {
                    break;
                }
            }

            return result;
        }

        public Optional<Rule> testAndReturn(List<Rule> rules) {

            for (Rule rule : rules) {
                if (!rule.test(pageOrder)) {
                    return Optional.of(rule);
                }
            }

            return Optional.empty();
        }

        public PageOrder applyRule(Rule rule) {
            int prior = rule.priorPage();
            int after = rule.afterPage();

            List<Integer> newOrder = new ArrayList<>(this.pageOrder().length);
            for (int i = 0; i < this.pageOrder().length; i++ ) {
                int value = this.pageOrder()[i];

                if (value == after) {
                    newOrder.add(prior);
                }
                if (value != prior) {
                    newOrder.add(value);
                }
            }

            int[] pages = newOrder.stream().mapToInt(Integer::intValue).toArray();

            PageOrder newPageOrder = new PageOrder(pages);

            System.out.println("Updated " + this.toString() + " to " + newPageOrder.toString() + " based on " + rule.toString());
            return newPageOrder;
        }

        public String toString() {
            return Arrays.toString(this.pageOrder());
        }
    }



}
