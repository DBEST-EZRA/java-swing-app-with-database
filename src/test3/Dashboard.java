/**
 * 
 */
package test3;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Dashboard {
	private JFrame frame1;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private Connection connection;

	public Dashboard() {
		frame1 = new JFrame("Admin Dashboard");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setSize(600, 400);
        frame1.setLayout(new GridLayout(2,4));
        
        JLabel itemLabel = new JLabel("Item Name:");
        JTextField itemNameField = new JTextField();
        JLabel priceLabel = new JLabel("Item Price:");
        JTextField itemPriceField = new JTextField();
        JButton addItemButton = new JButton("Add Item");
        JButton deleteButton = new JButton("Delete Selected Item");
        JButton approveButton = new JButton("Approve Selected Item");
        addItemButton.setBackground(Color.BLUE);
        addItemButton.setForeground(Color.white);
        deleteButton.setBackground(Color.red);
        deleteButton.setForeground(Color.white);
        approveButton.setBackground(Color.green);
        approveButton.setForeground(Color.white);
        addItemButton.setPreferredSize(new Dimension(150,50));
        DefaultListModel<CustomItem> listModel = new DefaultListModel<>();
        JList<CustomItem> jlist = new JList<>(listModel);
        jlist.setFixedCellHeight(30);
        jlist.setFixedCellWidth(200);
        
        
        
        try {
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "");
        	Statement statement = connection.createStatement();
        	ResultSet resultSet = statement.executeQuery("SELECT itemName, price FROM items");
        	
        	while(resultSet.next()) {
        		String itemName = resultSet.getString("itemName");
        		int price = resultSet.getInt("price");
        		CustomItem item = new CustomItem(itemName, price);
        		listModel.addElement(item);
        	}
        	resultSet.close();
        	statement.close();
        	connection.close();
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        
             
        
        
        approveButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		CustomItem selectedItem = jlist.getSelectedValue();
        		if(selectedItem != null) {
        			try {
                    	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "");
                        PreparedStatement statement3 = connection.prepareStatement("INSERT INTO approved_items (itemName, price) VALUES (?, ?)");
                        statement3.setString(1, selectedItem.getItemName());
                        statement3.setLong(2, selectedItem.getPrice());
                        statement3.executeUpdate();
                        statement3.close();
                        connection.close();
                        JOptionPane.showMessageDialog(frame1, "Item approved successfully!");
                	}catch(SQLException f){
                		f.printStackTrace();
                		JOptionPane.showMessageDialog(frame1, "Failed to approve Item!");
                	}
        		}
        	}
        });
        
                
        
        JScrollPane scrollPane = new JScrollPane(jlist);
        
        frame1.add(itemLabel);
        frame1.add(itemNameField);
        frame1.add(priceLabel);
        frame1.add(itemPriceField);
        frame1.add(addItemButton);
        frame1.add(deleteButton);
        frame1.add(approveButton);
        frame1.add(scrollPane, BorderLayout.CENTER);
        frame1.pack();
        frame1.setVisible(true);
	}

}
