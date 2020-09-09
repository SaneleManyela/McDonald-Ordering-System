/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package McDonaldOrderingSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Sanele
 */
public class McDonaldOrderingSystem extends JFrame {
    public McDonaldOrderingSystem() {
       this.setSize(600, 300); //Set the size of the form
       this.setDefaultCloseOperation(EXIT_ON_CLOSE); //When closing this form the app will terminate
       this.setTitle("McDonald Ordering System"); //Set title of this form
       this.setLocationRelativeTo(null); //Causes the form to be displayed middle of the screen
       mCreateWindow(); //Call to a method that create and put window controls
       this.setVisible(true); //Causes this form to be displayed
    }
    
    //A method that creates the window display. It defines how components should be
    //displayed inside a JPanel container which is in turn displayed in the window
    private void mCreateWindow() {
        JPanel jpPanel = new JPanel(new BorderLayout()); 
        jpPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        jpPanel.setOpaque(true);
        jpPanel.setBackground(new Color(255, 255, 255));
        jpPanel.add(mCreateLabel("McDonald Ordering System"), BorderLayout.NORTH); 
        JPanel jpComponents = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jpComponents.setBorder(new EmptyBorder(20, 20, 20, 20));
        jpComponents.setOpaque(true);
        jpComponents.setBackground(new Color(255, 255, 255));
        jpComponents.add(mCreateButton("Make An Order", 150, 100, this::mOpenOrderingWindow));
        jpComponents.add(mCreateButton("Waiter Interface", 150, 100, this::mOpenWaiterInterface));
        jpComponents.add(mCreateButton("Kitchen Interface", 150, 100, this::mOpenKitchenInterface));
        jpPanel.add(jpComponents, BorderLayout.CENTER);
        this.add(jpPanel);
    }
    
    //A method that accepts as an argument an action event 
    //and display a form used to order food
    private void mOpenOrderingWindow(ActionEvent e) {
        frmOrderFood frmOrder = new frmOrderFood();
        frmOrder.show();
    }
    
    //A method that accepts as an argument an action event
    //and display a form used by waiters to display order information
    private void mOpenWaiterInterface(ActionEvent e) {
        frmDisplayOrderDetailsForWaiterInterface frmDisplayWaiterInterface =
                new frmDisplayOrderDetailsForWaiterInterface();
        frmDisplayWaiterInterface.show();
    }
    
    //A method that accepts as an argument an action event
    //and display a form used by kitchen staff to display 
    //information about the ordered dishes
    private void mOpenKitchenInterface(ActionEvent e){
        frmDisplayOrderDetailsForKitchenInterface frmDisplay =
                new frmDisplayOrderDetailsForKitchenInterface();
        frmDisplay.show();
    }
    
    /**
     * A method that creates and returns a JButton. It is defined with the following
     * parameters: @param str used to set button text, @param intWidth used to set width of the 
     * button, @param intHeight used to set the height of the button, and @param listener, an
     * action event object that tells the button what to do on an event it is clicked
    */
    private JButton mCreateButton(String str, int intWidth, int intHeight,
            ActionListener listener) {
        JButton btn = new JButton(str);
        btn.addActionListener(listener);
        btn.setPreferredSize(new Dimension(intWidth, intHeight));
        btn.setBackground(new Color(255, 255, 255));
        return btn;
    }
    
    //A method that returns a JLabel and defined with a string parameter
    //for setting the label text
    private JLabel mCreateLabel(String str) {
        JLabel lblLabel = new JLabel(str);
        lblLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        return lblLabel;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Instantiation of an object of class McDonaldOrderingSystem
        McDonaldOrderingSystem mcDee = new McDonaldOrderingSystem();
    }
    
}
