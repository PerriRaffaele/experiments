import java.util.Random;

public class IntArrayGenerator{

    public static void main(String[] args) {
      
    }

    public static int[] intArrayGenerator(int dimension, int natureChoice){
        switch (natureChoice) {
            case 1:
                return generateRandomIntArray(dimension);
            case 2:
                return generateSortedIntArray(dimension);
            case 3:
                return generatePartiallySortedIntArray(dimension);
            case 4:
                return generateReverseSortedIntArray(dimension);
            case 5:
                return generateRandomIntArrayWithDuplicates(dimension);
            case 6:
                return generateRandomIntArrayWithoutDuplicates(dimension);
            case 7:
                return generateEqualIntArray(dimension);
        }
        return null;
    }


    private static int[] generateRandomIntArray(int length) {
        Random random = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(100); // Adjust the range as needed
        }
        return array;
    }

    // Generate a sorted integer array
    private static int[] generateSortedIntArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = i + 1;
        }
        return array;
    }

    // Generate a reverse sorted integer array
    private static int[] generateReverseSortedIntArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = length - i;
        }
        return array;
    }

    private static int[] generateEqualIntArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = 1;
        }
        return array;
    }

    private static int[] generatePartiallySortedIntArray(int length) {
        int[] array = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }

        // Shuffle a portion of the array to make it partially sorted
        for (int i = 0; i < length / 3; i++) {
            int index1 = random.nextInt(length);
            int index2 = random.nextInt(length);
            int temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }

        return array;
    }

    private static int[] generateRandomIntArrayWithDuplicates(int length) {
        Random random = new Random();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(10); // Adjust the range as needed
        }
        return array;
    }

    private static int[] generateRandomIntArrayWithoutDuplicates(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = i;
        }
        shuffleArray(array);
        return array;
    }

    // Shuffle the array to make it random
    private static void shuffleArray(int[] array) {
        int index, temp;
        for (int i = array.length - 1; i > 0; i--) {
            index = (int) (Math.random() * (i + 1));
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
