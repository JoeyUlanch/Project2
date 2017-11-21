/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dakota
 * This is the file that contains the "ai" for the
 * nim CPU. It is a static class that can be fed
 * 2-D nim boards into the method cpuMove
 * 
 * usage: NimBot::cpuMove(nimBoard)
 *  makes a move for the computer
 *  then, it is up to the main program to
 *  pass turn and determine winners, as well as
 *  reassign the new board to nimBoard
 */
public class NimBot {
    
    public static void printNim(int [][] nimBoard) {
        for (int [] nimRow : nimBoard) {
            for (int nimChar : nimRow) {
                System.out.print(nimChar);
            }
            System.out.println();
        }
    }
    
    public static int[][] rotateArr(int [][] nimBoard) {
        int [][] newBoard = new int[nimBoard[0].length][nimBoard.length];
        
        for (int i = 0; i < nimBoard[0].length; i++) {
            for (int j = 0; j < nimBoard.length; j++) {
                newBoard[i][j] = nimBoard[j][i];
            }
        }
        
        return newBoard;
    }
    
    public static int[][] cpuMove(int [][] nimBoard) {
                
        System.out.println("Before");
        int [][] origBoard = nimBoard;
        nimBoard = rotateArr(nimBoard);
        printNim(nimBoard);
        
        
        
        int [] simpleNim = new int[nimBoard[0].length];
        int [] binaryBoard;
        
        for (int i = 0; i < nimBoard.length; i++) {
            simpleNim[i] = rowSum(nimBoard[i]);
        }
        
        binaryBoard = new int[3];
        
        for (int i = 0; i < nimBoard.length; i++) {
            int [] binaryRow = convertToBinaryArr(simpleNim[i]);
            binaryBoard[0] += binaryRow[0];
            binaryBoard[1] += binaryRow[1];
            binaryBoard[2] += binaryRow[2];
        }
        
        // if there is only one row with more than one,
        // take all but one from that row
        if (binaryBoard[0] + binaryBoard[1] == 1) {
            for (int i = 0; i < simpleNim.length; i++) {
                if (simpleNim[i] > 1) {
                    System.out.println("take processed for condition: one good move");
                    origBoard = takeFrom(simpleNim[maxRow(simpleNim)] - 1, origBoard, i);
                }
            }
            System.out.println("Take should be processed");
        } else {
            if ((binaryBoard[0] & 1) == 1) {
                System.out.println("take processed: parity of 4");
                origBoard = take(simpleNim, 4, origBoard);
            } else if ((binaryBoard[1] & 1) == 1) {
                System.out.println("take processed: parity of 2");
                origBoard = take(simpleNim, 2, origBoard);
            } else {
                System.out.println("take processed: parity of 1 or no parity");
                origBoard = take(simpleNim, 1, origBoard);
            }
        }
        
        for (int i = 0; i < simpleNim.length; i++) {
            nimBoard[i] = createRow(simpleNim[i], nimBoard[i].length);
        }
        System.out.println("After:");
        printNim(nimBoard);
        nimBoard = rotateArr(nimBoard);
        printNim(nimBoard);
        
        return origBoard;
    }
    
    public static int[] createRow(int arg, int length) {
        int[] retval = new int[length];
        for (int i = 0; i < arg; i++) {
            retval[i] = 1;
        }
        
        return retval;
    }
    
    public static int [][] take(int [] simpleBoard, int howMany, int[][] originalBoard) {
        boolean set = false;
        int takeFrom = 0;
        System.out.println((int)(Math.random()*100) + "Take function activated (" + howMany + ")" );
        for (int i = 0; i < simpleBoard.length; i++) {
            System.out.println("Row has " + simpleBoard[i] + " units");
            if(simpleBoard[i] >= howMany && !set) {
                System.out.println("Take set");
                takeFrom = i;
                set = true;
            }
        }
        
        int iterator = 0;
        while (howMany > 0) {
            if (originalBoard[iterator][takeFrom] == 1) {
                System.out.println("Took from col" + takeFrom);
                originalBoard[iterator][takeFrom] = 0;
                howMany--;
            }
            iterator++;
        }
        //if (set == false)
        //    originalBoard = take(simpleBoard, howMany-1, originalBoard);
        return originalBoard;
    }
    
    public static int [][] takeFrom(int howMany, int[][] originalBoard, int from) {
        int iterator = 0;
        while (howMany > 0) {
            if (originalBoard[iterator][from] == 1) {
                originalBoard[iterator][from] = 0;
                howMany--;
            }
            iterator++;
        }
        return originalBoard;
    }
    
    public static int[] convertToBinaryArr(int row) {
        int [] ret = new int[3];
        ret[0] = row / 4;
        row -= 4 * (row / 4);
        ret[1] = row / 2;
        row -= 2 * (row / 2);
        ret[2] = row;
        
        return ret;
    }
    
// INSERT LOST HERE    
    private static int maxRow(int [] nimRows) {
        int maxIndex = 0;
        int max = 0;
        for (int i = 0; i < nimRows.length; i++) {
            if (max < nimRows[i]) {
                max = nimRows[i];
                maxIndex = i;
            }
        }
        
        return maxIndex;
    }
    
    // calculates the sum of all values in a row
    private static int rowSum(int [] row) {
        int sum = 0;

        for (int i = 0; i < row.length; i++) {
            sum += row[i];
        }
        
        return sum;
    }
    
 
}
