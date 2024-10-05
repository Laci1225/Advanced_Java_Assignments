package org.forth_class;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.*;

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
        System.out.println(nextDaySupplier.get());
        System.out.println(nextDaySupplier.get());
        System.out.println(nextDaySupplier.get());
        System.out.println(nextDaySupplier.get());
        System.out.println(nextDaySupplier.get());
        System.out.println(nextDaySupplier.get());
        System.out.println(nextDaySupplier.get());
    }
};

