/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

// Commented out unused imports - Dak
//import java.util.ArrayList;

public class Nim
{
  private String type;
  private int[][] board;
  private int turn = 0;
  
  public Nim(int[][] board, String type) {
    this.board = board;
    this.type = type;
  }
  

  public String getType()
  {
    return type;
  }
  
  public int[][] getBoard() {
    return board;
  }
  
  public String getTurn() {
      // According to convention, return-condition if
      // should not have an else statement

      if (this.type.equals("cpu")) {
        if (turn == 0) {
          return "Human";
        }

        return "CPU";
      }
      
      // else excluded due to return condition
      if (turn == 0) {
          return ("Player 1");
      }
      
      return ("Player 2");
  }
  
  private void processMovement(int x, int y) {
    for (int i = x; i > -1; i--) { // Removing from position picked, going up.
      board[i][y] = 0;
    } 
    if (turn == 0) {
      turn += 1;
      } else {
      turn -= 1;
      }
    
    Project2.game1.getContentPane().removeAll(); //Redrawing the buttons.
    Project2.game1.buttonList.clear();
    Project2.game1.setButtonArray();
    Project2.game1.revalidate();
    Project2.game1.repaint();
  }
  
  private void processCPU() {
    board = NimBot.cpuMove(board);
    if (turn == 0) {
        turn += 1;
    } else {
        turn -= 1;
    }
    
    Project2.game1.getContentPane().removeAll(); //Redrawing the buttons.
    Project2.game1.buttonList.clear();
    Project2.game1.setButtonArray();
    Project2.game1.revalidate();
    Project2.game1.repaint();
  }
  
  public boolean hasWon() {
    int count = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 1) {
          count++;
        }
      }
    }
    return count == 0;
  }
  
  // now sure how this is a function unique to PVP.
  // Separating out the move code from the PVP code
  public void pvp(int x, int y) {
    this.type = "pvp";
    this.processMovement(x, y);

    // check if someone has won
    // Consider splitting this into a separate method
    if (this.hasWon()) {
        // if so, use a switch statement to make sure
        switch(turn) {
            case 0:
                // code if player 1 wins
                System.out.println("Algorithm reported player 1 won");
                break;
            case 1:
                // code if player 2 wins
                System.out.println("Algorithm reported player 2 won");
                break;
        }
    }
  }
  
  public void cpu(int moveX, int moveY) {
    this.type = "cpu";
    this.processMovement(moveX, moveY);
    if (this.hasWon())
        System.out.println("Computer won");
    this.processCPU();
    if (this.hasWon()) {
        System.out.println("Human won");
    }
  }
}
