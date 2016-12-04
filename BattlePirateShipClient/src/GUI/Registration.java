
package GUI;

import BusinessLogic.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

  /** 
     *  This class contains the form that allows a guest to register on the system.
     */

public class Registration extends javax.swing.JFrame {

   
   private static First mainFirst;
   private final Authentication handler = new Authentication();
   private int handler_return;
   private String name;
   private String user;
   private String pass;
   private String email;
   private String question;
   private String answer;
   
    /**
     * Creates new form Registration, including listener for buttons and text boxes.
     * @param main This argument allows the form to get back to the First interface window
     *
     */
   
    public Registration(First main) {
        initComponents();
        this.mainFirst = main;
        NameField.addActionListener(new ActionListener(){   // listener to click login button on ENTER key press

                public void actionPerformed(ActionEvent e){
                        SignUpButton.doClick();

                }});
        LoginField.addActionListener(new ActionListener(){   // listener to click login button on ENTER key press

                public void actionPerformed(ActionEvent e){
                        SignUpButton.doClick();

                }});
       PasswordField.addActionListener(new ActionListener(){   // listener to click login button on ENTER key press

                public void actionPerformed(ActionEvent e){
                        SignUpButton.doClick();

                }});
       ConfirmPasswordField.addActionListener(new ActionListener(){   // listener to click login button on ENTER key press

                public void actionPerformed(ActionEvent e){
                        SignUpButton.doClick();

                }});
       ConfirmPasswordField.addActionListener(new ActionListener(){   // listener to click login button on ENTER key press

                public void actionPerformed(ActionEvent e){
                        SignUpButton.doClick();

                }});
       EmailField.addActionListener(new ActionListener(){   // listener to click login button on ENTER key press

                public void actionPerformed(ActionEvent e){
                        SignUpButton.doClick();

                }});
       AnswerField.addActionListener(new ActionListener(){   // listener to click login button on ENTER key press

                public void actionPerformed(ActionEvent e){
                        SignUpButton.doClick();

                }});
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RegisterPanel = new javax.swing.JPanel();
        NameLabel = new javax.swing.JLabel();
        LoginLabel = new javax.swing.JLabel();
        PasswordLabel = new javax.swing.JLabel();
        NameField = new javax.swing.JTextField();
        LoginField = new javax.swing.JTextField();
        ConfirmPasswordLabel = new javax.swing.JLabel();
        EmailLabel = new javax.swing.JLabel();
        EmailField = new javax.swing.JTextField();
        PassWordRecoveryText = new javax.swing.JLabel();
        QuestionCombo = new javax.swing.JComboBox<>();
        AnswerLabel = new javax.swing.JLabel();
        AnswerField = new javax.swing.JTextField();
        SignUpButton = new javax.swing.JButton();
        PasswordField = new javax.swing.JPasswordField();
        ConfirmPasswordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        RegisterPanel.setBackground(new java.awt.Color(51, 51, 51));
        RegisterPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Personal Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Charlemagne Std", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        RegisterPanel.setForeground(new java.awt.Color(255, 255, 255));
        RegisterPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NameLabel.setFont(new java.awt.Font("Charlemagne Std", 0, 12)); // NOI18N
        NameLabel.setForeground(new java.awt.Color(255, 255, 255));
        NameLabel.setText("Name");
        RegisterPanel.add(NameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 50, 20));

