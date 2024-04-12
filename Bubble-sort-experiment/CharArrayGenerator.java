import java.util.Random;

public class CharArrayGenerator {

    public static void main(String[] args) {
      
    }
    public static char[] charArrayGenerator(int dimension, int natureChoice){
        switch (natureChoice) {
            case 1:
                return generateRandomCharArray(dimension);
            case 2:
                return generateSortedCharArray(dimension);
            case 3:
                return generatePartiallySortedCharArray(dimension);
            case 4:
                return generateReverseSortedCharArray(dimension);
            case 5:
                return generateRandomCharArrayWithDuplicates(dimension);
            case 6:
                return generateRandomCharArrayWithoutDuplicates(dimension);
            case 7:
                return generateEqualCharArray(dimension);
        }
        return null;
    }
    private static char[] generateRandomCharArray(int length) {
        char[] array = new char[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = generateRandomChar(random);
        }
        return array;
    }

    private static char[] generateSortedCharArray(int length) {
        char[] array = new char[length];
        for (int i = 0; i < length; i++) {
            array[i] = (char) ('a' + i); // Assuming you want to generate characters in sequence from 'a' onwards
        }
        return array;
    }

    private static char[] generatePartiallySortedCharArray(int length) {
        char[] array = new char[length];
        for (int i = 0; i < length; i++) {
            array[i] = (char) ('a' + i);
        }

        // Shuffle a portion of the array to make it partially sorted
        Random random = new Random();
        for (int i = 0; i < length / 3; i++) {
            int index1 = random.nextInt(length);
            int index2 = random.nextInt(length);
            char temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }

        return array;
    }

    private static char[] generateReverseSortedCharArray(int length) {
        char[] array = new char[length];
        for (int i = 0; i < length; i++) {
            array[i] = (char) ('a' + length - i - 1); // Generating characters in reverse order
        }
        return array;
    }

    private static char[] generateRandomCharArrayWithDuplicates(int length) {
        char[] array = new char[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = generateRandomChar(random);
        }
        return array;
    }

    private static char[] generateRandomCharArrayWithoutDuplicates(int length) {
        char[] array = new char[length];
        Random random = new Random();
        char[] characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        for (int i = 0; i < length; i++) {
            array[i] = characters[random.nextInt(characters.length)];
        }

        return array;
    }

    private static char[] generateEqualCharArray(int length) {
        return generateEqualCharArray(length, 'a');
    }

    private static char[] generateEqualCharArray(int length, char value) {
        char[] array = new char[length];
        for (int i = 0; i < length; i++) {
            array[i] = value;
        }
        return array;
    }

    private static char generateRandomChar(Random random) {
        char[] characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int randomIndex = random.nextInt(characters.length);
        return characters[randomIndex];
    }
}
