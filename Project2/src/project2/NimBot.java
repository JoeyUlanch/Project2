/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;
//import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Dakota
 */
public class NimBot {
    public static int[][] cpuMove(int [][] nimBoard) {
        // number of columns with >0 matchsticks
        int flagged = nimBoard.length - emptyRows(nimBoard);
        
        // if there is one lonely (contains 1) row
        if (flagged - lonelyRows(nimBoard) == 1) {
            // if the number of lonely rows is odd, ta
            if (flagged % 2 == 1) {
                // take all but one item from the column
            } else {
                // take all of the greatest column
            }
        } else if ((flagged - getParity(nimBoard)) % 2 == 0) {
            // take from the greatest column to equal the value of the second
            // greatest column
        } else {
            if (flagged == 1) {
                // subtract all but one from the column
            } else {
                // subtract all from the column
            }
        }
        
        return nimBoard;
    }
    
    // returns an int for the number of pairs that each item has
    // change this so that it doesn't require a specific row
    public static int getParity(int [][] nimBoard) {
        int parityCount = 0;
        
        for (int [] row : nimBoard) {
            int rowTotal = rowSum(row);

            for (int i = 1; i < nimBoard.length; i++) {
                int thisParity = 0;
                
                for (int[] currentRow : nimBoard) {
                    if (rowSum(currentRow) == rowTotal) {
                        thisParity++;
                    }
                }
                
                // strip down odd parity counts
                thisParity = thisParity / 2;
                thisParity = thisParity * 2;
                
                parityCount += thisParity;
            }
        }
        
        return parityCount;
    }
    
    // calculates the sum of all values in a row
    public static int rowSum(int [] row) {
        int sum = 0;

        for (int i = 0; i < row.length; i++) {
            sum += row[i];
        }
        
        return sum;
    }
    
    private static int emptyRows(int[][] nimBoard) {
        int count = 0;
        
        for (int[] nimRow : nimBoard) {
            if (rowSum(nimRow) == 0) {
                count++;
            }
        }
        
        return count;
    }
    
    private static int lonelyRows(int[][] nimBoard) {
        int count = 0;
        
        for (int[] nimRow : nimBoard) {
            if (rowSum(nimRow) == 1) {
                count++;
            }
        }
        
        return count;
    }
    
    private static int[][] sortNim(int[][] nimBoard) {
        // using a quicksort algorithm to sort the board
        int [] rowLengths = new int[nimBoard.length];
        
        // iterate through the board and assign lengths to each row
        for (int i = 0; i < nimBoard.length; i++) {
            rowLengths[i] = nimBoard[i].length;
        }
        
        boolean swapped = false;
        int steps = rowLengths.length - 1;

        for (int i = 0; i < steps; i++) {
            int j = i;
            while (rowLengths[j] < rowLengths[j + 1]) {
                // perform a swap
                int temp = rowLengths[j];
                rowLengths[j] = rowLengths[j + 1];
                rowLengths[j + 1] = temp;

                int [] tempArr = nimBoard[j];
                nimBoard[j] = nimBoard[j + 1];
                nimBoard[j + 1] = tempArr;
                swapped = true;
                j--;
            }
        }
        
        return nimBoard;
    }
    
    // calculates how many identical pairs there are
    private static int parityColumns(int[][] nimBoard) {
        int parity = 0;
        
        nimBoard = sortNim(nimBoard);
        
        for (int i = 0; i < nimBoard.length; i++) {
            if (Arrays.equals(nimBoard[i], nimBoard[i + 1])) {
                parity++;
                i++;
            }
        }
        
        return parity;
    }
    
    public static int totalEmptyRows(int[][] nimBoard) {
        int empty = nimBoard.length;
        for (int[] row : nimBoard) {
            if (rowSum(row) > 0) {
                empty--;
            }
        }
        
        return empty;
    }
}
