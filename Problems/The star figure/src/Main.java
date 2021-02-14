import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        char[][] arr = new char[size][size];
        int row;
        int col;
        int mid = size / 2;
        for (row = 0; row < size; row++) {
            for (col = 0; col < size; col++) {
                if (row == col || col == mid || row == mid) {
                    arr[row][col] = '*';
                } else {
                    arr[row][col] = '.';
                }
                arr[row][size - row - 1] = '*';
                System.out.print(arr[row][col] + " ");
            }
            System.out.println();
        }
    }
}
