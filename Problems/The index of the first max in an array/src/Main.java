
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int[] numbers = new int[size];
        int i;
        int max;
        int maxIdx = 0;
        for (i = 0; i < size; i++) {
            numbers[i] = scan.nextInt();
        }
        max = numbers[ 0 ];

        for (i = 0; i < numbers.length; i++) {

            if (numbers[i] > max) {
                maxIdx = i;
                max = numbers[i];
            }
        }
        System.out.println(maxIdx);
    }
}