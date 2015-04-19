/**
 * 
 */
package atm.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author CSWells
 */
public class DatabaseController {
	

	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   //static final String DB_URL = "jdbc:mysql://db4free.net/cs4398atm";
	   static final String DB_URL = "jdbc:mysql://commo.de/cs4398atm";
	   //static final String DB_URL = "jdbc:mysql://192.168.67.224/cs4398atm";

	   //  Database credentials
	   static final String USER = "cs4398group1";
	   static final String PASS = "cs4398group1";
	   
	   Connection conn = null;
	   Statement stmt = null;
	   
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String connectionString;
	private String CCN;

	public Set<Results> getAccounts(){
		Set<Results> accounts = new HashSet<Results>();
		String query = "Select a.name,a.account_bal,a.account_num from account a, Users u, User_Accounts ua Where ua.CCN = u.CCN AND ua.account_num = a.account_num AND u.CCN = '" + CCN +"'";
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			ResultSet rs= stmt.executeQuery(query); 
			while(rs.next()){
				accounts.add(new Results(rs.getString("name"), rs.getInt("account_num"),rs.getDouble("account_bal")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DB CONTROLLER ACCOUNTS SIZE: " + accounts.size());
		return accounts;
	}
	
	public BufferedImage getPicture(Object account, String PIN) {
		Connection conn = null;
		Statement stmt = null;
		CCN = (String) account;
		BufferedImage picture = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String query = "SELECT * FROM Users WHERE CCN = '" + CCN + "' AND PIN = '"+ PIN + "'";
			ResultSet rs= stmt.executeQuery(query);
			if(rs.next()){
				Blob blob = rs.getBlob("Picture");
				if (blob != null){
					byte[] blobAsBytes = blob.getBytes(1, (int) blob.length());
					picture = ImageIO.read(new ByteArrayInputStream(blobAsBytes));
				}
				return picture;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return picture;
	}
	
	public void setPicture(Object account, String PIN, BufferedImage picture) {
		Connection conn = null;
		PreparedStatement stmt = null;
		CCN = (String) account;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(picture, "JPG", baos);
			baos.flush();
			byte[] imageAsRawBytes = baos.toByteArray();
			Blob blob = new javax.sql.rowset.serial.SerialBlob(imageAsRawBytes);
			stmt = conn.prepareStatement("UPDATE users SET Picture = ? WHERE CCN = ? and PIN = ?");
			stmt.setBlob(1, blob);
			stmt.setString(2, CCN);
			stmt.setString(3, PIN);
			stmt.executeUpdate();
			System.out.println("Updated picture");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @!generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int validate_user(Object account, String PIN) {
		// begin-user-code
		// returns 0 if account not found.
		// returns 1+ if account(s) found.
		Connection conn = null;
		Statement stmt = null;
		CCN = (String) account;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String query = "SELECT COUNT(1) AS BIT FROM Users WHERE CCN = '" + CCN + "' AND PIN = '"+ PIN+"'";
			ResultSet rs= stmt.executeQuery(query);
			if(rs.next())
				System.out.println(query + "\nreturned: "+ rs.getInt("BIT"));
			return rs.getInt("BIT");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int deposit(Integer account, double amount) {
		// begin-user-code
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			//get current account balance
			String query = "SELECT account_bal AS CURBAL FROM account WHERE account_num = '" + account + "'";
			ResultSet rs= stmt.executeQuery(query);
			if(rs.next())
				System.out.println(query + "\nreturned: "+ rs.getInt("CURBAL"));
				int curBal = rs.getInt("CURBAL");
			//calculate new balance
			double newBal = amount + curBal;
			//set new balance
			String query1 = "UPDATE account SET account_bal " + newBal +"'";
			ResultSet rs1 = stmt.executeQuery(query1);
			//confirm & return new balance
			String query2 = "SELECT account_bal AS CURBAL FROM account WHERE account_num = '" + account + "'";
			ResultSet rs2= stmt.executeQuery(query2);
			if(rs2.next())
				System.out.println(query2 + "\nreturned: "+ rs2.getInt("CURBAL"));
				int finBal = rs2.getInt("CURBAL");
			return finBal;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public boolean withdrawl(Integer account, double d) {
		// begin-user-code
		// TODO Auto-generated method stub
		//returns new balance if current balance - amount is >= 0
		//returns -1 if new balance would be < 0 after withdrawl
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			//get current account balance
			double curBal = -1;
			double	newBal = -1;
			String query = "SELECT account_bal AS CURBAL FROM account WHERE account_num = '" + account + "'";
			ResultSet rs= stmt.executeQuery(query);
			if(rs.next()){
				System.out.println(query + "\nreturned: "+ rs.getInt("CURBAL"));
				curBal = rs.getInt("CURBAL");
			}
			//calculate new balance
			newBal = curBal - d;
			if (newBal < 0){return false;}
			//set new balance
			String query1 = "UPDATE account SET account_bal = '" + newBal +"' Where account_num = '" +account+"'"  ;
			System.out.println(query1);
			stmt.executeUpdate(query1);
			//confirm & return new balance
			String query2 = "SELECT account_bal AS CURBAL FROM account WHERE account_num = '" + account + "'";
			ResultSet rs2= stmt.executeQuery(query2);
			if(rs2.next()){
				System.out.println(query2 + "\nreturned: "+ rs2.getInt("CURBAL"));
			}
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public boolean transfer(Integer fromAccountNum, Integer toAccountNum, double amount) {
		// begin-user-code
		// TODO Auto-generated method stub
		// returns set of accounts in Results class. 
		// Withdrawal funds 
		boolean account1Bal = withdrawl(fromAccountNum, amount);
		if (account1Bal == true){
			deposit(toAccountNum, amount);
			return true;}
		//If transfer amount is not available to transfer			
		return false;
		
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void transaction_history() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}