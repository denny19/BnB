

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TicTacToe implements ActionListener {

    private JFrame window = new JFrame("Town Planning");
    private JButton buttons[] = new JButton[101];
    private int count = 0;
    private String letter = "";
    private boolean win = false;
    GridLayout k = new GridLayout(10,10);
    public TicTacToe(){
    /*Create Window*/
   // window.setSize(300,300);
    	window.setPreferredSize(new Dimension(1000, 1000));
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    window.setLayout(k);
    
    /*Add Buttons To The Window*/
    for(int i=0; i<=100; i++){
        buttons[i] = new JButton();
        window.add(buttons[i]);
        buttons[i].addActionListener(this);
    }
    
    /*Make The Window Visible*/
    window.setVisible(true);
    }
    
    /**
     When an object is clicked, perform an action.
     @param a action event object
     */
    public void actionPerformed(ActionEvent a) {
        JButton pressedButton = (JButton)a.getSource(); 
         pressedButton.setText(letter);
         pressedButton.setEnabled(false);
            }
    
    public static void main(String[] args){
        TicTacToe starter = new TicTacToe();
    }
}