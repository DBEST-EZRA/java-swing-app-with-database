package test3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Main {
	private JFrame frame;
    
    public Main(){
    	frame = new JFrame("Signup and Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(6, 1));
      

        JButton signupButton = new JButton("User Registration & login");
        JButton loginButton = new JButton("Admin Login");

        frame.add(signupButton);
        frame.add(loginButton);

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserDashboard register = new UserDashboard();
                register.setVisible(true);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                                
                EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					AdminLogin adminLogin = new AdminLogin();
//        					window.setVisible(true);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});

            }
        });

        frame.setVisible(true);
        
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });


	}

}
