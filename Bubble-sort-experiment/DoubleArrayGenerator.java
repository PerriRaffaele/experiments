
import java.util.Random;

public class DoubleArrayGenerator {

    public static void main(String[] args) {
      
    }

    public static double[] doubleArrayGenerator(int dimension, int natureChoice){
        switch (natureChoice) {
            case 1:
                return generateRandomDoubleArray(dimension);
            case 2:
                return generateSortedDoubleArray(dimension);
            case 3:
                return generatePartiallySortedDoubleArray(dimension);
            case 4:
                return generateReverseSortedDoubleArray(dimension);
            case 5:
                return generateRandomDoubleArrayWithDuplicates(dimension);
            case 6:
                return generateRandomDoubleArrayWithoutDuplicates(dimension);
            case 7:
                return generateEqualDoubleArray(dimension);
        }
        return null;
    }

       

    private static double[] generateRandomDoubleArray(int length) {
        double[] array = new double[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = random.nextDouble(); // Generates a random double between 0.0 (inclusive) and 1.0 (exclusive)
        }
        return array;
    }

    private static double[] generateSortedDoubleArray(int length) {
        double[] array = new double[length];
        for (int i = 0; i < length; i++) {
            array[i] = (double) i; // Assuming you want to generate doubles in sequence
        }
        return array;
    }

    private static double[] generatePartiallySortedDoubleArray(int length) {
        double[] array = new double[length];
        for (int i = 0; i < length; i++) {
            array[i] = (double) i;
        }

        // Shuffle a portion of the array to make it partially sorted
        Random random = new Random();
        for (int i = 0; i < length / 3; i++) {
            int index1 = random.nextInt(length);
            int index2 = random.nextInt(length);
            double temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }

        return array;
    }

    private static double[] generateReverseSortedDoubleArray(int length) {
        double[] array = new double[length];
        for (int i = 0; i < length; i++) {
            array[i] = (double) (length - i - 1); // Generating doubles in reverse order
        }
        return array;
    }

    private static double[] generateRandomDoubleArrayWithDuplicates(int length) {
        double[] array = new double[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = random.nextDouble(); // Generates a random double between 0.0 (inclusive) and 1.0 (exclusive)
        }
        return array;
    }

    private static double[] generateRandomDoubleArrayWithoutDuplicates(int length) {
        double[] array = new double[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = random.nextDouble() * 10; // Adjust the range as needed
        }
        return array;
    }

    private static double[] generateEqualDoubleArray(int length) {
        return generateEqualDoubleArray(length, 1.0);
    }


    private static double[] generateEqualDoubleArray(int length, double value) {
        double[] array = new double[length];
        for (int i = 0; i < length; i++) {
            array[i] = value;
        }
        return array;
    }



}