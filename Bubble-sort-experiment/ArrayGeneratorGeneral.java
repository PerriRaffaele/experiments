import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ArrayGeneratorGeneral {

    public static void main(String[] args) {
        int dimension = 10000;

        BubbleSortPassPerItem bubbleSortPassPerItem = new BubbleSortPassPerItem();
        BubbleSortUntilNoChange bubbleSortUntilNoChange = new BubbleSortUntilNoChange();
        BubbleSortWhileNeeded bubbleSortWhileNeeded = new BubbleSortWhileNeeded();

        String[] typeOfTheArray = { "random", "sorted", "partSorted", "reverse", "duplicates", "noDuplicates", "equal" };

        // FOR INT
        for (String type : typeOfTheArray) {
            for (int i = 0; i < 150; i++) {
                // int
                int[] intArray = (int[]) arrayGeneratorGeneral("int", dimension, type);
                // Convert int[] to Integer[]
                Integer[] integerArray = Arrays.stream(intArray).boxed().toArray(Integer[]::new);
                Integer[] integerArrayCopy = Arrays.copyOf(integerArray, dimension);
                Integer[] integerArrayCopy2 = Arrays.copyOf(integerArray, dimension);

                analyzeSortingPerformance(bubbleSortPassPerItem, integerArray, "PassPerItem", "int", type, i);
                analyzeSortingPerformance(bubbleSortUntilNoChange, integerArrayCopy, "UntilNoChange", "int", type, i);
                analyzeSortingPerformance(bubbleSortWhileNeeded, integerArrayCopy2, "WhileNeeded", "int", type, i);
            }

        }

        // FOR INTEGER
        for (String type : typeOfTheArray) {

            for (int i = 0; i < 150; i++) {
                // Integer
                Integer[] integerArray = (Integer[]) arrayGeneratorGeneral("Integer", dimension, type);
                Integer[] integerArrayCopy = Arrays.copyOf(integerArray, dimension);
                Integer[] integerArrayCopy2 = Arrays.copyOf(integerArray, dimension);

                analyzeSortingPerformance(bubbleSortPassPerItem, integerArray, "PassPerItem", "Integer", type, i);
                analyzeSortingPerformance(bubbleSortUntilNoChange, integerArrayCopy, "UntilNoChange", "Integer", type,
                        i);
                analyzeSortingPerformance(bubbleSortWhileNeeded, integerArrayCopy2, "WhileNeeded", "Integer", type, i);
            }
        }

        // FOR STRING
        for (String type : typeOfTheArray) {

            for (int i = 0; i < 150; i++) {
                // String
                String[] stringArray = (String[]) arrayGeneratorGeneral("String", dimension, type);
                String[] stringArrayCopy = Arrays.copyOf(stringArray, dimension);
                String[] stringArrayCopy2 = Arrays.copyOf(stringArray, dimension);

                analyzeSortingPerformance(bubbleSortPassPerItem, stringArray, "PassPerItem", "String", type, i);
                analyzeSortingPerformance(bubbleSortUntilNoChange, stringArrayCopy, "UntilNoChange", "String", type, i);
                analyzeSortingPerformance(bubbleSortWhileNeeded, stringArrayCopy2, "WhileNeeded", "String", type, i);
            }
        }

        // FOR DOUBLE
        for (String type : typeOfTheArray) {

            for (int i = 0; i < 150; i++) {
                // Double
                double[] doubleArray = (double[]) arrayGeneratorGeneral("double", dimension, type);
                // Convert double[] to Double[]
                Double[] doubleArray2 = Arrays.stream(doubleArray).boxed().toArray(Double[]::new);
                Double[] doubleArrayCopy = Arrays.copyOf(doubleArray2, dimension);
                Double[] doubleArrayCopy2 = Arrays.copyOf(doubleArray2, dimension);

                analyzeSortingPerformance(bubbleSortPassPerItem, doubleArray2, "PassPerItem", "Double", type, i);
                analyzeSortingPerformance(bubbleSortUntilNoChange, doubleArrayCopy, "UntilNoChange", "Double", type, i);
                analyzeSortingPerformance(bubbleSortWhileNeeded, doubleArrayCopy2, "WhileNeeded", "Double", type, i);
            }
        }

        // FOR CHAR
        for (String type : typeOfTheArray) {

            for (int i = 0; i < 150; i++) {
                // Character
                char[] charArray = (char[]) arrayGeneratorGeneral("char", dimension, type);
                // Convert char[] to Character[]
                Character[] charArray2 = new Character[charArray.length];
                for (int j = 0; j < charArray.length; j++) {
                    charArray2[j] = charArray[j];
                }
                Character[] charArrayCopy = Arrays.copyOf(charArray2, dimension);
                Character[] charArrayCopy2 = Arrays.copyOf(charArray2, dimension);
                analyzeSortingPerformance(bubbleSortPassPerItem, charArray2, "PassPerItem", "Character", type, i);
                analyzeSortingPerformance(bubbleSortUntilNoChange, charArrayCopy, "UntilNoChange", "Character", type,
                        i);
                analyzeSortingPerformance(bubbleSortWhileNeeded, charArrayCopy2, "WhileNeeded", "Character", type, i);
            }
        }

        // FOR BYTE
        for (String type : typeOfTheArray) {

            for (int i = 0; i < 150; i++) {
                // Byte
                byte[] byteArray = (byte[]) arrayGeneratorGeneral("byte", dimension, type);
                // Convert byte[] to Byte[]
                Byte[] byteArray2 = new Byte[byteArray.length];
                for (int j = 0; j < byteArray.length; j++) {
                    byteArray2[j] = byteArray[j];
                }
                Byte[] byteArrayCopy = Arrays.copyOf(byteArray2, dimension);
                Byte[] byteArrayCopy2 = Arrays.copyOf(byteArray2, dimension);

                analyzeSortingPerformance(bubbleSortPassPerItem, byteArray2, "PassPerItem", "Byte", type, i);
                analyzeSortingPerformance(bubbleSortUntilNoChange, byteArrayCopy, "UntilNoChange", "Byte", type, i);
                analyzeSortingPerformance(bubbleSortWhileNeeded, byteArrayCopy2, "WhileNeeded", "Byte", type, i);
            }
        }
    }

    public static <T extends Comparable<T>> void analyzeSortingPerformance(Sorter<T> sorter, T[] array, String funcName,
            String type, String typeOfTheArray, int counter) {
        long startTime = System.nanoTime();
        sorter.sort(array);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        if (counter >= 25) {
            writeResultToCSV(funcName, type, typeOfTheArray, startTime, endTime, executionTime);
        }
    }

    public static void writeResultToCSV(String funcName, String type, String typeOfTheArray, long startTime,
            long endTime, long executionTime) {
        String csvFilename = "results1000.csv";
        try (FileWriter writer = new FileWriter(csvFilename, true)) {
            // check if file is empty
            if (new File(csvFilename).length() == 0) {
                writer.append("ALGORITHM,ELEMENT ARRAY TYPE,ARRAY ORDER,START TIME,END TIME,ELAPSED TIME\n");
            }
            writer.append(funcName)
                    .append(",")
                    .append(type)
                    .append(",")
                    .append(typeOfTheArray)
                    .append(",")
                    .append(String.valueOf(startTime))
                    .append(",")
                    .append(String.valueOf(endTime))
                    .append(",")
                    .append(String.valueOf(executionTime))
                    .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object arrayGeneratorGeneral(String type, int dimension, String typeOfTheArray) {
        int choice = 0;
        switch (typeOfTheArray) {
            case "random":
                choice = 1;
                break;
            case "sorted":
                choice = 2;
                break;
            case "partSorted":
                choice = 3;
                break;
            case "reverse":
                choice = 4;
                break;
            case "duplicates":
                choice = 5;
                break;
            case "noDuplicates":
                choice = 6;
                break;
            case "equal":
                choice = 7;
                break;
        }

        Object result = null;

        switch (type) {
            case "int":
                result = IntArrayGenerator.intArrayGenerator(dimension, choice);
                break;
            case "String":
                result = StringArrayGenerator.stringArrayGenerator(dimension, choice);
                break;
            case "double":
                result = DoubleArrayGenerator.doubleArrayGenerator(dimension, choice);
                break;
            case "char":
                result = CharArrayGenerator.charArrayGenerator(dimension, choice);
                break;
            case "Integer":
                result = IntegerArrayGenerator.intArrayGenerator(dimension, choice);
                break;
            case "byte":
                result = ByteArrayGenerator.byteArrayGenerator(dimension, choice);
                break;
        }

        return result;
    }
}
