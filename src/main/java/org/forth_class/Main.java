package org.forth_class;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.function.*;
import java.util.stream.IntStream;

enum Weekday {
    SUNDAY("vasárnap"),
    MONDAY("hétfő"),
    TUESDAY("kedd"),
    WEDNESDAY("szerda"),
    THURSDAY("csütörtök"),
    FRIDAY("péntek"),
    SATURDAY("szombat");
    private final String hungarianName;
    Weekday(String hungarianName) {
        this.hungarianName = hungarianName;
    }
    final BiFunction<Integer, Integer, Weekday> getWeekday2 = (month, day) ->
            Weekday.values()[new GregorianCalendar(new GregorianCalendar().get(Calendar.YEAR), month, day).get(Calendar.DAY_OF_WEEK) - 1];
    final BiPredicate<Integer,Integer> isThisDay = (month, day) -> getWeekday2.apply(month, day) == this;
    final Predicate<Weekday> isEarlierThan = (weekday2) -> this.ordinal() < weekday2.ordinal();
    final Function<Integer, Weekday> nextDayN = n -> Weekday.values()[(this.ordinal() + n) % Weekday.values().length];
    final Supplier<Weekday> nextDay = () -> Weekday.values()[(this.ordinal() + 1) % Weekday.values().length];

}

public class Main {

    static Supplier<Weekday> getNextDay() {
        int[] index = {Weekday.MONDAY.ordinal()};
        return () -> Weekday.values()[index[0]++ % 7];
    }
    public static int[] createArray(int length, IntUnaryOperator f) {
        return IntStream.range(0,length).map(f).toArray();
    }

    public static int[][] createArrayBi(int length, IntBinaryOperator f) {
        return IntStream.range(0,length)
                .mapToObj(i -> IntStream.range(0, i)
                        .map(j -> f.applyAsInt(i,j))
                        .toArray())
                .toArray(int[][]::new);
    }
    public static IntFunction<Integer> compose(IntFunction<Integer> f, IntFunction<Integer> g) {
        return x -> g.apply(f.apply(x));

    }
    public static BiFunction<Function<Integer,Integer>, Integer, Function<Integer,Integer>> iterate=
            //(fun, count) -> n -> {
            //    for (int i = 0; i < count; i++) {
            //        n = fun.apply(n);}
            //    return n;
            //};
            (fun, count) -> n -> IntStream.range(0, count).reduce(n, (acc, i) -> fun.apply(acc));


    public static void main(String[] args) {
        System.out.println("Hello, World!");
        var monday = Weekday.MONDAY;
        var friday = Weekday.FRIDAY;
        System.out.println(monday.nextDayN.apply(7));
        System.out.println(monday.nextDay.get());
        System.out.println(monday.isEarlierThan.test(friday));
        System.out.println(monday.isThisDay.test(1, 5));

        Supplier<Weekday> nextDaySupplier = getNextDay();
        System.out.println(nextDaySupplier.get());

        //Sort the command line arguments based on the number of 'a' characters in them.
        args = new String[]  {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape"};
        //Sort the command line arguments based on the number of 'a' characters in them.
        System.out.println(args);

        Arrays.sort( args,Comparator.comparing((String s) -> s.chars().filter(c -> c == 'a').count()).reversed());
        System.out.println(Arrays.toString(args));
        //Sort the command line arguments so that those arguments that contains names of weekdays come first.
        args = new String[] {"MONDAY", "TUEASD" , "ASDFRINDAY", "ASDFRIDAYASD", "ASDASD"};
        var newArgs = Arrays.stream(args).sorted(
                Comparator.comparing((String s) -> Arrays.stream(Weekday.values())
                                .anyMatch(weekday -> s.contains(weekday.name()))).reversed()
        ).toArray(String[]::new);
        System.out.println(Arrays.toString(newArgs));

        System.out.println(Arrays.toString(createArray(10, i -> i * 2)));
        System.out.println(Arrays.deepToString(createArrayBi(10, (i, j) -> i * j)));

        System.out.println(compose(x -> x + 1, x -> x * 2).apply(5));
    }
};

