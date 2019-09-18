
/*
 * MainPage
 * Tabs for the administrator to use, student list, instrument list, sign in and out tab
 * @Authors: Daniel
 * Date: November 28, 2017
 */

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainPage {

 private JFrame frame;
 private JTextField textField;
 private JPasswordField passwordField;

 /**
  * Launch the application.
  */
 public static void main(String[] args) {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     MainPage window = new MainPage();
     window.frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace(); 
    }
   }
  });
 }

 /**
  * Create the application.
  */
 
 
 public MainPage() {
  initialize();
 }

 /**
  * Initialize the contents of the frame.
  */
 
 
 
 private void initialize() {
  frame = new JFrame();
  frame.setBounds(0, 0, 754, 478);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.getContentPane().setLayout(null);
  
  JLabel lblMenu = new JLabel("Welcome to MusicSignoutPro");
  lblMenu.setBounds(111, 24, 599, 99);
  lblMenu.setFont(new Font("Sitka Small", Font.PLAIN, 34));
  lblMenu.setForeground(Color.RED);
  frame.getContentPane().add(lblMenu);
  
  JButton btnViewInfo = new JButton("Login");
  btnViewInfo.setBackground(Color.GREEN);
  btnViewInfo.setFont(new Font("SansSerif", Font.BOLD, 14));
  btnViewInfo.setBounds(313, 185, 121, 41);
  btnViewInfo.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    String adminId = textField.getText();
    @SuppressWarnings("deprecation")
    String adminPassword = passwordField.getText();
    
    if ( (adminId.equals("christopoulos") || (adminId.equals("admin"))) && (adminPassword.equals("p"))){
     frame.setVisible(false);
     AdminGuiTabs.getInstance().openInfoPage();
    } else {
    }
   }
  });
  frame.getContentPane().add(btnViewInfo);
  
  JLabel lblUsername = new JLabel("Username:");
  lblUsername.setFont(new Font("SansSerif", Font.BOLD, 17));
  lblUsername.setBounds(144, 287, 121, 23);
  frame.getContentPane().add(lblUsername);
  
  JLabel lblPassword = new JLabel("Password:");
  lblPassword.setFont(new Font("SansSerif", Font.BOLD, 17));
  lblPassword.setBounds(144, 335, 138, 33);
  frame.getContentPane().add(lblPassword);
  
  textField = new JTextField();
  textField.setBounds(275, 291, 211, 20);
  frame.getContentPane().add(textField);
  textField.setColumns(10);
  
  passwordField = new JPasswordField();
  passwordField.setBounds(275, 344, 211, 20);
  frame.getContentPane().add(passwordField);
  
  JLabel lblNewLabel = new JLabel("");
  lblNewLabel.setIcon(new ImageIcon("musicSignoutsScreen.png"));
  lblNewLabel.setBounds(0, -77, 924, 599);
  frame.getContentPane().add(lblNewLabel);
  
  
 }
}
