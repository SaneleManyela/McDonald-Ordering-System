/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package McDonaldOrderingSystem;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
/**
 *
 * @author Sanele
 */
public class frmOrderFood extends javax.swing.JFrame {

    /**
     * Creates new form frmOrderFood
     */
    public frmOrderFood() {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //Causes only this form to be closed 
        this.setLocationRelativeTo(null);
        mPopulateNumberOfGuests(); //A call to a method that populates a combo box
        arrChk = new JCheckBox[] { //An array of type JCheckBox to store all the check boxes used in the GUI
            chkChinesePork, chkTandzedaPrawns, chkBlackForest, 
            chkNeptuneInCream, chkCoca, chkRiverOlympus
        };
        mSetButtonColour(arrChk); //A call to a method that sets the colour of the check boxes
        txtOrderDate.setText(new SimpleDateFormat("yyyy/MM/dd").format(new Date())); //This sets current dtDate to txtOrderDate
        switch(DateFormat.getTimeInstance().format(new Date()).substring(0, 5).trim().indexOf(":")){
            case 1:
                //Following line of code sets to txtTime the current time when the formed was displayed
                txtTime.setText(DateFormat.getTimeInstance().format(new Date()).substring(0, 4).trim());
                break;
            case 2:
                txtTime.setText(DateFormat.getTimeInstance().format(new Date()).substring(0, 5).trim());
                break;
        }
        
        ButtonGroup btnGroup = new ButtonGroup(); //An instantiation of a ButtonGroup object for handling radio button 
        rbAM.setBackground(new Color(255, 255, 255)); //Sets the background colour of a radio button
        rbPM.setBackground(new Color(255, 255, 255)); 
        btnGroup.add(rbAM); //Add a radio button to the ButtonGroup object so that when one radio button is selected the other is deselected 
        btnGroup.add(rbPM); 
    }
    
    File flFile; //Declaration of a variable of type file
    JCheckBox[] arrChk; //Declaration of a JCheckBox array variable
    String[] arrOrderDetails; //Declaration of a String array variable
        
    //Definition of a method that get order details of the customer from the GUI
    private void mGetOrderDetails() {
        //An instantiation of a String array object. Its elements are the values
        //of the text fields, the combo box, and the radio buttons
        String[] arrStrings = new String[] {
            txtFirstname.getText(), 
            cboNumberOfGuests.getSelectedItem().toString(),
            txtOrderDate.getText(), 
            rbAM.isSelected() ? txtTime.getText() +"AM" : txtTime.getText() +"PM"
        };
        
        String[] arrMenuOptions = mCustomerMenuOptions(); //Dleclaration and assignment of a string array
        arrOrderDetails = new String[arrMenuOptions.length + arrStrings.length]; //Instantiation of an array to store order details
        
        for(int i = 0; i < arrMenuOptions.length; i++){ //Menu slected options are being stored to the order details array
            arrOrderDetails[i] = arrMenuOptions[i];
        }
        for(int index = arrMenuOptions.length; index < arrOrderDetails.length; index++) { //Values of other GUI components that were stored on arrStrings
            for(int i = 0; i < arrStrings.length; i++) {
                arrOrderDetails[index] = arrStrings[i];
            }
        }
        int i = 0; //Declaration and initiliasation of an integer loop control variable
        while(i < arrStrings.length) { //A while loop to further populate array arrOrderDetails
            for(int index = arrMenuOptions.length; index < arrOrderDetails.length; index++) {
                arrOrderDetails[index] = arrStrings[i];
                i++;
            }
        }
    }
    
    //A method that returns an array of type String carrying customer menu selections
    private String[] mCustomerMenuOptions() {
        int intCount = 0; //Declaration and initialisation of an integer variable for keeping count of delected check boxes
        for (JCheckBox arrChkElement : arrChk) {
            if (arrChkElement.isSelected()) {
                intCount++;
            }
        }
        
        String[] arrMenuOptions = new String[intCount]; //Instantiation of a string array
        
        int i = 0;
        while(i < arrChk.length) { //A while loop to get selected menu options
            for(int index = 0; index < arrMenuOptions.length; index++) {
                if(arrChk[i].isSelected()) {
                    arrMenuOptions[index] = arrChk[i].getText();  
                }
                i++;
            }
        }
        return arrMenuOptions;
    }
    
