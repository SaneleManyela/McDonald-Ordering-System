/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package McDonaldOrderingSystem;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sanele
 */
public class frmDisplayOrderDetailsForWaiterInterface extends javax.swing.JFrame {

    /**
     * Creates new form frmDisplayOrderDetails
     */
    public frmDisplayOrderDetailsForWaiterInterface() {
        initComponents();
        this.setSize(600, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 20)); //Set layout of the form
        JPanel jpPanel = new JPanel(new BorderLayout(10, 20));
        jpPanel.setOpaque(true); //Set the panel container ready to be painted
        jpPanel.setBackground(new Color(255, 255, 255));
        mReadOrderFile(mPath()); //Calls a method to read a file
        mGetOrderData(); //Calls a method to get order details
        this.add(jpPanel); //Current object of this form adds the JPanel component
        mTable(arrOrderData); //A call to a table method that display order data
    }
    
    
    //Creating an object of class StringBuilder used for 
    //holding contents of a read file.
    StringBuilder sb = new StringBuilder();
    
    String[] arrOrderData; //Declaration of an array to hold order details
        
    public File mPath() { //A method that returns a path to a text file to be read from
        File flPath = new File("Sanele_2020-09-06.txt");
        return flPath;
    }
    
    //A method that reads a file passed as an argument to it
    //and append the read details to a StringBuilder object
    public void mReadOrderFile(File flOrder) {
        FileReader reader;
        int intChar;
        
        try{
            reader = new FileReader(flOrder);
            while((intChar = reader.read()) != -1){
                sb.append((char)intChar);
            }
            reader.close();
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(frmDisplayOrderDetailsForWaiterInterface.this, ex.getMessage());
        }catch(IOException ex){
            JOptionPane.showMessageDialog(frmDisplayOrderDetailsForWaiterInterface.this, ex.getMessage());
        }     
    }
    
    //A method to get details read from a file
    public void mGetOrderData() {
        arrOrderData = new String[mGetOrderLength()];
        for(int i = 0; i < arrOrderData.length; i++) {
            arrOrderData[i] = mStringDestroyer(sb);
        }
    }
    
    //A method that returns as an integer a count of the order details
    private int mGetOrderLength() {
        int intLength = 0;
        for(int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i) == ',') {
                intLength++ ;
            }
        }
        return intLength;
    }
    
    //A method that retrieves a string which is a substring and deletes the
    //retrieved string.
    private String mStringDestroyer(StringBuilder sb) {
        String strDestroyed = sb.substring(0, sb.indexOf(",")).trim();
        sb.delete(0, sb.indexOf(",")).deleteCharAt(sb.indexOf(","));
        return strDestroyed;
    }
    
    //A method that diplays file contents on a JTable
    private void mTable(String[] arrData) {
        
        DefaultTableModel dmModel = (DefaultTableModel)tblOrder.getModel();
        tblOrder = new JTable((DefaultTableModel)tblOrder.getModel());
                        
        String[] arrRowData = new String[arrData.length];
        for(int i = 0; i < arrRowData.length; i++) {
            arrRowData[i] = arrData[i];
        }
        
        dmModel.addRow(mSortForWaiterInterface(arrRowData));
                
    }
    
    //A method that sorts the data for the waiter interface by storing to a string
    //array all the values to be returned <customer firstname, number of guests, date and time of arrival/>
    //The method accepts as an argument an array of type string
    private String[] mSortForWaiterInterface(String[] arrRowData) {
        String[] arrSorted = new String[4]; //An array of type string used to hold the four values to be displayed on this interface 
        String[] arrNumbers = new String[] { 
          "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "10"
        };
        //Checks if a value in arrRowData is equal to any of the numbers in arrNumbers
        //The value that equals is inserted on index 1 of arrSorted
        for(int index = 0; index < arrRowData.length; index++){
            for(int i = 0; i < arrNumbers.length; i++){
                if(arrRowData[index] == null ? arrNumbers[i] == null : arrRowData[index].equals(arrNumbers[i])){
                    arrSorted[1] = arrRowData[index];
                }
            }
        }
        
        //Insert indices 2, 3, and 0 by filtering out all the food and drinks menu option
        //and set the value of index 2 by finding an element with a forward slash, set element
        //3 by finding and inserting a value with a colon, and inserts index 0 by finding a string that has a length greater than 2
        for(int i = 0; i < arrRowData.length; i++){
            if(!(arrRowData[i].contains("Pork") || arrRowData[i].contains("Prawns")
                    || arrRowData[i].contains("Forest") || arrRowData[i].contains("Cream")
                    || arrRowData[i].equals("Coca Cola") || arrRowData[i].contains("Olympus")
                    || arrRowData[i].equals("null"))) {
                if(arrRowData[i].contains("/")) {
                    arrSorted[2] = arrRowData[i];
                } else if(arrRowData[i].contains(":")) {
                    arrSorted[3] = arrRowData[i];
                } else if(arrRowData[i].length() > 2){
                    arrSorted[0] = arrRowData[i];
                }
            }
        }
        return arrSorted; //returns the sorted 4 element array
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jsPane = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Number Of Guests", "Date", "Time Of Arrival"
            }
        ));
        jsPane.setViewportView(tblOrder);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jsPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
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
            java.util.logging.Logger.getLogger(frmDisplayOrderDetailsForWaiterInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDisplayOrderDetailsForWaiterInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDisplayOrderDetailsForWaiterInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDisplayOrderDetailsForWaiterInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDisplayOrderDetailsForWaiterInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jsPane;
    private javax.swing.JTable tblOrder;
    // End of variables declaration//GEN-END:variables
}
