/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

// Commented out unused imports - Dak

import java.util.*;

//import java.util.ArrayList;

public class Nim
{
  private String type;
  private int[][] board;
  private int turn = 0;
  private boolean gameStateLocked = false;
  private boolean gameHasStarted = false;
  
  public void setGameStateLocked(boolean state) {
      gameStateLocked = state;
  }
  
  public Nim(int[][] board, String type) {
    this.board = board;
    this.type = type;
  }
  

  public boolean gameHasStarted() {
      return gameHasStarted;
  }
  
  public void setGameStarted() {
      gameHasStarted = true;
  }
  public String getType()
  {
    return type;
  }
  
  public int[][] getBoard() {
    return board;
  }
  
  public void setTurn(int turn) {
      this.turn = turn;
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
  
  protected void processCPU() {

    
            board = NimBot.cpuMove(board);
            gameStateLocked = false;
      

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
    boolean count = true;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 1) {
          count = false;
        }
      }
    }
    return count;
  }
  
  // now sure how this is a function unique to PVP.
  // Separating out the move code from the PVP code
  public void pvp(int x, int y) {
    this.type = "pvp";
    this.processMovement(x, y);
  }
  
  public void cpu(int moveX, int moveY) {
    this.type = "cpu";
    this.processMovement(moveX, moveY);
    Timer timer = new Timer();

    if (!this.hasWon()) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Project2.nim1.processCPU();
            }
        }, 1000);    }
  }
  
  public boolean isLocked () {
      return gameStateLocked;
  }
}
