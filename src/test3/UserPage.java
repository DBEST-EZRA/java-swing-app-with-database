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

public class UserPage extends JFrame{
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
	// Create a JFrame (window)
    public UserPage(){
    	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ADMIN\\Desktop\\sdm.jpg"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 110, 1014, 597);
        setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("USER DASHBOARD");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        lblNewUserRegister.setBounds(362, 12, 325, 50);
        contentPane.add(lblNewUserRegister);
        
               
        JButton addedItems = new JButton("View Added Items");
        addedItems.setFont(new Font("Tahoma", Font.PLAIN, 12));
        addedItems.setForeground(Color.white);
        addedItems.setBackground(Color.blue);
        addedItems.setBounds(58, 80, 200, 43);
        contentPane.add(addedItems);
             
        JButton addItems = new JButton("Add Items");
        addItems.setFont(new Font("Tahoma", Font.PLAIN, 12));
        addItems.setForeground(Color.white);
        addItems.setBackground(Color.blue);
        addItems.setBounds(58, 130, 200, 43);
        contentPane.add(addItems);
        
        JButton addToCart = new JButton("Add To Cart");
        addToCart.setFont(new Font("Tahoma", Font.PLAIN, 12));
        addToCart.setForeground(Color.white);
        addToCart.setBackground(Color.blue);
        addToCart.setBounds(58, 180, 200, 43);
        contentPane.add(addToCart);
        
        JButton changeColor = new JButton("Change Color");
        changeColor.setFont(new Font("Tahoma", Font.PLAIN, 12));
        changeColor.setForeground(Color.white);
        changeColor.setBackground(Color.blue);
        changeColor.setBounds(58, 230, 200, 43);
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
      		      	ResultSet resultSet = statement.executeQuery("SELECT itemName, price FROM approved_items");
      		      	
      		      	while(resultSet.next()) {
      		      		String itemName = resultSet.getString("itemName");
      		      		int price = resultSet.getInt("price");
      		      		CustomItem item = new CustomItem(itemName, price);
      		      		listModel.addElement(item);
      		      	}
      		      	resultSet.close();
      		      	statement.close();
      		      	connection.close();
      	
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
        
        addToCart.addActionListener(new ActionListener() {
        	  @Override
              public void actionPerformed(ActionEvent e) {
//        		  add items to array and calculate total
          	}
          });
        
        changeColor.addActionListener(new ActionListener() {
      	  @Override
            public void actionPerformed(ActionEvent e) {
      		  Color newColor = getRandomColor();
      		  changeColor.setBackground(newColor);
      		  addItems.setBackground(newColor);
      		  addedItems.setBackground(newColor);
      		  addToCart.setBackground(newColor);
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
