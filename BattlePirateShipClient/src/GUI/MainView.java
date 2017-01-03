
package GUI;

import BusinessLogic.Game;
import BusinessLogic.User;
import Communications.SocketClient;
  /** 
     *  This class contains the interface for the Main Menu where the player can see his stats and decide to start or watch a game.
     */

public class MainView extends javax.swing.JFrame {
    private final SocketClient client;
    private final Game gamehandler;
    private final User userinfo;
    private int gameid;
    private int userid;
    
    public MainView(int userid, SocketClient client) {
        initComponents();
        this.userid = userid;
        this.client = client;
        gamehandler = new Game(client);
        userinfo = new User(client);
        fillPlayerInfo();
        
        
    }
    
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
            java.util.logging.Logger.getLogger(First.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(First.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(First.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(First.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView(1,null).setVisible(true);
            }
        });
    }

    private int fillPlayerInfo()
    {
         String[] info = userinfo.get(userid);
         if(info == null)
         {
             return -1;
         }
         //userinfo.getFriends(userid);
         
         UsernameText.setText(info[2]);
         UsernameText.setEditable(false);
         GamesPlayedText.setText(info[5]);
         WinsText.setText(info[6]);
         DefeatsText.setText(info[7]);
         SurrendersText.setText(info[8]);
         RankingText.setText(info[9]);
         PlayerID.setText(""+userid);
       return 1;    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        WatchGameButton = new javax.swing.JButton();
        QuickGameButton = new javax.swing.JButton();
        PlayWithFriendButton = new javax.swing.JButton();
        LogoutButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        IDLabel = new javax.swing.JLabel();
        Ranking = new javax.swing.JLabel();
        RankingText = new javax.swing.JButton();
        Surrenders = new javax.swing.JLabel();
        Defeats = new javax.swing.JLabel();
        Wins = new javax.swing.JLabel();
        GamesPlayed = new javax.swing.JLabel();
        SurrendersText = new javax.swing.JButton();
        DefeatsText = new javax.swing.JButton();
        WinsText = new javax.swing.JButton();
        GamesPlayedText = new javax.swing.JButton();
        Username = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        UsernameText = new javax.swing.JTextPane();
        PlayerID = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 6));

        WatchGameButton.setBackground(new java.awt.Color(102, 102, 102));
        WatchGameButton.setFont(new java.awt.Font("Charlemagne Std", 1, 15)); // NOI18N
        WatchGameButton.setForeground(new java.awt.Color(255, 255, 255));
        WatchGameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/folder/spyglass.png"))); // NOI18N
        WatchGameButton.setText("Watch a game");
        WatchGameButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        WatchGameButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        WatchGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WatchGameButtonActionPerformed(evt);
            }
        });

        QuickGameButton.setBackground(new java.awt.Color(102, 102, 102));
        QuickGameButton.setFont(new java.awt.Font("Charlemagne Std", 1, 18)); // NOI18N
        QuickGameButton.setForeground(new java.awt.Color(255, 255, 255));
        QuickGameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/folder/saber-2.png"))); // NOI18N
        QuickGameButton.setText("Quick Game");
        QuickGameButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        QuickGameButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        QuickGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuickGameButtonActionPerformed(evt);
            }
        });

        PlayWithFriendButton.setBackground(new java.awt.Color(102, 102, 102));
        PlayWithFriendButton.setFont(new java.awt.Font("Charlemagne Std", 1, 18)); // NOI18N
        PlayWithFriendButton.setForeground(new java.awt.Color(255, 255, 255));
        PlayWithFriendButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/folder/pirate-captain-2.png"))); // NOI18N
        PlayWithFriendButton.setText("Play With a Friend");
        PlayWithFriendButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        PlayWithFriendButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        PlayWithFriendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayWithFriendButtonActionPerformed(evt);
            }
        });

        LogoutButton.setBackground(new java.awt.Color(102, 102, 102));
        LogoutButton.setFont(new java.awt.Font("Charlemagne Std", 0, 10)); // NOI18N
        LogoutButton.setForeground(new java.awt.Color(255, 255, 255));
        LogoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/folder/key.png"))); // NOI18N
        LogoutButton.setText("Logout");
        LogoutButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Games Played", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Charlemagne Std", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Date", "Opponent", "Name"
            }
        ));
        jTable3.setGridColor(new java.awt.Color(51, 51, 51));
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Charlemagne Std", 0, 18)); // NOI18N
        jButton2.setText("AVATAR");
        jButton2.setToolTipText("");

        IDLabel.setBackground(new java.awt.Color(51, 51, 51));
        IDLabel.setForeground(new java.awt.Color(204, 204, 0));
        IDLabel.setText("userID #");

        Ranking.setFont(new java.awt.Font("Charlemagne Std", 0, 12)); // NOI18N
        Ranking.setForeground(new java.awt.Color(255, 255, 255));
        Ranking.setText("RANking");

        RankingText.setText("10");
        RankingText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RankingTextActionPerformed(evt);
            }
        });

        Surrenders.setFont(new java.awt.Font("Charlemagne Std", 0, 12)); // NOI18N
        Surrenders.setForeground(new java.awt.Color(255, 255, 255));
        Surrenders.setText("Surrenders");

        Defeats.setFont(new java.awt.Font("Charlemagne Std", 0, 12)); // NOI18N
        Defeats.setForeground(new java.awt.Color(255, 255, 255));
        Defeats.setText("Defeats");

        Wins.setFont(new java.awt.Font("Charlemagne Std", 0, 12)); // NOI18N
        Wins.setForeground(new java.awt.Color(255, 255, 255));
        Wins.setText("Wins");

        GamesPlayed.setFont(new java.awt.Font("Charlemagne Std", 0, 12)); // NOI18N
        GamesPlayed.setForeground(new java.awt.Color(255, 255, 255));
        GamesPlayed.setText("Games played");

        SurrendersText.setText("0");

        DefeatsText.setText("2");
        DefeatsText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DefeatsTextActionPerformed(evt);
            }
        });

        WinsText.setText("3");

        GamesPlayedText.setText("5");

        Username.setFont(new java.awt.Font("Charlemagne Std", 0, 12)); // NOI18N
        Username.setForeground(new java.awt.Color(255, 255, 255));
        Username.setText("UserName");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton3.setText("Advertisement");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(UsernameText);

        PlayerID.setBackground(new java.awt.Color(51, 51, 51));
        PlayerID.setForeground(new java.awt.Color(204, 204, 0));
        PlayerID.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Wins)
                                    .addComponent(GamesPlayed)
                                    .addComponent(Defeats)
                                    .addComponent(Surrenders)
                                    .addComponent(Ranking))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(GamesPlayedText, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(RankingText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(SurrendersText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(WinsText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DefeatsText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(Username)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(IDLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PlayerID, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(QuickGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PlayWithFriendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(WatchGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(QuickGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PlayWithFriendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(WatchGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(Username)
                                        .addGap(16, 16, 16))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(GamesPlayed)
                                    .addComponent(GamesPlayedText))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Wins)
                                    .addComponent(WinsText))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Defeats)
                                    .addComponent(DefeatsText))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Surrenders)
                                    .addComponent(SurrendersText))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(RankingText)
                                    .addComponent(Ranking)))
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(IDLabel)
                            .addComponent(PlayerID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/folder/pirate-head-2.png"))); // NOI18N

        jMenuItem1.setText("Friends");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/folder/edit.png"))); // NOI18N
        jMenu2.setText("Edit");

        jMenuItem3.setText("Options");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem6.setText("Avatar");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Password");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/folder/information.png"))); // NOI18N
        jMenu3.setText("About the game");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem4.setText("How to Play");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Credits");
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        
        Preferences op=new Preferences();
        op.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        
        FriendsList p= new FriendsList();
        //this.jPanel1.add(p);
        p.show(true);
        //p.setVisible(true);
        
      
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void DefeatsTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DefeatsTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DefeatsTextActionPerformed

    private void RankingTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RankingTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RankingTextActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        ChangeAvatar c=new ChangeAvatar();
        c.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        ChangePassword p=new ChangePassword();
        p.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
        
        HowToPlay how2=new HowToPlay();
        how2.setVisible(true);
    }//GEN-LAST:event_jMenu3ActionPerformed

    /** 
     *  This method will request for a quickgame, using Game class on Business Logic.
     */
    
    private void QuickGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuickGameButtonActionPerformed
        // TODO add your handling code here:
        
        int[] args = new int[2];
        args = gamehandler.quickgame(userid);
        InGame game= new InGame(this,args[0],userid,this.client,args[1]);
        game.setVisible(true);
    }//GEN-LAST:event_QuickGameButtonActionPerformed

    /** 
     *  This method will request to play with a friend, using Game class on Business Logic
     */
    
    private void PlayWithFriendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayWithFriendButtonActionPerformed
        // TODO add your handling code here:
        PlayWithFriend play = new PlayWithFriend();
        play.setVisible(true);
    }//GEN-LAST:event_PlayWithFriendButtonActionPerformed

      /** 
     *  This method will request to watch a game, using Game class on Business Logic
     */
    
    private void WatchGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WatchGameButtonActionPerformed
        // TODO add your handling code here:
        WatchGame play = new WatchGame();
        play.setVisible(true);
    }//GEN-LAST:event_WatchGameButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

      /** 
     *  This method requests to logout the user.
     */
    
    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_LogoutButtonActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Defeats;
    private javax.swing.JButton DefeatsText;
    private javax.swing.JLabel GamesPlayed;
    private javax.swing.JButton GamesPlayedText;
    private javax.swing.JLabel IDLabel;
    private javax.swing.JButton LogoutButton;
    private javax.swing.JButton PlayWithFriendButton;
    private javax.swing.JLabel PlayerID;
    private javax.swing.JButton QuickGameButton;
    private javax.swing.JLabel Ranking;
    private javax.swing.JButton RankingText;
    private javax.swing.JLabel Surrenders;
    private javax.swing.JButton SurrendersText;
    private javax.swing.JLabel Username;
    private javax.swing.JTextPane UsernameText;
    private javax.swing.JButton WatchGameButton;
    private javax.swing.JLabel Wins;
    private javax.swing.JButton WinsText;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
