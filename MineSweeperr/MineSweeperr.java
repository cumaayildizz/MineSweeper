package MineSweeperr;

import java.util.Random;
import java.util.Scanner;

public class MineSweeperr {

    int row;
    int column;
    int mapArea ;
    int[][] gameMap;
    int[][] userMap;
    boolean isGame = true ;

    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    MineSweeperr(int row , int column ) {
        this.row = row;
        this.column = column;
        this.gameMap = new int[row][column];
        this.userMap = new int[row][column];
        this.mapArea = (row * column) ;
    }

    public void gameRun(){
        int rowG=0, col=0 , win = 0 ;

        prepareGame();

        printMap(this.gameMap);

        System.out.println();
        System.out.println("Game start");
        System.out.println();

        prepareGameUserMap();

        do {
            printMap(this.userMap);
               do {
                   System.out.print(" Row Number    : ");
                   rowG = scanner.nextInt();
                   System.out.print(" Column Number : ");
                   col = scanner.nextInt();
                   System.out.println("Please enter a number.");

                   System.out.println();
                   if ((rowG >= this.row || rowG < 0) || ( col >= this.column || col < 0)){ //If any of the entered values are not satisfied, the value does not match the coordinates.
                       System.out.println("The coordinates you entered are not within the playing field.\n" +
                               "Please enter row and column again");
                   }

               }while (!((this.row > rowG && rowG >= 0) && (this.column > col && col >= 0))); //The entered coordinates must be less  than the current coordinates and greater than or equal to zero.
            /*
             * If there is no bomb in the entered frame, increase the win value by 1.
             * If all the non-bomb squares (map size - (map size/4)) are opened, that is, win=(map size - (map size/4)) the player has been successful.
             */
            if (this.userMap[rowG][col] == 9 ){ //
                win++; //The "win" value serves as a counter here. Each new frame that opens enters the loop with the value 9. Every time we see this value of 9 percent, we increment the counter by 1.
            }          //In the ongoing loop, the value 9 enters the "checkMine" loop and takes value according to the number of mines around.
                       //So, if we enter the same frame again, our counter will not work because its value will be different from 9!!
            if (this.gameMap[rowG][col]!= -1 ){ //If the user has not selected the mine, activate this blog
                checkMine(rowG , col);
                  //counter(win) map size - the game is won when the number of mines is reached
                if ( (win == (this.mapArea - (this.mapArea/4)))) {
                    System.out.println("CONGRATULATIONS. YOU WIN <3 <3 <3 ");
                    showMap();
                    break;
                }

            }else {  //If the user has selected the mine, game over
                isGame = false ;
                System.out.println( "GAME OVER!!!" );
                showMap();

            }
        }while (isGame);
    }

    public void prepareGame(){
        int randomRow;
        int randomColumn ;
        int count = 0 ;

           while (count !=(this.mapArea/4)){  //Adding bombs up to 1/4 of the map size
               randomRow = random.nextInt(this.row);
               randomColumn = random.nextInt(this.column);
               if (this.gameMap[randomRow][randomColumn] != -1){
                   this.gameMap[randomRow][randomColumn] = -1;
                   count++;
               }
           }

           for( int i = 0 ; i < gameMap.length ; i++ ){ //I wrote 9 on the empty spaces on my game map. Why 9 and not 0? Because if we print 0, we will not be able to print 0 again when we check for mines in the future.
               for( int j = 0 ; j < gameMap[0].length ; j++){
                   if (this.gameMap[i][j] != 9 && this.gameMap[i][j] != -1){  //Skip mined areas and areas previously populated with 9
                       this.gameMap[i][j] = 9;
                       count++;
                   }
               }
           }

    }

    public void prepareGameUserMap(){
        int randomRow;
        int randomColumn ;
        int count = 0 ;

        for( int i = 0 ; i < userMap.length ; i++ ){ //I wrote 9 on the empty spaces on my game map. Why 9 and not 0? Because if we print 0, we will not be able to print 0 again when we check for mines in the future.
            for( int j = 0 ; j < userMap[0].length ; j++){
                if (this.userMap[i][j] != 9 ){  //Skip mined areas and areas previously populated with 9
                    this.userMap[i][j] = 9;
                    count++;
                }
            }
        }

    }

