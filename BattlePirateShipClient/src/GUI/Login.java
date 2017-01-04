
package GUI;

import javax.swing.JOptionPane;
import BusinessLogic.Authentication;
import Communications.SocketClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

   
    /** This class contains the login form that allows the user to join the system and be able to play. 
     * 
     */

public class Login extends javax.swing.JFrame {

   private final Authentication handler;
   private final SocketClient client;
   private String handler_return;
   private String user;
   private String pass;
   private int userid;
   private First first;
   private WindowListener exitListener;

 /** Creates new form Login, initializing components and action listener for Buttons and Text Box
     * 
     */
    public Login(First first,SocketClient client) {
        initComponents();
        this.client = client;
        this.first = first;
        handler = new Authentication(client);
        
        UserField.addActionListener(new ActionListener(){   // listener to click login button on ENTER key press

                public void actionPerformed(ActionEvent e){
                        LoginButton.doClick();

                }});
        PasswordField.addActionListener(new ActionListener(){ 

                public void actionPerformed(ActionEvent e){
                        LoginButton.doClick();

                }});
        exitListener = new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) 
        {
            leave();
        }
        };
        this.addWindowListener(exitListener);
    }
    
    
    private void leave()
    {
        first.setVisible(true);
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        UserField = new javax.swing.JTextField();
        PasswordField = new javax.swing.JPasswordField();
        UserLabel = new javax.swing.JLabel();
        PasswordLabel = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        ForgotPasswordButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        UserField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserFieldActionPerformed(evt);
            }
        });
        jPanel1.add(UserField, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 67, 218, 29));

        PasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordFieldActionPerformed(evt);
            }
        });
        jPanel1.add(PasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 125, 218, -1));

        UserLabel.setFont(new java.awt.Font("Charlemagne Std", 0, 14)); // NOI18N
        UserLabel.setForeground(new java.awt.Color(255, 255, 255));
        UserLabel.setText("User");
        jPanel1.add(UserLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 36, 91, 25));

        PasswordLabel.setFont(new java.awt.Font("Charlemagne Std", 0, 14)); // NOI18N
        PasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        PasswordLabel.setText("Password");
        jPanel1.add(PasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 102, -1, -1));

        LoginButton.setBackground(new java.awt.Color(102, 102, 102));
        LoginButton.setFont(new java.awt.Font("Charlemagne Std", 0, 14)); // NOI18N
        LoginButton.setForeground(new java.awt.Color(255, 255, 255));
        LoginButton.setText("log in");
        LoginButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        LoginButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });
        jPanel1.add(LoginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 120, 40));

        ForgotPasswordButton.setBackground(new java.awt.Color(102, 102, 102));
        ForgotPasswordButton.setFont(new java.awt.Font("Charlemagne Std", 0, 10)); // NOI18N
        ForgotPasswordButton.setForeground(new java.awt.Color(255, 255, 255));
        ForgotPasswordButton.setText("Forgot Password");
        ForgotPasswordButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        ForgotPasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ForgotPasswordButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ForgotPasswordButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 212, 120, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void UserFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserFieldActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_UserFieldActionPerformed

    /** 
     *  This method will process the field information and deliver it to the Authentication class. It should wait for a server response and print a message.
     */
    
    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        // TODO add your handling code here:
         user = UserField.getText(); //received username
         pass = PasswordField.getText(); //received password
       
        if( handler.validate(user,pass) ) //return will be true if user and pass are valid, false otherwise
            {
                        handler_return = handler.login(user,pass); //checking if login is successful
                        if(!handler_return.equals("ERROR"))
                        {
                            userid = Integer.parseInt(handler_return);
                            JOptionPane.showMessageDialog(null,"Welcome " + user + "!");
                            MainView on = new MainView(userid,this.client);
                            on.setVisible(true);
                            this.dispose();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"User and Password combination not found in database!");
                            leave();
                        }         
            }
            else
            {
                    JOptionPane.showMessageDialog(null,"Invalid Username/Password");
                    UserField.requestFocus();  
            }

    }//GEN-LAST:event_LoginButtonActionPerformed

    /** 
     *  This method will open a new ForgotPassword frame, in case the button is clicked.
     */
    
    private void ForgotPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ForgotPasswordButtonActionPerformed
        // TODO add your handling code here:
        
        ForgotPassword fp = new ForgotPassword();
        fp.setVisible(true);
    }//GEN-LAST:event_ForgotPasswordButtonActionPerformed

    private void PasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordFieldActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ForgotPasswordButton;
    private javax.swing.JButton LoginButton;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JTextField UserField;
    private javax.swing.JLabel UserLabel;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
