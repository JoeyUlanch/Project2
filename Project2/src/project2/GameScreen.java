/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.PrintStream;
import java.util.*;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;





/**
 *
 * @author prais
 */
public class GameScreen extends javax.swing.JFrame {

    
    public static ArrayList<JButton> buttonList;
    /**
     * Creates new form GameWindow
     */
    public GameScreen() {
        initComponents();
        buttonList = new ArrayList();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1178, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameScreen().setVisible(true);
            }
        });
    }
    
  private void jButton2ActionPerformed(ActionEvent evt, int x, int y) {
    if (!Project2.nim1.isLocked()) {
        if (Project2.nim1.getType().equals("pvp")) {
          // move as if it is a PVP game.
          Project2.nim1.pvp(x, y);
        } else {
          // move as if it is a computer game
          
          Project2.nim1.cpu(x, y);
        }
    }
  }
  
  
  private void jButton3ActionPerformed(ActionEvent evt) { 
    //New Game
    this.hide();
    Project2.w1.show();
  }
  
  private void jButton4ActionPerformed(ActionEvent evt) {
      //Exits program
      System.exit(0);
  }
     
  
  
  
  private void endGame() {
      Container endContainer = getContentPane();
      
      //Start of New Game Button
      JButton newButton = new JButton();
      endContainer.add(newButton);
      newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
          });
      newButton.setSize(100, 40);
      newButton.setLocation((this.getWidth() / 2) - 150, 200);
      newButton.setText("New Game");
      //End of New Game Button
      
      //Start of Exit Button
      JButton endButton = new JButton();
      endContainer.add(endButton);
      endButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
          });
      endButton.setSize(100, 40);
      endButton.setLocation((this.getWidth() / 2) + 50, 200);
      endButton.setText("Exit");
      //End Exit Button
  }
     
  public void setButtonArray() {
    int count = 0;
    int columnarCount = 0;
    Container buttonLayout = getContentPane();
    
    // Display the turn in a label above the board
    if (Project2.nim1.getType().equals("pvp")) {
        JLabel label = new JLabel();
        label.setSize(100, 30);
        // If someone has won, let the players know.
        if (Project2.nim1.hasWon()) {
            label.setText(Project2.nim1.getTurn() + " Wins!");
            endGame();
            // Consider adding an option for a new game here
        } else {
            // Inform players whose move it is
            label.setText(Project2.nim1.getTurn());
        }
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setLocation((this.getWidth() / 2) - 50, 50);
        buttonLayout.add(label);
    } else {
        JLabel label = new JLabel();
        label.setSize(100, 30);
        
        // If someone has won, let the players know.
        if (Project2.nim1.hasWon()) {
            label.setText(Project2.nim1.getTurn() + " Wins!");
            endGame();
            // Consider adding an option for a new game here
        } else {
            // Inform players whose move it is
            if (Math.random() > .5 && !Project2.nim1.gameHasStarted()) {
                
                Project2.nim1.setTurn(1);//
                label.setText(Project2.nim1.getTurn());
                Project2.nim1.setGameStateLocked(true);
                Timer timer = new Timer();

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Project2.nim1.processCPU();
                    }
                }, 1000);
            }
            Project2.nim1.setGameStarted();
            label.setText(Project2.nim1.getTurn());
        }
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setLocation((this.getWidth() / 2) - 50, 50);
        buttonLayout.add(label);
    }
    
    //buttonLayout.setLayout(new GridLayout(7, 6));
    
    /* It would seem that the following
     * for() loops are responsible for
     * generating all the buttons for the
     * nim game.
     */
    
    // Creates 36 new JButtons
    for (int i = 0; i < 100; i++) {
      JButton button = new JButton();
      button.hide();
      buttonList.add(button);
      buttonLayout.add(button);
    }
    
    // Nested loops set the location of each of the buttons, pixelwise
    int space = (this.getWidth() / (Project2.columns + 1));
    for (int i = 0; i < 10; i++) {
      for (int j = 9; j > -1; j--) {
        if (Project2.nim1.getBoard()[j][i] == 1) {
          buttonList.get(count).enable();
          buttonList.get(count).show();
          // button x = 150+100(column count)
          /* button y = 400px - 50(number of elements in column)
           * The above is written this way so that some elements
           * of the nim board can be randomly excluded without 
           * random holes showing up in the game board.
           */
          buttonList.get(count).setLocation((space + (i * space)) - 18, 600 - columnarCount * 50);
          /* Above code is written to space out the buttons on
             the x-axis using the width of the JFrame divided
             by the number of columns/stacks in the Nim game plus
             1 (To prevent having 1 stack from being on the very end).
             The subtraction of 18 pixels is so the buttons align correctly.
             Since buttons aren't drawn centered I divided button width by 2.
          */
          buttonList.get(count).setSize(36, 36);
          buttonList.get(count).setName(Integer.toString(j) + Integer.toString(i));
          int x = j;
          int y = i;
          buttonList.get(count).addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt, x, y);
            }
          });
          //repaint();
          count++;
          columnarCount++;
        }
      }
      
      columnarCount = 0;
    }
  }  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