    public void checkMine(int rowCheck , int columnCheck ){  //If there is no bomb in the entered coordinates, check the surrounding squares. If there is a bomb add +1
        if(this.userMap[rowCheck][columnCheck] == 9 ){
            this.userMap[rowCheck][columnCheck] = 0;   //In order to increment the counter properly, I set 9 equal to 0.
            //Mine check in the squares adjacent to the entered coordinates
            if( (columnCheck < this.column - 1 ) && (this.gameMap[rowCheck][columnCheck +1] == -1)){
                this.userMap[rowCheck][columnCheck]++ ; //right side square mine check
            }
            if((rowCheck < this.row - 1 ) && (this.gameMap[rowCheck + 1][columnCheck] == -1)){
                this.userMap[rowCheck][columnCheck]++ ; //bottom square mine check
            }
            if((rowCheck < this.row - 1 && columnCheck > 0) && (this.gameMap[rowCheck + 1][columnCheck -1] == -1)){
                this.userMap[rowCheck][columnCheck]++ ; //bottom left square mine check
            }
            if((rowCheck <this.row -1 && columnCheck < this.column - 1) && (this.gameMap[rowCheck + 1][columnCheck +1] == -1)){
                this.userMap[rowCheck][columnCheck]++ ;  //bottom right square check
            }
            if((rowCheck > 0 ) && (this.gameMap[rowCheck - 1 ][columnCheck] == -1 )){
                this.userMap[rowCheck][columnCheck]++ ; //upper square mine check
            }
            if((rowCheck > 0 && columnCheck > 0) && (this.gameMap[rowCheck - 1][columnCheck - 1] == -1 )){
                this.userMap[rowCheck][columnCheck]++ ; //upper left square mine check
            }
            if((rowCheck > 0 && columnCheck < this.column - 1 ) && (this.gameMap[rowCheck - 1][columnCheck + 1] == -1 )){
                this.userMap[rowCheck][columnCheck]++ ; //upper right square mine check
            }
            if((columnCheck > 0) && (this.gameMap[rowCheck][columnCheck - 1 ] == -1)){
                this.userMap[rowCheck][columnCheck]++ ; //left side square mine check
            }
        }
    }
    public void printMap(int[][]array){
        System.out.print("    ");
        gameMapHorizontalDesign(this.row);
        System.out.println();
        for ( int i = 0 ; i < array.length ; i++ ){
            gameMapVerticalDesign(i);
            for ( int j = 0 ; j < array[0].length ; j++ ){
                if (array[i][j] == 9 ){  // Suppress the "-" symbol at a value of 9
                    System.out.print("[ - ]");
                }
                else if(array[i][j] == -1 ){ // and the "*" symbol at a value of -1 (mined area).
                    System.out.print(  "[ * ]" );
                }
                else {  //  If one of these two values is not present, print the value from the isCheck method.
                    System.out.print( "[ " + array[i][j] + " ]");
                }
            }
            System.out.println();
        }
    }

    public void showMap(){
        System.out.print("Press any number key to see mine locations : ");
        int wait = scanner.nextInt(); // Added to make the player wait
        System.out.println();
        printMap(this.gameMap);
    }

    public void gameMapVerticalDesign(int i){ //vertical map design method
        if ( i < 10){
            System.out.print((i) + " | ");
        }
        else {
            System.out.print((i) + "| ");
        }
    }

    public void gameMapHorizontalDesign(int i) {  //horizontal map design metod
        for ( i = 0 ; i < this.gameMap[0].length ; i++ ){
            if( i < 10 ){
                System.out.print( "| " + (i) + " |");
            }
            else{
                System.out.print( "| " + (i) + "|");
            }
        }
        System.out.println();
        System.out.print("    ");
        for ( i = 0 ; i < this.gameMap[0].length ; i++ ){
            System.out.print("=====");
        }
    }



}


