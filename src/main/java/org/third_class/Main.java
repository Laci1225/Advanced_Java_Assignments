package org.third_class;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(summer(1, 5));

        Map<String,String> hungarianEnglishDictionary = Map.of(
                "apple", "alma",
                "pear", "körte",
                "plum", "szilva"
        );
        System.out.println(translateText(hungarianEnglishDictionary, "apple pear plum"));

        System.out.println(getWeekday(Weekday.MONDAY, 7));
    }



    public static List<Integer> firstNPrimes(int n) {
            List<Integer> primes = new ArrayList<>();
            if (n < 1) {
                throw new IllegalArgumentException("n must be greater than 0");
            }
            int num = 2;
            while (primes.size() < n) {
                if (isPrime(num)) {
                    primes.add(num);
                }
                num++;
            }
            return primes;
        }
        private static boolean isPrime(int num) {
            if (num <= 1) return false;
            if (num == 2) return true;
            if (num % 2 == 0) return false;
            for (int i = 3; i <= Math.sqrt(num); i += 2) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
    }


    public static String translateText(Map<String, String> dictionary, String text) {
        String[] words = text.split(" ");
        StringBuilder translatedText = new StringBuilder();
        for (String word : words) {
            String translatedWord = dictionary.getOrDefault(word, "?");
            translatedText.append(translatedWord).append(" ");
        }
        return translatedText.toString().trim();
    }
    public enum Weekday {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
    // lambda gets weekday returns weekday+ n days after use @CsvSource
    public static Weekday getWeekday(Weekday weekday, int n) {
        return Weekday.values()[weekday.ordinal() + n % 7];
    }

    public static int summer(int a, int b) {
        int sum = 0;
        for (int i = a; i <= b; i++) {
            sum += i;
        }
        return sum;
    }
    public static int shortSummer(int a, int b){
        return (b - a + 1) * (a + b) / 2;
    }

}