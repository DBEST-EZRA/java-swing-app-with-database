package test3;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class AddItem extends JFrame{
	private static final long serialVersionUID = 1L;
//  private JPanel contentPane;
	private JTextField itemName;
	private JTextField itemPrice;
	private JButton btnNewButton;
	
	public AddItem() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADMIN\\Desktop\\sdm.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 150, 800, 500);
        setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblName = new JLabel("Item name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setBounds(58, 50, 99, 43);
        contentPane.add(lblName);
        
        itemName = new JTextField();
        itemName.setFont(new Font("Tahoma", Font.PLAIN, 22));
        itemName.setBounds(214, 50, 228, 50);
        contentPane.add(itemName);
        itemName.setColumns(10);
        
        JLabel lblPrice = new JLabel("Item price");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPrice.setBounds(58, 100, 99, 43);
        contentPane.add(lblPrice);
        
        itemPrice = new JTextField();
        itemPrice.setFont(new Font("Tahoma", Font.PLAIN, 22));
        itemPrice.setBounds(214, 100, 228, 50);
        contentPane.add(itemPrice);
        itemPrice.setColumns(10);
        
        btnNewButton = new JButton("ADD ITEM");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton.setBackground(Color.BLUE);
        btnNewButton.setForeground(Color.white);
        btnNewButton.setBounds(214, 300, 259, 74);
        contentPane.add(btnNewButton);
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String itemname = itemName.getText();
                int itemprice = Integer.parseInt(itemPrice.getText());
                
                try {
                  	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "");
                      PreparedStatement statement3 = connection.prepareStatement("INSERT INTO items (itemName, price) VALUES (?, ?)");
                      statement3.setString(1, itemname);
                      statement3.setInt(2, itemprice);
                      statement3.executeUpdate();
                      statement3.close();
                      connection.close();
                      JOptionPane.showMessageDialog(contentPane, "Item added successfully!");
                      itemName.setText("");
                      itemPrice.setText("");
              	}catch(SQLException f){
              		f.printStackTrace();
              		JOptionPane.showMessageDialog(contentPane, "Failed to add Item!");
              	}
            	
            }
        });
        
        
	}
}
