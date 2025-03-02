package streams;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamExamples {
    public static void main(String[] args) {

        // FILTER
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Even numbers: " + evenNumbers);

        // MAP
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        List<String> upperCaseWords = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Uppercase words: " + upperCaseWords);

        // SORTED
        numbers = Arrays.asList(5, 3, 8, 1, 2, 9, 7, 4, 6);

        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted numbers: " + sortedNumbers);

        // REDUCE
        numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int sum = numbers.stream()
                .reduce(0, Integer::sum);

        System.out.println("Sum: " + sum);

        // GROUPING
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 20),
                new Person("Charlie", 30),
                new Person("David", 20),
                new Person("Edward", 25)
        );

        Map<Integer, List<Person>> groupedByAge = people.stream()
                .collect(Collectors.groupingBy(person -> person.age));

        System.out.println("Grouped by age: " + groupedByAge);

        // FLATMAP

        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
        );

        List<Integer> flatList = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println("Flat list: " + flatList);
    }

    static class Person {
        String name;
        int age;


        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }


        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}
