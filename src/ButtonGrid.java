import javax.swing.*;

import java.awt.Color;
import java.awt.GridLayout; //imports GridLayout library
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class ButtonGrid implements  ActionListener{
 
        JFrame frame; //creates frame
        JPanel titlePanel, itemPanel, componentPanel;
        JButton[][] grid; //names the grid of buttons
        JButton[][] itemGrid;
        int width,length;
        JButton resetButton, testButton, addButton;
        JLabel lengthL,widthL,benefitL;
    	JTextField widthTF,lengthTF,benefitTF;
    	JPanel item;
    	GridComponent component;
    	int widthC,lengthC;
    	  
        
        public JPanel createContentPane (int width,int length){
        	JPanel totalGUI = new JPanel();
        	this.width=width;
        	this.length=length;
            totalGUI.setLayout(null);  
            grid=new JButton[width][length]; //allocate the size of grid
            titlePanel = new JPanel();
            titlePanel.setLayout(null);
            titlePanel.setLocation(20, 50);
            titlePanel.setSize(300, 500);
            totalGUI.add(titlePanel);
            
           
            
            resetButton = new JButton("Reset");
            resetButton.setLocation(30*(length+1), 0);
            resetButton.setSize(100, 30);
            resetButton.addActionListener(this);
            titlePanel.add(resetButton);
            
            
            
            testButton = new JButton("400*30");
           testButton.setLocation(0, 400);
            testButton.setSize(400, 30);
            testButton.addActionListener(this);
            titlePanel.add(testButton);
            
            
            for(int x=0; x<width; x++){
                    for(int y=0; y<length; y++){
                            grid[x][y]=new JButton(""); //creates new button     
                            //adds button to grid
                            grid[x][y].setLocation(y*30,  x*30);
                            grid[x][y].setSize(30, 30);
                            grid[x][y].addActionListener(this);
                            grid[x][y].setBackground(Color.WHITE);
                           titlePanel.add(grid[x][y]); 
                    }
            }
            
            itemPanel = new JPanel();
            itemPanel.setLayout(null);
            itemPanel.setLocation(40*(length+3), 50);
            itemPanel.setSize(500, 500);
            totalGUI.add(itemPanel);
            
            lengthL = new JLabel("Length");
            lengthL.setLocation(0, 0);
            lengthL.setSize(100, 20);
            lengthL.setForeground(Color.black);
            itemPanel.add(lengthL);
            
            widthL = new JLabel("Width");
            widthL.setLocation(0, 40);
            widthL.setSize(100, 20);
            widthL.setForeground(Color.black);
            itemPanel.add(widthL);
     
            benefitL = new JLabel("Benefit");
            benefitL.setLocation(0, 80);
            benefitL.setSize(100, 20);
            benefitL.setForeground(Color.black);
            itemPanel.add(benefitL);
      
            lengthTF = new JTextField(5);
            lengthTF.setText(1 + "");
            lengthTF.setLocation(100,0);
            lengthTF.setSize(100, 20);
    		itemPanel.add(lengthTF);
    		
    		widthTF = new JTextField(1);
            widthTF.setText(1 + "");
            widthTF.setLocation(100,40);
            widthTF.setSize(100, 20);
    		itemPanel.add(widthTF);
    		
    		benefitTF = new JTextField(5);
            benefitTF.setText(10 + "");
            benefitTF.setLocation(100,80);
            benefitTF.setSize(100, 20);
    		itemPanel.add(benefitTF);
    		
    		addButton = new JButton("Add Item");
            addButton.setLocation(30*(length+1), 0);
           addButton.setSize(100, 30);
            addButton.addActionListener(this);
            itemPanel.add(addButton);
         
            componentPanel = new JPanel();
            componentPanel.setLayout(null);
            componentPanel.setLocation(500,400);
            componentPanel.setSize(300, 500);
            totalGUI.add( componentPanel);
            
            
            totalGUI.setOpaque(true);
            return totalGUI;
        }
        
    
        public void actionPerformed(ActionEvent e) {
        	if(e.getSource() == resetButton)
            {
        		   for(int x=0; x<width; x++){
                       for(int y=0; y<length; y++){
                       	
                       		grid[x][y].setBackground(Color.WHITE);
                       	}  
                          
                       }
               }
        	
        	else if (e.getSource()== addButton) {
        		
        	/*	widthC=Integer.parseInt(widthTF.getText());
        		lengthC=Integer.parseInt(lengthTF.getText());
        		 itemGrid=new JButton[widthC][lengthC];  
        		 for(int x=0; x<widthC; x++){
                     for(int y=0; y<lengthC; y++){
                             itemGrid[x][y]=new JButton(""); //creates new button     
                             //adds button to grid
                             itemGrid[x][y].setLocation(y*30,  x*30);
                             itemGrid[x][y].setSize(30, 30);
                             itemGrid[x][y].addActionListener(this);
                             itemGrid[x][y].setBackground(Color.WHITE);
                            componentPanel.add(itemGrid[x][y]); 
                     }
             }
        		
        		frame.revalidate(); 
        		frame.validate();*/
        		componentPanel.add(new JButton("Button"));
        		componentPanel.revalidate();
        		 frame.validate();
        	}
                
        	else{
          
            for(int x=0; x<width; x++){
                for(int y=0; y<length; y++){
                	if(e.getSource() == grid[x][y]){
                		if(grid[x][y].getBackground()==Color.WHITE){
                			grid[x][y].setBackground(Color.BLACK);
                		}
                		else{
                			grid[x][y].setBackground(Color.WHITE);
                		}
                	}  
                   
                }
        }
        	}
              
           
        }
        public ButtonGrid(int width, int length){ //constructor
        	frame=new JFrame("Town Planning");
                frame.setContentPane(createContentPane(width,length));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 400);
              // frame.pack(); //sets appropriate size for frame
                frame.setVisible(true); //makes frame visible
        }
        public static void main(String[] args) {
                new ButtonGrid(10,6);//makes new ButtonGrid with 2 parameters
        }
}