//package edu.ucsb.cs56.M16.chunqingliu.go;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event. ActionEvent;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JTextArea;
import java.awt.Color;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.net.URL;




/**
 * An Swing component for playing go

  
   @author David Winkenwerder, Dustin Henderson
   @author Keith Waldron, Nick Abrahan
   @author Jeffrey Liu and Lauren Dumapias
   @author Chunqing LIu and Xingyuan Zhang
   @version CS56 7/21/16
*/

public class GoComponent extends JComponent
{
    private GoGame game; 
    private GoGrid grid;
    private JTextArea md;
  
    private JButton [] buttons = new JButton[362];

    /** Constructor
	
	@param game an object that implements the GoGame interface to keep track
	            of the moves in each game, ensuring the rules are followed and detecting
		                when someone has won.
				@param md an object that implements the MessageDestination interface.  This is just
				           a place to send any messages that need to be communicated to the user.
					      Making this separate allows a user of this components to decide to
					         send those messages to the console, or to a variety of different
						    swing Widgets, or even to a web page, as needed.
    */
       
    public GoComponent(GoGame game, JTextArea md) {
	super(); // is this line necessary?  what does it do?

	this.game = game;  // the Go game
	this.md = md;  // a place we can write messages to
	
	// note columns ignored when rows are set
	// number of columns is implicit from the number of things added

	this.setLayout(new GridLayout(19,0)); 
	for(int i=1; i<=361; i++) {
	    JButton jb = new JButton("");
	    buttons[i] = jb;
	    Color tan = new Color(210,180,140);
	    buttons[i].setBackground(tan);

	    buttons[i].setForeground(tan);
	    buttons[i].setBorderPainted(false);
	    

	    ImageIcon img = new ImageIcon(getClass().getResource("/images/Picture1.png"));

	    buttons[i].setIcon(img);
	    /*buttons[i].setSize(30,30);
	    int width = buttons[i].getWidth();
	    int height = buttons[i].getHeight();

	    Image imgs = img.getImage();
	    imgs.getScaledInstance(width,height,1);*/
	    
	    
				    
	 
	    

	   

	    
	    jb.addActionListener(new ButtonListener(i));
	    this.add(jb);
	    
	    validate();
	}

	
    }    


    class ButtonListener implements ActionListener {
	private int num;
	

	public ButtonListener(int i) {
	    super();  // is this line necessary? what does it do?
	    this.num = i;

	}

	public void actionPerformed (ActionEvent event) {
            char turn=game.getTurn();

                       String nextTurn = "Black" ;
                       if(turn == 'W')
                           nextTurn = "Black";
                       if(turn == 'B')
                           nextTurn = "White";

                       if (turn==' ')
                           return;

                       if (!game.isBlank(num)) {
                           md.append("\n\nThat square is already occupied!");
                           return;
                       }

		       // makeMove returns true if move is legal, false if move is illegal
		       if (!game.makeMove(num)) {
                               md.append("\nCannot place tile there, it would be surrounded\n");
			       return;
		       }
		       
                       game.changeTurn();
           //	    char winner=game.move(num);
           //	    game.move2(num);
           //	    game.changeTurn();
                       JButton jb = buttons[num];
                       jb.setFont(new Font("Arial",Font.BOLD,25));
//	    jb.setText(Character.toString(turn)); // this is how we convert char to String
	    for(int i=1;i<362;i++){
		if(game.charAt(i) == 'W'){ //if element in Array list is W, set background color of JButton to WHITE
		    //buttons[i].setBackground(Color.WHITE);
		    //buttons[i].setForeground(Color.WHITE); // set font color of JButton to Black for visibility

		    

		     ImageIcon img = new ImageIcon(getClass().getResource("/images/whiteStone.jpg"));
		     buttons[i].setIcon(img);
		}
		else if(game.charAt(i) == 'B'){ //if element in ArrayList is B, set background color of JButton to BLACK
		    //buttons[i].setBackground(Color.BLACK);
		    //buttons[i].setForeground(Color.BLACK); // set font color of JButton to White for visibility

		     ImageIcon img = new ImageIcon(getClass().getResource("/images/blackStone.jpg"));
		     buttons[i].setIcon(img);
		}
		else if(game.charAt(i) == ' '){ //if ' ' element in Arraylist, set background color back to tan.
		    Color tan = new Color(210,180,140);
		    //buttons[i].setBackground(tan);
		    //buttons[i].setForeground(Color.RED);

		     ImageIcon img = new ImageIcon(getClass().getResource("/images/Picture1.png"));
		     buttons[i].setIcon(img);
		}
/*
		if(game.charAt(i)!=' '){
		    buttons[i].setText(Character.toString(game.charAt(i)));
		}
		else{
		    buttons[i].setText("");
		}
*/		
	    }

	    //prints current score of game and whos turn it is 
	    md.append("\n\nWhite Score: " + game.getWScore() + "\nBlack Score: " + game.getBScore());
	    md.append("\n" + nextTurn +"s turn.");
		
	    // check for a winner
/*	    if (winner=='D')
		md.append("\nPhooey.  It's a draw.\n");
	    else if (winner!=' ')
	    md.append("\n"+ winner + " wins!\n");
*/
	}
    }
   /** restart the game by making the grid go back to its origin color
     */
    public void restart(){
	for(int i = 1;i<=362;i++){
	    Color tan = new Color(210,180,140);
	    buttons[i].setBackground(tan);
	    buttons[i].setForeground(tan);

	     buttons[i].setBorderPainted(false);

	  
	      ImageIcon img = new ImageIcon(getClass().getResource("/images/Picture1.png"));

	    buttons[i].setIcon(img);

	}
    }
}
