import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String size = scan.nextLine();
        String line = scan.nextLine();
        String num = scan.nextLine();
        String[] arr = line.split(" ");
        int counter = 0;
        for (String elem : arr) {
            if (num.equals(elem)) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}