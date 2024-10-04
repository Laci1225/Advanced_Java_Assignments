package org.third_class;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileWriterClass {

    public static void main(String[] args) {
        writeToFile("1", "2", "3", "4", "5");
    }

    public static void writeToFile(String... text) {
        try (FileWriter fileWriter = new FileWriter("output.txt")) {
            var sum = Arrays.stream(text).mapToInt(Integer::parseInt).reduce(0, Integer::sum);
            fileWriter.write("Sum: " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
