/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author prais
 */
public class Project2 {

  public static ArrayList<JButton> buttonList = new ArrayList();
  public static int[][] myArray = new int[6][6];
  public static Nim nim1;
  public static GameScreen game1;
  public Project2() {}
  public static String type;
  
  public static void main(String[] args)
  {
    Window1 w1 = new Window1();
    w1.show();
    //game1 = new GameScreen();
    //game1.show();
    //newGame(type);
    
    


    /*for (int i = 0; i < myArray.length; i++) {
      for (int j = 0; j < myArray[i].length; j++) {
        System.out.print(myArray[i][j]);
      }
      System.out.println();
    }*/
  }
  
  //testing
  public static void newGame(String type) {
    setRandArray();
    nim1 = new Nim(myArray, type);
    game1 = new GameScreen();
    game1.show();
    game1.setButtonArray();
  }
  


  public static void setRandArray()
  {
    Random rand = new Random();
    for (int i = 0; i < 6; i++) {
      int n = rand.nextInt(6) + 1;
      for (int b = 5; b > -1; b--) {
        if (n < 0) {
          myArray[b][i] = 0;
        } else {
          myArray[b][i] = 1;
        }
        n--;
      }
    }
  }
}
