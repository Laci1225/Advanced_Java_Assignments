package org.second_class;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {

    public enum Language {
        ENGLISH, HUNGARIAN, GERMAN
    }
    public enum Months {

        JANUARY("január", "Januar"), FEBRUARY("február", "Februar"), MARCH("március", "März"),
        APRIL("április", "April"), MAY("május", "Mai"), JUNE("június", "Juni"),
        JULY("július", "Juli"), AUGUST("augusztus", "August"), SEPTEMBER("szeptember", "September"),
        OCTOBER("október", "Oktober"), NOVEMBER("november", "November"), DECEMBER("december", "Dezember");
        private final String hungarianName;
        private final String germanName;
        Months(String hungarianName, String germanName) {
            this.hungarianName = hungarianName;
            this.germanName = germanName;
        }
        public String getHungarianName() {
            return hungarianName;
        }
        public String getEnglishName() {
            return toString().charAt(0) + toString().substring(1).toLowerCase();
        }
        public String getGermanName() {
            return germanName;
        }
        public String getName(Language language){
            try {
                return switch (language) {
                    case ENGLISH -> getEnglishName();
                    case HUNGARIAN -> getHungarianName();
                    case GERMAN -> getGermanName();
                };
            } catch (NullPointerException e) {
                throw new IllegalArgumentException("Unknown language: " + language);
            }
        }
    }

    public enum Weekday {
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
        public static Weekday getWeekday(int month, int day){
            Calendar calendar = new GregorianCalendar(new GregorianCalendar().get(Calendar.YEAR), month, day);
            return switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.MONDAY -> MONDAY;
                case Calendar.TUESDAY -> TUESDAY;
                case Calendar.WEDNESDAY -> WEDNESDAY;
                case Calendar.THURSDAY -> THURSDAY;
                case Calendar.FRIDAY -> FRIDAY;
                case Calendar.SATURDAY -> SATURDAY;
                case Calendar.SUNDAY -> SUNDAY;
                default -> throw new IllegalArgumentException("Invalid day of week");
            };
        }
        public static Weekday getWeekday2(int month, int day){
            return Weekday.values()[new GregorianCalendar(new GregorianCalendar().get(Calendar.YEAR), month, day).get(Calendar.DAY_OF_WEEK) - 1];
        }

        public String getHungarianName() {
            return hungarianName;
        }
        public String getEnglishName() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }

        public boolean isThisDay(int month, int day){
            return getWeekday(month, day) == this;
        }

        public boolean isEarlierThan(Weekday weekday1, Weekday weekday2){
            return weekday1.ordinal() < weekday2.ordinal();
        }

        public Weekday nextDay(){
            return nextDay(1);
        }
        public Weekday nextDay(int n){
            return Weekday.values()[(this.ordinal() + n) % Weekday.values().length];
        }

    }
    public static void say(String s){
        System.out.println(s);
    }
    private static void say2() {
        System.out.println("Hello");
    }
    public static void main(String[] args) {
        say("Hello");
        var month = Months.JANUARY;

        // asd = txt -> System.out.println(txt);
        // asd.accept("Hello"); now implement
        Consumer<String> asd = System.out::println;
        asd.accept("Hello");

        Runnable r2 = Main::say2;

        Runnable r = () -> System.out.println("Hello");
        r.run();
        // n -> 3 * n + 1; implement now
        Function<Integer, Integer> func = n -> 3 * n + 1;
        System.out.println(func.apply(5));

        int[][] jaggedArray = new int[3][];
        jaggedArray[0] = new int[2];
        jaggedArray[1] = new int[3];
        jaggedArray[2] = new int[4];

        //print jaggedArray nicely
        for (int i = 0; i < jaggedArray.length; i++) {
            for (int j = 0; j < jaggedArray[i].length; j++) {
                System.out.print(jaggedArray[i][j] + " ");
            }
            System.out.println();
        }

        /*JFrame frame = new JFrame("Enum tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 640);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        frame.getContentPane().add(panel);

        for (Weekday day: Weekday.values()) {
            // day need to be final to be used in lambda or effectively final
            JButton button = new JButton(day.toString());
            button.setSize(600,40);
            button.addActionListener(e -> {
                showMessageDialog(frame, "English name " + day.getEnglishName() + "\n"
                + "Hungarian Name" + day.getHungarianName() + "\n"
                        + "Is this day Sept 19: " + day.isThisDay(8, 19) + "\n"
                        + "Next day: " + day.nextDay() + "\n"
                        + "100 day later: " + day.nextDay(100) + "\n"
                        + "2 days before: " + day.nextDay(-2) + "\n"
                        + "Is earlier than Wednesday: " + day.isEarlierThan(day, Weekday.WEDNESDAY) + "\n");
            }); 
            panel.add(button);
        }
        frame.setVisible(true);*/
    }


}
