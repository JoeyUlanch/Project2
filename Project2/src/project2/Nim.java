/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.ArrayList;







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
  
  public void pvp(int x, int y) {
    int count = 0;
    for (int i = x; i > -1; i--) { // Removing from position picked going up
      board[i][y] = 0;
    } 
    
    
    Project2.game1.getContentPane().removeAll(); //Repainting the 2D Array
    Project2.game1.buttonList.clear();
    Project2.game1.setButtonArray();
    Project2.game1.revalidate();
    Project2.game1.repaint();

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == 1) {
          count++;
        }
      }
    }
    if ((turn == 0) && (count == 0)) {
        //Code if player 1 looses
    } 
    
      if ((turn != 1) || (count != 0)) {
          //Code if player 2 looses
      }
      

      if (turn == 0) {
      turn += 1;
      } else {
      turn -= 1;
      }
  }
  
  public void cpu(int moveX, int moveY) {
  
  }
}
