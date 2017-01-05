
package GUI;

import BusinessLogic.User;
import Communications.SocketClient;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

/**
 * This class contains the Ingame Window, that shows player's information and both player's boards. It allows the player to play the game.
 */
public class InGame extends javax.swing.JFrame {

    private final MainView main;
    private final SocketClient client;
    private final int gameid;
    private Grid playergrid;
    private Grid enemygrid;
    private Thread pthread;
    private Thread ethread;
    private Timer gametimer;
    private ActionListener timerlistener;
    private WindowListener exitListener;
    private int gametime;
    private ChatUI chat;
    private User userinfo;
    
    public InGame(MainView main, int gameid, int userid, SocketClient client, int startplayer) 
    {
     /**
     * Creates new form InGame
     * @param gameid receives the gameid that was given when creating or joining a game
     */
        initComponents();

        playergrid = new Grid("player",gameid,userid,client,startplayer);
        enemygrid = new Grid("enemy",gameid,userid,client,startplayer);
        gridPlayer.add(playergrid);
        gridEnemy.add(enemygrid);
        
        userinfo = new User(client);
        String info[] =  userinfo.get(userid);
        PlayerUsername.setText(info[3]);
        RankText.setText(info[9]);
        GameIDnum.setText(""+gameid);
        chat = new ChatUI(info[3]);
        ChatPanel.add(chat);
        gametime = 0;
        this.client = client;
        this.main = main;
        this.gameid = gameid;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        exitListener = new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) 
        {
            leavegame();
        }
        };
        this.addWindowListener(exitListener);
        
        timerlistener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                gametime += 1;
                MinutesLabel.setText(((gametime/60) < 10 ? "0" : "") + gametime/60);  
                SecondsLabel.setText(((gametime%60) < 10 ? "0" : "") + gametime%60);
            }
        };
        
        gametimer = new Timer(1000,timerlistener);
        pthread = new Thread(new Runnable() 
        {
            private MainView main;
            private InGame gui;
                   
            public Runnable init(MainView main, InGame gui) 
            {
                this.main = main;
                this.gui = gui;
                return this;
            }
            
            public void run()
            {
                StatusMsg.setText("Please place your ships...");
                playergrid.placePlayerShips();
                OrientationButton.setVisible(false);
                // playergrid.sendPositions();
                StatusMsg.setText("Ships Placed! Waiting for other player...");
                playergrid.sendBoard();
                StatusMsg.setText("Players Ready!...");
                gametimer.start();
                playergrid.receiveShots(StatusMsg);
                StatusMsg.setText("Game finished...");

                if(playergrid.Winner)
                {   
                    JOptionPane.showMessageDialog(null,"You Win!");
                }
                else
                {   
                    JOptionPane.showMessageDialog(null,"You Lose!");
                }
                this.main.setVisible(true);
                this.gui.setVisible(false);
                this.gui.dispose();
            }
         }.init(this.main,this));
        
        ethread = new Thread(new Runnable() 
        {
           private MainView main;
           private InGame gui;
                   
            public Runnable init(MainView main, InGame gui) 
            {
                this.main = main;
                this.gui = gui;
                return this;
            }
            
            public void run()
            {  
               while(!enemygrid.playersReady)
                {
                    try 
                    {
                        Thread.sleep(50); // for 100 FPS
                    } 
                    catch (InterruptedException ignore) 
                    {
                    }
                }
               enemygrid.doShots(StatusMsg);
               gametimer.stop();             
           }
        }.init(this.main,this));
        pthread.start();
        ethread.start();
       
    }

  /** public static void main(String args[]) {
  
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
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InGame(new MainView(),1,1,null,1).setVisible(true);
            }
        });
    }*/

    private void leavegame()
    {
        Object[] options = {"Yes, please","No, thanks"};
            
        int n = JOptionPane.showOptionDialog(this,"Are you sure you want to surrender?", "Leaving game..",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[1]);
        if(n == JOptionPane.CLOSED_OPTION || n == 1)
        { 
        }
        else
        {
             //game.surrender();
             this.main.setVisible(true);
             this.dispose();
    
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jButton267 = new javax.swing.JButton();
        jButton264 = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jButton268 = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        TimerPanel = new javax.swing.JPanel();
        MinutesLabel = new javax.swing.JLabel();
        SecondsLabel = new javax.swing.JLabel();
        MinutesLabel2 = new javax.swing.JLabel();
        jButton222 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jButton137 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jButton269 = new javax.swing.JButton();
        jButton270 = new javax.swing.JButton();
        PlayerUsername = new javax.swing.JLabel();
        RankText = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        SurrenderButton = new javax.swing.JButton();
        ChatLabel = new javax.swing.JPanel();
        ChatPanel = new javax.swing.JPanel();
        jButton223 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        gridEnemy = new javax.swing.JPanel();
        gridPlayer = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        StatusMsg = new javax.swing.JLabel();
        OrientationButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        GameIDnum = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "PIRATE2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Charlemagne Std", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Avatar");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel43.setFont(new java.awt.Font("Charlemagne Std", 0, 11)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(153, 153, 0));
        jLabel43.setText("HIts");

        jButton267.setText("#ID201605017");
        jButton267.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jButton267.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton267ActionPerformed(evt);
            }
        });

        jButton264.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton264.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton264ActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Charlemagne Std", 0, 11)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(153, 153, 0));
        jLabel46.setText("Login name");

        jButton268.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton268.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton268ActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Charlemagne Std", 0, 11)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(153, 153, 0));
        jLabel47.setText("Rank");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButton268, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButton264, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jButton267, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton268, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jButton264, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton267, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 10, 180, 310));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(514, 11, 10, 370));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 1085, -1));

        TimerPanel.setBackground(new java.awt.Color(102, 102, 102));
        TimerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Play time", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Charlemagne Std", 0, 10))); // NOI18N

        MinutesLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        MinutesLabel.setText("00");

        SecondsLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        SecondsLabel.setText("00");

        MinutesLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        MinutesLabel2.setText(" :");

        javax.swing.GroupLayout TimerPanelLayout = new javax.swing.GroupLayout(TimerPanel);
        TimerPanel.setLayout(TimerPanelLayout);
        TimerPanelLayout.setHorizontalGroup(
            TimerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimerPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(MinutesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MinutesLabel2)
                .addGap(18, 18, 18)
                .addComponent(SecondsLabel))
        );
        TimerPanelLayout.setVerticalGroup(
            TimerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TimerPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(TimerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SecondsLabel)
                    .addComponent(MinutesLabel)
                    .addComponent(MinutesLabel2))
                .addContainerGap())
        );

        jPanel1.add(TimerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 420, 160, 70));

        jButton222.setBackground(new java.awt.Color(102, 102, 102));
        jButton222.setFont(new java.awt.Font("Charlemagne Std", 0, 10)); // NOI18N
        jButton222.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/folder/avatar-2.png"))); // NOI18N
        jButton222.setText("Add as friend ");
        jButton222.setToolTipText("");
        jButton222.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jButton222, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 326, -1, 30));

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "PIRATE1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Charlemagne Std", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jButton137.setForeground(new java.awt.Color(255, 255, 255));
        jButton137.setText("Avatar");
        jButton137.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel48.setFont(new java.awt.Font("Charlemagne Std", 0, 11)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(153, 153, 0));
        jLabel48.setText("Hits");

        jButton269.setText("#ID201605017");
        jButton269.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jButton269.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton269ActionPerformed(evt);
            }
        });

        jButton270.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton270.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton270ActionPerformed(evt);
            }
        });

        PlayerUsername.setFont(new java.awt.Font("Charlemagne Std", 0, 11)); // NOI18N
        PlayerUsername.setForeground(new java.awt.Color(153, 153, 0));
        PlayerUsername.setText("USERname");

        RankText.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        RankText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RankTextActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Charlemagne Std", 0, 11)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(153, 153, 0));
        jLabel50.setText("Rank");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton269, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton270, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(RankText, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(PlayerUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton137, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jButton137, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PlayerUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(RankText, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton270, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton269, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, -1, 310));

        SurrenderButton.setBackground(new java.awt.Color(51, 51, 51));
        SurrenderButton.setFont(new java.awt.Font("Charlemagne Std", 0, 12)); // NOI18N
        SurrenderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/folder/gallows-2.png"))); // NOI18N
        SurrenderButton.setText("Surrender");
        SurrenderButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SurrenderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SurrenderButtonActionPerformed(evt);
            }
        });
        jPanel1.add(SurrenderButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 500, 160, 30));

        ChatLabel.setBackground(new java.awt.Color(51, 51, 51));
        ChatLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Charlemagne Std", 0, 10))); // NOI18N

        javax.swing.GroupLayout ChatPanelLayout = new javax.swing.GroupLayout(ChatPanel);
        ChatPanel.setLayout(ChatPanelLayout);
        ChatPanelLayout.setHorizontalGroup(
            ChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );
        ChatPanelLayout.setVerticalGroup(
            ChatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ChatLabelLayout = new javax.swing.GroupLayout(ChatLabel);
        ChatLabel.setLayout(ChatLabelLayout);
        ChatLabelLayout.setHorizontalGroup(
            ChatLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
            .addGroup(ChatLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ChatLabelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(ChatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        ChatLabelLayout.setVerticalGroup(
            ChatLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
            .addGroup(ChatLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ChatLabelLayout.createSequentialGroup()
                    .addComponent(ChatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel1.add(ChatLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 530, 150));
        ChatLabel.getAccessibleContext().setAccessibleDescription("");

        jButton223.setBackground(new java.awt.Color(51, 51, 51));
        jButton223.setFont(new java.awt.Font("Charlemagne Std", 0, 10)); // NOI18N
        jButton223.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/folder/replay-arrow.png"))); // NOI18N
        jButton223.setText("Replay ");
        jButton223.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jButton223, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 120, 30));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 25, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 25, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 25, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("4");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 25, -1));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("5");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 25, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("6");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 25, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("7");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 25, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("8");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 25, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("9");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 25, -1));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("10");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 25, -1));

        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("C");
        jPanel1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 10, 25));

        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("I");
        jPanel1.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 270, 10, 25));

        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("E");
        jPanel1.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 10, 25));

        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("F");
        jPanel1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 10, 25));

        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("H");
        jPanel1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 10, 25));

        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("D");
        jPanel1.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 10, 25));

        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("A");
        jPanel1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 10, 25));

        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("G");
        jPanel1.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 210, 10, 25));

        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("B");
        jPanel1.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, 10, 25));

        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("J");
        jPanel1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 10, 25));

        gridEnemy.setPreferredSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout gridEnemyLayout = new javax.swing.GroupLayout(gridEnemy);
        gridEnemy.setLayout(gridEnemyLayout);
        gridEnemyLayout.setHorizontalGroup(
            gridEnemyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        gridEnemyLayout.setVerticalGroup(
            gridEnemyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jPanel1.add(gridEnemy, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, -1, -1));

        gridPlayer.setPreferredSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout gridPlayerLayout = new javax.swing.GroupLayout(gridPlayer);
        gridPlayer.setLayout(gridPlayerLayout);
        gridPlayerLayout.setHorizontalGroup(
            gridPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        gridPlayerLayout.setVerticalGroup(
            gridPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jPanel1.add(gridPlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("1");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 25, -1));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("2");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 25, -1));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("3");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 25, -1));

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("4");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 25, -1));

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("5");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 25, -1));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("6");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 25, -1));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("7");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 25, -1));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("8");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, 25, -1));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("9");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 25, -1));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("10");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 25, -1));

        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("A");
        jPanel1.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 30, 10, 25));

        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("B");
        jPanel1.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 60, 10, 25));

        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("C");
        jPanel1.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 90, 10, 25));

        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("D");
        jPanel1.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 120, 10, 25));

        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("E");
        jPanel1.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 150, 10, 25));

        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("F");
        jPanel1.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 180, 10, 25));

        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("G");
        jPanel1.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 210, 10, 25));

        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("H");
        jPanel1.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 240, 10, 25));

        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("I");
        jPanel1.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 270, 10, 25));

        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("J");
        jPanel1.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 300, 10, 25));

        StatusMsg.setForeground(new java.awt.Color(255, 255, 255));
        StatusMsg.setText("Starting Game...");
        jPanel1.add(StatusMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 366, -1, 20));

        OrientationButton.setText("Change Orientation");
        OrientationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrientationButtonActionPerformed(evt);
            }
        });
        jPanel1.add(OrientationButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, -1, -1));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Who's Seeing", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Charlemagne Std", 0, 10))); // NOI18N

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Login Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setGridColor(new java.awt.Color(51, 51, 51));
        jScrollPane5.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 400, 360, 150));

        GameIDnum.setText("0");
        jPanel1.add(GameIDnum, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 50, -1));

        jLabel67.setFont(new java.awt.Font("Charlemagne Std", 0, 11)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(153, 153, 0));
        jLabel67.setText("sTATUS :");
        jPanel1.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 370, 60, 10));

        jLabel68.setFont(new java.awt.Font("Charlemagne Std", 0, 11)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(153, 153, 0));
        jLabel68.setText("GAMEID ");
        jPanel1.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton267ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton267ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton267ActionPerformed

    private void jButton264ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton264ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton264ActionPerformed

    private void jButton268ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton268ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton268ActionPerformed

    private void jButton269ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton269ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton269ActionPerformed

    private void jButton270ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton270ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton270ActionPerformed

    private void RankTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RankTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RankTextActionPerformed

    private void OrientationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrientationButtonActionPerformed
        playergrid.vertical = !playergrid.vertical;
    }//GEN-LAST:event_OrientationButtonActionPerformed

    private void SurrenderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SurrenderButtonActionPerformed
        // TODO add your handling code here:
        leavegame();
    }//GEN-LAST:event_SurrenderButtonActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ChatLabel;
    private javax.swing.JPanel ChatPanel;
    private javax.swing.JTextField GameIDnum;
    private javax.swing.JLabel MinutesLabel;
    private javax.swing.JLabel MinutesLabel2;
    private javax.swing.JButton OrientationButton;
    private javax.swing.JLabel PlayerUsername;
    private javax.swing.JButton RankText;
    private javax.swing.JLabel SecondsLabel;
    private javax.swing.JLabel StatusMsg;
    private javax.swing.JButton SurrenderButton;
    private javax.swing.JPanel TimerPanel;
    private javax.swing.JPanel gridEnemy;
    private javax.swing.JPanel gridPlayer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton137;
    private javax.swing.JButton jButton222;
    private javax.swing.JButton jButton223;
    private javax.swing.JButton jButton264;
    private javax.swing.JButton jButton267;
    private javax.swing.JButton jButton268;
    private javax.swing.JButton jButton269;
    private javax.swing.JButton jButton270;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
