/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;


public class ChatUI extends javax.swing.JPanel {

    
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 3217;
    Boolean isConnected = false;
    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    public ChatUI(String username) {
        initComponents();
        this.username = username;
        this.setSize(498,119);
        ta_chat.setEditable(false);
        ta_chat.append("Chat Started.. \n");
        Connect();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        tf_chat = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 51));
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(504, 114));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 484, 58));

        tf_chat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_chatKeyPressed(evt);
            }
        });
        add(tf_chat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 352, 23));

        b_send.setText("SEND");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });
        add(b_send, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 80, 126, -1));
    }// </editor-fold>//GEN-END:initComponents

    
    
    
     public void Connect() {
            try 
            {
                //method get username
                sock = new Socket(address, port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
                
            }
            
            ListenThread();
            
        } 
     
     public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
                        ta_chat.append(data[0] + ": " + data[1] + "\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                     } 
                    
                }
           }catch(Exception ex) { }
        }
    }
    
     public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new ChatUI.IncomingReader());
         IncomingReader.start();
    }
     
    private void tf_chatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_chatKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)  {
            //System.out.println("ENTER!");
            String nothing = "";
            if ((tf_chat.getText()).equals(nothing)) {
                tf_chat.setText("");
                tf_chat.requestFocus();
            } else {
                try {
                    writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
                    writer.flush(); // flushes the buffer
                } catch (Exception ex) {
                    ta_chat.append("Message was not sent. \n");
                }
                tf_chat.setText("");
                tf_chat.requestFocus();
            }

            tf_chat.setText("");
            tf_chat.requestFocus();
        }

    }//GEN-LAST:event_tf_chatKeyPressed

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
                writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
                writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                ta_chat.append("Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }//GEN-LAST:event_b_sendActionPerformed

public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                JFrame frame = new JFrame();
                
                frame.add(new ChatUI("nabo"));
                frame.setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_send;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_chat;
    // End of variables declaration//GEN-END:variables
}