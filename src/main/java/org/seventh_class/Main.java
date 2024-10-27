package org.seventh_class;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        args = new String[]{"apple", "banana", "cherry", "date", "elderberry", "fig", "grape"};
        System.out.println(Arrays.stream(args)
                .map(String::length).toList());
        args = new String[]{"1", "2", "3", "4", "5", "6d", "7", "8", "9", "10", "s11", "12", "13", "14f", "15", "16", "17", "18", "19", "20"};
        System.out.println(Arrays.stream(args)
                .mapMulti((String s, Consumer<Integer> consumer) -> {
                    try {
                        consumer.accept(Integer.parseInt(s));
                    } catch (NumberFormatException ignored) {
                    }
                })
                .filter(i -> i % 2 == 0)
                .filter(i -> i > 8 ).toList());
        var a = zipStreams(Stream.of(1, 2, 3, 4, 5,6), Stream.of("6bb", 7, List.of(1,2), "sfd9", "10fd"));
        a.map(Stream::toList).forEach(System.out::print);

        //some filenames as args

        /*args = new String[]{"file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt", "file7.txt", "file8.txt", "file9.txt", "file10.txt"};
        Arrays.stream(args).mapMulti((String filename, Consumer<Map<Integer, Integer>> consumer) -> {
            try {
                consumer.accept(readFileLines(filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).sorted(Comparator.comparingInt((Map<Integer, Integer> m) -> m.get(0)).reversed()
                        .thenComparing(m -> m.get(1)).reversed())
                .forEach(m -> System.out.println(m.get(0) + " " + m.get(1)));
*/

    }
    public static Map<Integer, Integer> readFileLines(String filename) throws IOException {
        //read file and return number of line
        //readLine
        //BufferedReader
        var path = Path.of(filename);
        Integer numberOfLines = (int) Files.lines(path).count();
        Integer numberOfCharactersInTheFirstWord = Files.lines(path).findFirst().get().length();
        return Map.of(numberOfLines, numberOfCharactersInTheFirstWord);
    }

    public static boolean isPrime(int n) {
        return n > 1 && IntStream.range(2, (int) Math.sqrt(n) + 1).noneMatch(i -> n % i == 0);
    }
    public static Stream<Integer> firstNPrime(int n){
        return Stream.iterate(2, i -> i + 1).filter(Main::isPrime).limit(n);
    }
    public static <T> Stream<Stream<T>> zipStreams(Stream<T> stream1, Stream<T> stream2){
        var list1 = stream1.toList();
        var list2 = stream2.toList();
        return Stream.iterate(0, i -> i + 1)
                .map(i -> Stream.of(list1.get(i),list2.get(i)))
                .limit(Math.min(list1.size(),list2.size()));
    }

}
