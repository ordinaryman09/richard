import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * @author Richard Lung
 * @version 1.0
 * */

//create a KochCurve class
public class KochCurve extends JPanel {
  //create a private class for JLabel and ButtonListener
  private ButtonListener buttonListener;  
  private JLabel disp;
  
  //create a constructor for KochCurve
  public KochCurve() {
    //instantiate the JLabel
    disp = new JLabel( "");
    // create panel with increase/decrease order buttons
    JButton increaseButton = new JButton( "Increase" );
    JButton decreaseButton = new JButton( "Decrease" );
    JPanel buttons = new JPanel( );
    buttons.add( increaseButton );
    buttons.add( decreaseButton );
    //set up the colors of the buttons
    increaseButton.setBackground( Color.yellow );
    decreaseButton.setBackground( Color.green );   
    this.add( buttons);
    this.add( disp );
    
    // set up listener for the buttons
    buttonListener = new ButtonListener( );
    increaseButton.addActionListener( buttonListener );
    decreaseButton.addActionListener( buttonListener );
  }
  
  /**
   * create a paintComponent class to call the starter of recursive method for the setted points
   * (the points are calculated to create a nice equilateral triangle)
   * 
   * @param g Graphics
   * */
  public void paintComponent(Graphics g) 
  {
    super.paintComponent(g);
    //call the recursive
    drawKochCurves(g,buttonListener.returnOrder(),500,(int)(400 + 0.5*Math.sqrt(30000)),  400, (int)(400 - 0.5*Math.sqrt(30000)));
    drawKochCurves(g,buttonListener.returnOrder(),300, (int)(400 + 0.5*Math.sqrt(30000)), 500,  (int)(400 + 0.5*Math.sqrt(30000)));
    drawKochCurves(g,buttonListener.returnOrder(), 400, (int)(400 - 0.5*Math.sqrt(30000)), 300, (int)(400 + 0.5*Math.sqrt(30000)));
    //set the text to show the order when it's finish drawing the curves
    disp.setText("The order is " + buttonListener.returnOrder());
    repaint();
  }
  
  /**
   * create the recursive method to draw the curves
   * @param graph Graphics 
   * @param level the order of KochCurves
   * @param x1 the x initial point
   * @param y1 the y initial point
   * @param x5 the x last point
   * @param y5 the y last point
   * */
  public void drawKochCurves(Graphics graph, int level, int x1,int y1,int x5, int y5) {
    //intantiate the variable to store the points
    int x2,x3,x4;
    int y2,y3,y4;
    int deltaX,deltaY;
    //condition when the order is 0, draw the curve
    if(level == 0) { 
      
      graph.drawLine(x1,y1,x5,y5);
      return;
    }
    //condition when the order is greater than 1, call the recursive
    else if ( level >= 1) {      
      
      //these are mathematical calculations for setting up the points
      deltaX = x5-x1;
      deltaY = y5-y1;
      
      x2 = x1+deltaX/3;
      y2 = y1+deltaY/3;
      
      x3 = (int)((0.5 * (x1 + x5)) + Math.sqrt(3) * (y1-y5) /6);
      y3 = (int)(0.5 * (y1 + y5) + Math.sqrt(3) * (x5-x1) / 6);
      
      x4 = x1 + 2 * deltaX/3;
      y4 = y1 + 2 * deltaY/3;
      
      level--;
      //call the recursive for the point 1 up to point 5 to draw the line.
      drawKochCurves(graph,level, x1, y1, x2, y2);
      drawKochCurves(graph,level, x2, y2, x3, y3);
      drawKochCurves(graph,level, x3, y3, x4, y4);
      drawKochCurves(graph,level, x4, y4, x5, y5);
      
    }    
  }
  
  public static void main(String[] args) {
    // Create the simulation model and populate it
    JFrame frame = new JFrame("Koch Curves");
    KochCurve koch = new KochCurve();
    //showing the drawing in the window
    frame.getContentPane().add(koch);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(800,800);
    frame.setVisible(true);
  }  
}

class ButtonListener implements ActionListener {
  //instantiate order for updating the value when the button is clicked
  private int order = 0;
  
  /**
   * 
   * this class will return the number of buttons click to determine the order, and the value will be passed in to the KochCurve class as the order.
   * @return order
   * */
  public int returnOrder() {
    return order; 
  }
  
  /**
   * this class will handle the action performed when the button is clicked
   * @param e
   * */
  public void actionPerformed( ActionEvent e ) {
    //handler if the increase button is clicked, update the value of order +1
    if ( e.getActionCommand( ).equals( "Increase" ) ) {
      
      order += 1;
    } 
    //handler if the decrease button is clicked, update the value of order -1
    else if ( e.getActionCommand( ).equals( "Decrease" ) ) {
      if(order >= 1) {
        order -= 1;
      } 
    } 
  }
}