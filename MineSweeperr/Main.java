package MineSweeperr;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int row = 0;
        int column = 0;

        System.out.println();
        System.out.println( "**** WELCOME TO MINE SWEEPER ****");
        System.out.println();
        System.out.println("Please enter the map size");
        System.out.println("Please enter a number between 2-100");
        System.out.println();
        do {
            System.out.print(" Row Number    : ");
            row = scanner.nextInt();
            System.out.print(" Column Number : ");
            column = scanner.nextInt();
            if ((row >= 100 || row < 2) || (column >= 100 || column < 2)){
                System.out.println("INCORRECT NUMBER ENTRY");
                System.out.println("Please enter a number between 2-100");
            }
        }while (!((row < 100 && row >= 2) && (column < 100 && column >= 2)));

        MineSweeperr start = new MineSweeperr(row , column);
        start.gameRun();

    }
}
