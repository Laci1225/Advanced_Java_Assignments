package org.nineth_class;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        firstNPrimes(10).forEach(System.out::println);

        args = "cherry.txt banana.txt apple.txt date.txt".split(" ");
        String pth = "src/main/java/org/nineth_class/";
    }
    public static String filenames(String[] args, String pth ) {
        return Arrays.stream(args).sorted(Comparator.comparing(Main::numberOfLinesInFile)
                .thenComparing(a-> Main.numberOfWordsInTheFirstLine(pth,a))
                        .reversed())
                .collect(Collectors.joining(", "));
    }

    public static Integer numberOfWordsInTheFirstLine(String pth, String filename) {
        var path = Path.of(pth + filename);
        try {
            var firstLine = Files.readAllLines(path).getFirst();
            return firstLine.split(" ").length;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer numberOfLinesInFile(String filename) {
        var path = Path.of("src/main/java/org/nineth_class/"+filename);
        try {
            return Files.readAllLines(path).size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Stream<Integer> firstNPrimes(int n) {
        return Stream.iterate(2, i -> i + 1)
                .filter(Main::isPrime)
                .limit(n);
    }
    public static Supplier<IntStream> firstNPrimesSupplier =
        () -> IntStream.iterate(2, i -> i + 1)
                .filter(Main::isPrime);


    public static  boolean isPrime(int n) {
        return n > 1 && Stream.iterate(2, i -> i + 1)
                .limit((int) Math.sqrt(n) - 1)
                .noneMatch(i -> n % i == 0);
    }
}
