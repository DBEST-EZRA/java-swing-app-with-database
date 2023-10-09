package test3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AdminPage extends JFrame{
  private static final long serialVersionUID = 1L;
//  private JPanel contentPane;
  private JTextField firstname;
  private JTextField lastname;
  private JTextField email;
  private JTextField username;
  private JTextField mob;
  private JPasswordField passwordField;
  private JButton btnNewButton;
  private JButton btnLogin;
  
  public AdminPage() {
	  setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADMIN\\Desktop\\sdm.jpg"));
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(300, 110, 1014, 597);
      setResizable(false);
      JPanel contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblNewUserRegister = new JLabel("ADMIN DASHBOARD");
      lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 22));
      lblNewUserRegister.setBounds(362, 12, 325, 50);
      contentPane.add(lblNewUserRegister);
      
      JButton registeredUsers = new JButton("View Registered Users");
      registeredUsers.setFont(new Font("Tahoma", Font.PLAIN, 12));
      registeredUsers.setForeground(Color.white);
      registeredUsers.setBackground(Color.blue);
      registeredUsers.setBounds(58, 80, 200, 43);
      contentPane.add(registeredUsers);
      
      JButton addedItems = new JButton("View Added Items");
      addedItems.setFont(new Font("Tahoma", Font.PLAIN, 12));
      addedItems.setForeground(Color.white);
      addedItems.setBackground(Color.blue);
      addedItems.setBounds(58, 130, 200, 43);
      contentPane.add(addedItems);
      
      JButton approveItems = new JButton("Approve Item");
      approveItems.setFont(new Font("Tahoma", Font.PLAIN, 12));
      approveItems.setForeground(Color.white);
      approveItems.setBackground(Color.blue);
      approveItems.setBounds(58, 180, 200, 43);
      contentPane.add(approveItems);
      
      JButton deleteItems = new JButton("Delete Item");
      deleteItems.setFont(new Font("Tahoma", Font.PLAIN, 12));
      deleteItems.setForeground(Color.white);
      deleteItems.setBackground(Color.blue);
      deleteItems.setBounds(58, 230, 200, 43);
      contentPane.add(deleteItems);
      
      JButton addItems = new JButton("Add Items");
      addItems.setFont(new Font("Tahoma", Font.PLAIN, 12));
      addItems.setForeground(Color.white);
      addItems.setBackground(Color.blue);
      addItems.setBounds(58, 280, 200, 43);
      contentPane.add(addItems);
      
      JButton changeColor = new JButton("Change Color");
      changeColor.setFont(new Font("Tahoma", Font.PLAIN, 12));
      changeColor.setForeground(Color.white);
      changeColor.setBackground(Color.blue);
      changeColor.setBounds(58, 330, 200, 43);
      contentPane.add(changeColor);
      
      DefaultListModel<CustomItem> listModel = new DefaultListModel<>();
      JList<CustomItem> stripedList = new JList<>(listModel);
      stripedList.setFixedCellHeight(50);
      stripedList.setFixedCellWidth(250);
      
      
      stripedList.setCellRenderer(new DefaultListCellRenderer() {
    	  public Component getListCellRendererComponent(JList<?> list, Object value, int index,
    			  boolean isSelected, boolean cellHasFocus) {
    		  Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    		  
    		  if(index % 2 == 0) {
    			  renderer.setBackground(Color.WHITE);
    			  renderer.setForeground(Color.BLUE);
    		  }else {
    			  renderer.setBackground(Color.LIGHT_GRAY);
    			  renderer.setForeground(Color.black);
    		  }
    		  
    		  return renderer;    		  
    	  }
      });
      
      addedItems.addActionListener(new ActionListener() {
    	  @Override
          public void actionPerformed(ActionEvent e) {
    		  listModel.clear();
    		  
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
    		      	JOptionPane.showMessageDialog(contentPane, "Items fetched successfully!");
    		      }catch(SQLException g) {
    		      	g.printStackTrace();
    		      	JOptionPane.showMessageDialog(contentPane, "Failed to fetch items from the database!");
    		      }
    		        		
      	}
      });
      
      
      addItems.addActionListener(new ActionListener() {
    	  @Override
          public void actionPerformed(ActionEvent e) {
    		  AddItem addItem = new AddItem();
              addItem.setVisible(true);
      	}
      });
      
      changeColor.addActionListener(new ActionListener() {
    	  @Override
          public void actionPerformed(ActionEvent e) {
    		  Color newColor = getRandomColor();
    		  changeColor.setBackground(newColor);
    		  addItems.setBackground(newColor);
    		  deleteItems.setBackground(newColor);
    		  approveItems.setBackground(newColor);
    		  addedItems.setBackground(newColor);
    		  registeredUsers.setBackground(newColor);
      	}
      });
      
      registeredUsers.addActionListener(new ActionListener() {
    	  @Override
          public void actionPerformed(ActionEvent e) {
    		  listModel.clear();
    		  
    		  try {
    		      	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "");
    		      	Statement statement = connection.createStatement();
    		      	ResultSet resultSet = statement.executeQuery("SELECT first_name, last_name, email_id, mobile_number FROM account");
    		      	
    		      	while(resultSet.next()) {
    		      		String first_name = resultSet.getString("first_name");
    		      		String last_name = resultSet.getString("last_name");
    		      		String email_id = resultSet.getString("email_id");
    		      		String mobile_number = resultSet.getString("mobile_number");
    		      		CustomItem user = new CustomItem(first_name, last_name, email_id, mobile_number);
    		      		listModel.addElement(user);
    		      	}
    		      	resultSet.close();
    		      	statement.close();
    		      	connection.close();
    		      	JOptionPane.showMessageDialog(contentPane, "Users data fetched successfully!");
    		      }catch(SQLException g) {
    		      	g.printStackTrace();
    		      	JOptionPane.showMessageDialog(contentPane, "Failed to fetch users from the database!");
    		      }
    		        		
      	}
      });
      
      
      deleteItems.addActionListener(new ActionListener() {
      	@Override
      	public void actionPerformed(ActionEvent e) {
      		CustomItem selectedItem = stripedList.getSelectedValue();
      		if(selectedItem != null) {
      			try {
                  	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "");
                      PreparedStatement statement2 = connection.prepareStatement("DELETE FROM items WHERE itemName = ?");
                      statement2.setString(1, selectedItem.getItemName());
                      statement2.executeUpdate();
                      statement2.close();
                      connection.close();
                      JOptionPane.showMessageDialog(contentPane, "Item deleted successfully!");
              	}catch(SQLException f){
              		f.printStackTrace();
              		JOptionPane.showMessageDialog(contentPane, "Cannot Delete Item!");
              	}
      			listModel.removeElement(selectedItem);
      		}
      	}
      });
      
      
      approveItems.addActionListener(new ActionListener() {
      	@Override
      	public void actionPerformed(ActionEvent e) {
      		CustomItem selectedItem = stripedList.getSelectedValue();
      		if(selectedItem != null) {
      			try {
                  	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "");
                      PreparedStatement statement3 = connection.prepareStatement("INSERT INTO approved_items (itemName, price) VALUES (?, ?)");
                      statement3.setString(1, selectedItem.getItemName());
                      statement3.setLong(2, selectedItem.getPrice());
                      statement3.executeUpdate();
                      statement3.close();
                      connection.close();
                      JOptionPane.showMessageDialog(contentPane, "Item approved successfully!");
              	}catch(SQLException f){
              		f.printStackTrace();
              		JOptionPane.showMessageDialog(contentPane, "Failed to approve Item!");
              	}
      		}
      	}
      });
      
      
      
      stripedList.setBounds(300, 80, 600, 430);
      contentPane.add(stripedList);
  }
  
  private Color getRandomColor() {
	  int r = (int)(Math.random() * 256);
	  int g = (int)(Math.random() * 256);
	  int b = (int)(Math.random() * 256);
	  
	  return new Color(r, g, b);
  }

}