    //A method to create a new text file
    private void mCreateFile() {
        try{
            flFile = new File(txtFirstname.getText()+"_"+txtOrderDate.getText().replace('/', '-')+".txt");
            flFile.createNewFile();
        }catch(IOException e){
            JOptionPane.showMessageDialog(frmOrderFood.this, e.getMessage());
        }
    }    
    
    //A method that writes to text file details stored in the array arrOrderDetails
    private void mWriteToFile() {
        mCreateFile();
        try{           
            try (FileWriter fw = new FileWriter(flFile, true)) {
                for (int i = 0; i < arrOrderDetails.length; i++) {
                    fw.write(arrOrderDetails[i]+", ");
                }
                fw.close();
            }
            JOptionPane.showMessageDialog(frmOrderFood.this, "Order placed.");
        } catch(IOException e){
            JOptionPane.showMessageDialog(frmOrderFood.this, e.getMessage());
        }
    }
    
    //A method to set colour of the JCheckBoxes to white, it accepts as parameter a JCheckBox array
    private void mSetButtonColour(JCheckBox[] chk) {
        for (JCheckBox chkElement : chk) {
            chkElement.setBackground(new Color(255, 255, 255));
        }
    }
    
    //A method that populates a combo box with values from 1 to 10
    private void mPopulateNumberOfGuests() {
        for(int i = 1; i <= 10; i++) {
            cboNumberOfGuests.addItem(String.valueOf(i));
        }
    }
    