        LoginLabel.setFont(new java.awt.Font("Charlemagne Std", 0, 12)); // NOI18N
        LoginLabel.setForeground(new java.awt.Color(255, 255, 255));
        LoginLabel.setText("Login ");
        RegisterPanel.add(LoginLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 101, -1, -1));

        PasswordLabel.setFont(new java.awt.Font("Charlemagne Std", 0, 12)); // NOI18N
        PasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        PasswordLabel.setText("Password");
        RegisterPanel.add(PasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 162, -1, -1));

        NameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameFieldActionPerformed(evt);
            }
        });
        RegisterPanel.add(NameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 63, 509, -1));

        LoginField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginFieldActionPerformed(evt);
            }
        });
        RegisterPanel.add(LoginField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 124, 509, -1));

        ConfirmPasswordLabel.setFont(new java.awt.Font("Charlemagne Std", 0, 12)); // NOI18N
        ConfirmPasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        ConfirmPasswordLabel.setText("Confirm Password");
        RegisterPanel.add(ConfirmPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        EmailLabel.setFont(new java.awt.Font("Charlemagne Std", 0, 12)); // NOI18N
        EmailLabel.setForeground(new java.awt.Color(255, 255, 255));
        EmailLabel.setText("E-mail");
        RegisterPanel.add(EmailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, -1, -1));

        EmailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailFieldActionPerformed(evt);
            }
        });
        RegisterPanel.add(EmailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 322, 509, -1));

        PassWordRecoveryText.setFont(new java.awt.Font("Charlemagne Std", 0, 12)); // NOI18N
        PassWordRecoveryText.setForeground(new java.awt.Color(255, 255, 255));
        PassWordRecoveryText.setText("Password recovery ");
        RegisterPanel.add(PassWordRecoveryText, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 401, -1, -1));

        QuestionCombo.setFont(new java.awt.Font("Charlemagne Std", 0, 10)); // NOI18N
        QuestionCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What is the name of your first pet?", "Who is your best mate?", "What is your favorite sport?" }));
        QuestionCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuestionComboActionPerformed(evt);
            }
        });
        RegisterPanel.add(QuestionCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 429, -1, -1));

        AnswerLabel.setFont(new java.awt.Font("Charlemagne Std", 0, 10)); // NOI18N
        AnswerLabel.setForeground(new java.awt.Color(255, 255, 255));
        AnswerLabel.setText("Answer");
        RegisterPanel.add(AnswerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 440, -1, -1));

        AnswerField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnswerFieldActionPerformed(evt);
            }
        });
        RegisterPanel.add(AnswerField, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 429, 158, -1));

        SignUpButton.setBackground(new java.awt.Color(102, 102, 102));
        SignUpButton.setFont(new java.awt.Font("Charlemagne Std", 1, 12)); // NOI18N
        SignUpButton.setForeground(new java.awt.Color(255, 255, 255));
        SignUpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/folder/musket.png"))); // NOI18N
        SignUpButton.setText("SIGN UP");
        SignUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpButtonActionPerformed(evt);
            }
        });
        RegisterPanel.add(SignUpButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, 136, 54));

        PasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordFieldActionPerformed(evt);
            }
        });
        RegisterPanel.add(PasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 510, -1));
        RegisterPanel.add(ConfirmPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 510, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RegisterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RegisterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameFieldActionPerformed

    private void AnswerFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnswerFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnswerFieldActionPerformed

     /**
     * This method will process all the field's data and call the Authentication class to proceed.
     */
    
    private void SignUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpButtonActionPerformed
            // TODO add your handling code here:
         
         name = NameField.getText();  
         user = LoginField.getText(); //received username
         pass = PasswordField.getText(); //received password
         email = EmailField.getText();
         question = QuestionCombo.getSelectedItem().toString();
         answer = AnswerField.getText();
         
         if ( !handler.validate(name,user) || !handler.validate(pass,pass) || !handler.validate(question,answer) ) // if any argument is not valid
         {
             JOptionPane.showMessageDialog(null,"Invalid Details! Every field must contain more than 3 characters!");
         }
         else if (!handler.emailcheck(email))
         {
             JOptionPane.showMessageDialog(null,"Invalid email! Please fill in a valid one!");
         }
         else if (!pass.equals(ConfirmPasswordField.getText()))
         {
             JOptionPane.showMessageDialog(null,"Passwords don't match! Please fill the same password on both fields!");
         }
         else 
         {
             handler_return = handler.register(name,user,pass,email,question,answer); //checking if login is successful
                        if(handler_return == 1)
                        {
                            JOptionPane.showMessageDialog(null,"Registration successful! Username: " + user);
                            mainFirst.setVisible(true);
                            setVisible(false);
                            dispose();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Error creating user in database! Try again!");
                            mainFirst.setVisible(true);
                            setVisible(false);
                            dispose();
                        }

         } 
    }//GEN-LAST:event_SignUpButtonActionPerformed

    private void EmailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailFieldActionPerformed

    private void LoginFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LoginFieldActionPerformed

    private void QuestionComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuestionComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QuestionComboActionPerformed

    private void PasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordFieldActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AnswerField;
    private javax.swing.JLabel AnswerLabel;
    private javax.swing.JPasswordField ConfirmPasswordField;
    private javax.swing.JLabel ConfirmPasswordLabel;
    private javax.swing.JTextField EmailField;
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JTextField LoginField;
    private javax.swing.JLabel LoginLabel;
    private javax.swing.JTextField NameField;
    private javax.swing.JLabel NameLabel;
    private javax.swing.JLabel PassWordRecoveryText;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JComboBox<String> QuestionCombo;
    private javax.swing.JPanel RegisterPanel;
    private javax.swing.JButton SignUpButton;
    // End of variables declaration//GEN-END:variables
}
