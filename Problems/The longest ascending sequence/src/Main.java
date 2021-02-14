
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int[] numbers = new int[size];
        int counter = 1;
        int max = 1;
        for (int i = 0; i < size; i++) {
            numbers[i] = scan.nextInt();
        }
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] < numbers[i + 1]) {
                counter++;
                max = Math.max(counter, max);

            } else if (numbers[i] >= numbers[i + 1]) {
                max = Math.max(counter, max);
                counter = 1;
            }
//            System.out.println(numbers[i] + "-" + numbers[i + 1] + " "+counter+" "+max);
        }

        System.out.println(max);


    }
}