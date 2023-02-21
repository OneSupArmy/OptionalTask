import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        firstVariant();
        //secondVariant();
    }
    private static void firstVariant() {
        String sentence = "yourapp the quick brown fox jumps over the lazy dog";
        List<String> stringList = new ArrayList<>(List.of(sentence.replaceAll("[\\W|\\d]", " ").trim().split("\\s+")));
        System.out.printf("In this sentence %d words.\nTop 10:\n", stringList.size());
        Map<String, Integer> treeMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        stringList.forEach(s -> {
            if (treeMap.containsKey(s)) treeMap.replace(s, treeMap.get(s) + 1);
            else treeMap.put(s, 1);
        });
        treeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .forEachOrdered(x -> System.out.println(x.getValue() + " - " + x.getKey()));
    }
    private static void secondVariant() {
        String sentence = "yourapp the quick brown fox jumps over the lazy dog";
        Map<String,Long> treeMap = Stream.of(sentence.replaceAll("[\\W|\\d]", " ").trim().split("\\s+"))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
        System.out.printf("In this sentence %d words.\nTop 10:\n", treeMap.values()
                .stream()
                .mapToLong(Long::intValue)
                .sum());
        treeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .forEachOrdered(x -> System.out.println(x.getValue() + " - " + x.getKey()));
    }
}