    //A method that refreshes the window by destroying the current window and instantiate and display a new object of the same window
    private void mRefresh() {
        this.dispose();
        frmOrderFood frmOrder = new frmOrderFood();
        frmOrder.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dskOrder = new javax.swing.JDesktopPane();
        lblFormHeading = new javax.swing.JLabel();
        lblFirstname = new javax.swing.JLabel();
        txtFirstname = new javax.swing.JTextField();
        lblNumberOfGuests = new javax.swing.JLabel();
        cboNumberOfGuests = new javax.swing.JComboBox<>();
        lblOrderDate = new javax.swing.JLabel();
        txtOrderDate = new javax.swing.JTextField();
        lblOrderTime = new javax.swing.JLabel();
        lblDishes = new javax.swing.JLabel();
        chkTandzedaPrawns = new javax.swing.JCheckBox();
        chkBlackForest = new javax.swing.JCheckBox();
        chkNeptuneInCream = new javax.swing.JCheckBox();
        chkCoca = new javax.swing.JCheckBox();
        lblDessertsHeading = new javax.swing.JLabel();
        chkRiverOlympus = new javax.swing.JCheckBox();
        lblBeveragesHeading = new javax.swing.JLabel();
        btnOrder = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        txtTime = new javax.swing.JTextField();
        rbAM = new javax.swing.JRadioButton();
        rbPM = new javax.swing.JRadioButton();
        chkChinesePork = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dskOrder.setBackground(new java.awt.Color(255, 255, 255));

        lblFormHeading.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblFormHeading.setText("Make Your Order");

        lblFirstname.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFirstname.setText("Firstname");

        lblNumberOfGuests.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNumberOfGuests.setText("Number Of Guests");

        lblOrderDate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblOrderDate.setText("Order Date");

        lblOrderTime.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblOrderTime.setText("Order Time");

        lblDishes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDishes.setText("Dishes");

        chkTandzedaPrawns.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkTandzedaPrawns.setText("Tandzeda Prawns");

        chkBlackForest.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkBlackForest.setText("Black Forest");

        chkNeptuneInCream.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkNeptuneInCream.setText("Neptune In Cream");

        chkCoca.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkCoca.setText("Coca Cola");

        lblDessertsHeading.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDessertsHeading.setText("Desserts");

        chkRiverOlympus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkRiverOlympus.setText("River Olympus");

        lblBeveragesHeading.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblBeveragesHeading.setText("Beverages");

        btnOrder.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnOrder.setText("Order");
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnClose.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        rbAM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbAM.setText("AM");

        rbPM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbPM.setText("PM");

        chkChinesePork.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkChinesePork.setText("Chinese Pork");

        dskOrder.setLayer(lblFormHeading, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(lblFirstname, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(txtFirstname, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(lblNumberOfGuests, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(cboNumberOfGuests, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(lblOrderDate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(txtOrderDate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(lblOrderTime, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(lblDishes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(chkTandzedaPrawns, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(chkBlackForest, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(chkNeptuneInCream, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(chkCoca, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(lblDessertsHeading, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(chkRiverOlympus, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(lblBeveragesHeading, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(btnOrder, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(btnRefresh, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(btnClose, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(txtTime, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(rbAM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(rbPM, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dskOrder.setLayer(chkChinesePork, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dskOrderLayout = new javax.swing.GroupLayout(dskOrder);
        dskOrder.setLayout(dskOrderLayout);
        dskOrderLayout.setHorizontalGroup(
            dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dskOrderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dskOrderLayout.createSequentialGroup()
                        .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFirstname, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumberOfGuests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblOrderDate, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblOrderTime, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dskOrderLayout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtOrderDate)
                                    .addComponent(cboNumberOfGuests, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFirstname, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(dskOrderLayout.createSequentialGroup()
                                        .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rbAM)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbPM)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dskOrderLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(dskOrderLayout.createSequentialGroup()
                        .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFormHeading)
                            .addGroup(dskOrderLayout.createSequentialGroup()
                                .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDishes)
                                    .addComponent(chkTandzedaPrawns)
                                    .addComponent(chkChinesePork))
                                .addGap(30, 30, 30)
                                .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDessertsHeading)
                                    .addComponent(chkBlackForest)
                                    .addComponent(chkNeptuneInCream)
                                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(31, 31, 31)
                        .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBeveragesHeading)
                            .addComponent(chkCoca)
                            .addComponent(chkRiverOlympus))))
                .addContainerGap())
        );
        dskOrderLayout.setVerticalGroup(
            dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dskOrderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblFormHeading)
                .addGap(18, 18, 18)
                .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDishes)
                    .addComponent(lblDessertsHeading)
                    .addComponent(lblBeveragesHeading))
                .addGap(18, 18, 18)
                .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkCoca)
                    .addComponent(chkBlackForest)
                    .addComponent(chkChinesePork))
                .addGap(18, 18, 18)
                .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkTandzedaPrawns)
                    .addComponent(chkRiverOlympus)
                    .addComponent(chkNeptuneInCream))
                .addGap(35, 35, 35)
                .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFirstname))
                .addGap(18, 18, 18)
                .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNumberOfGuests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumberOfGuests))
                .addGap(18, 18, 18)
                .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOrderDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOrderDate, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrderTime)
                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbAM)
                    .addComponent(rbPM))
                .addGap(33, 33, 33)
                .addGroup(dskOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOrder)
                    .addComponent(btnRefresh)
                    .addComponent(btnClose))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dskOrder)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dskOrder)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(txtOrderDate.getText()));
        Date dtDate = calendar.getTime();
        
        int intOrderHour = 0, intMns = 0, intCurrentHour = 0;
        if(DateFormat.getTimeInstance().format(new Date()).substring(0, 5).trim().indexOf(":") == 1){
            intCurrentHour = Integer.parseInt(DateFormat.getTimeInstance().format(new Date()).substring(0, 1).trim());
        } else if(DateFormat.getTimeInstance().format(new Date()).substring(0, 5).trim().indexOf(":") == 2){
            intCurrentHour = Integer.parseInt(DateFormat.getTimeInstance().format(new Date()).substring(0, 2).trim());
        }
        
        char[] chTime = txtTime.getText().toCharArray();
        if(chTime[1] == ':'){
            intOrderHour = Integer.parseInt(String.valueOf(chTime[0]));
            intMns = Integer.parseInt(String.valueOf(chTime[2]+""+chTime[3]));
        } else if(chTime[2] == ':') {
            intOrderHour = Integer.parseInt(String.valueOf(chTime[0]+""+chTime[1]));
            intMns = Integer.parseInt(String.valueOf(chTime[3]+""+chTime[4]));
        }
        
        int intOrderCheck = 0; //Declaration and initialisation of an integer variable to store count of un-selected check boxes
        for(int i = 0; i < arrChk.length; i++) {
            if(!arrChk[i].isSelected()){
                intOrderCheck++;
            }
        } 
        
        if(intOrderCheck == arrChk.length){ //Checks if number of unselected check boxes is equal to the number of checkboxes in GUI
            JOptionPane.showMessageDialog(frmOrderFood.this, "Please select what to order from the menu.");
        }
        else if(txtFirstname.getText().equals("") || txtFirstname.getText().length() < 2){ //Validates name
            JOptionPane.showMessageDialog(frmOrderFood.this, "Please provide your full first name.");
            txtFirstname.requestFocusInWindow();
        } else if(txtOrderDate.getText().equals("")){
            JOptionPane.showMessageDialog(frmOrderFood.this, "Please provide date of the order.");
        }
        else if(txtOrderDate.getText().replace("/", "-").equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {
            if((dtDate.toString().substring(0, 10).equals(new Date().toString().substring(0, 10))
                    && dtDate.toString().substring(dtDate.toString().length() - 4, dtDate.toString().length()).equals(
                            new Date().toString().substring(dtDate.toString().length() - 4, dtDate.toString().length()))) 
                && (rbPM.isSelected() && (intOrderHour >= intCurrentHour + 1)) || (rbAM.isSelected() && (intOrderHour >= 8)
                    && (intOrderHour >= intCurrentHour + 1)))
            {
                if((rbPM.isSelected() && intOrderHour >= 9)){
                    int intX = JOptionPane.showConfirmDialog(frmOrderFood.this, "This order cannot be made."
                            + "\nWould you like to reschedule to next day, 8:30 PM?",
                        "Reschedule", JOptionPane.YES_NO_OPTION);
                    if(intX == JOptionPane.YES_OPTION) {
                        Calendar c = Calendar.getInstance(); //Get a calendar instance
                        c.setTime(new Date()); // Now use current dtDate.
                        c.add(Calendar.DATE, 1); // Adds 1 days to the current dtDate
                        Date dt = c.getTime();
                        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                        mGetOrderDetails();
                        for(int i = 0; i < arrOrderDetails.length; i++){
                            if(arrOrderDetails[i].contains("/")){
                                arrOrderDetails[i] = sm.format(dt);
                            }
                            if(arrOrderDetails[i].contains(":")){
                                arrOrderDetails[i] = "8:30PM";
                            }
                        }
                        mWriteToFile();
                    } else if(intX == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(frmOrderFood.this, "Please reconsider the date of dining.");
                    }
                }else {
                    mGetOrderDetails();
                    mWriteToFile();
                } 
            }else {
                JOptionPane.showMessageDialog(frmOrderFood.this, rbAM.isSelected() ? 
                        "Please change your hour of arrival to "+ (intCurrentHour + 1 ) +":"+intMns +"AM" : 
                        "Please change your hour of arrival to "+ (intCurrentHour + 1 ) +":"+intMns +"PM");
            }
        } else if(dtDate.after(new Date())) {
            if((rbPM.isSelected()) || (rbAM.isSelected() && (intOrderHour >= 8))){
                if((rbPM.isSelected() && intOrderHour >= 9 && intOrderHour <= 12)){
                    JOptionPane.showMessageDialog(frmOrderFood.this, "Please reconsider the date of dining.");
                } else{
                    mGetOrderDetails();
                    mWriteToFile();
                }
            }else {
                JOptionPane.showMessageDialog(frmOrderFood.this, rbAM.isSelected() ? "Please change your hour of arrival to "+ (intOrderHour + 1 ) +":"+intMns +"AM" : "Please change your hour of arrival to "+ (intOrderHour + 1 ) +":"+intMns +"PM");
            }
        }
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        mRefresh();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.hide();
    }//GEN-LAST:event_btnCloseActionPerformed

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
            java.util.logging.Logger.getLogger(frmOrderFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmOrderFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmOrderFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmOrderFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmOrderFood().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cboNumberOfGuests;
    private javax.swing.JCheckBox chkBlackForest;
    private javax.swing.JCheckBox chkChinesePork;
    private javax.swing.JCheckBox chkCoca;
    private javax.swing.JCheckBox chkNeptuneInCream;
    private javax.swing.JCheckBox chkRiverOlympus;
    private javax.swing.JCheckBox chkTandzedaPrawns;
    private javax.swing.JDesktopPane dskOrder;
    private javax.swing.JLabel lblBeveragesHeading;
    private javax.swing.JLabel lblDessertsHeading;
    private javax.swing.JLabel lblDishes;
    private javax.swing.JLabel lblFirstname;
    private javax.swing.JLabel lblFormHeading;
    private javax.swing.JLabel lblNumberOfGuests;
    private javax.swing.JLabel lblOrderDate;
    private javax.swing.JLabel lblOrderTime;
    private javax.swing.JRadioButton rbAM;
    private javax.swing.JRadioButton rbPM;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtOrderDate;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
