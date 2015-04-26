package atm.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * This is the DatabaseController that we use to interact with the database.
 * 
 * @author Chris Wells
 * @since 2015-03-25
 */
public class DatabaseController {
	

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://commo.de/cs4398atm";

	//  Database credentials
	static final String USER = "cs4398group1";
	static final String PASS = "cs4398group1";
   
	Connection conn = null;
	Statement stmt = null;
	   
	private String connectionString;
	private String CCN;

	/**
	 * The getAccounts function gets all the accounts associated with the user's swiped 
	 * card and the PIN entered.
	 * 
	 * @return accounts that are associated with the user
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public List<Results> getAccounts() throws SQLException, Exception{
		List<Results> accounts = new ArrayList<Results>();
		String query = "Select a.name,a.account_bal,a.account_num from account a, "
				+ "Users u, User_Accounts ua Where ua.CCN = u.CCN AND "
				+ "ua.account_num = a.account_num AND u.CCN = '" + CCN +"'";
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			ResultSet rs= stmt.executeQuery(query); 
			while(rs.next()){
				accounts.add(new Results(rs.getString("name"), rs.getInt("account_num"),rs.getDouble("account_bal")));
			}
		Collections.sort(accounts, new AccountCompare());
		System.out.println("DB CONTROLLER ACCOUNTS SIZE: " + accounts.size());
		return accounts;
	}
	
	/**
	 * The getPicture function allows the system to access the camera on the ATM and 
	 * take a picture of the user.
	 * 
	 * @author Paul Bryson
	 * @param account the account information associated with the user
	 * @param PIN the PIN the user entered upon logging in
	 * @return the image of the user
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws Exception 
	 */
	public BufferedImage getPicture(Object account, String PIN) throws ClassNotFoundException, SQLException, IOException  {
		Connection conn = null;
		Statement stmt = null;
		CCN = (String) account;
		BufferedImage picture = null;
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
		return picture;
	}
	
	/**
	 * The setPicture function allows the system to use the picture that was taken and set it
	 * for the user's session.  This is a photo of the user who is using the ATM and card/PIN
	 * that is associated to the accounts.
	 * 
	 * @author Paul Bryson
	 * @param account the account the user is interacting with
	 * @param PIN the PIN that the user entered while logging in
	 * @param picture the picture of the user
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public void setPicture(Object account, String PIN, BufferedImage picture) throws SQLException, Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		CCN = (String) account;

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
	}

	/**
	 * The validate_user function validates the information that is entered (card information
	 * from the swiped card, and the PIN entered from the user) against the database
	 * to verify that the information matches in order to look at the associated accounts.
	 * 
	 * @author Chris Wells
	 * @param account the accounts associated with the user
	 * @param PIN the PIN they entered after swiping card
	 * @return zero if the account is not found, and a 1+ if the account is found
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public int validate_user(Object account, String PIN) throws SQLException, Exception {
		Connection conn = null;
		Statement stmt = null;
		CCN = (String) account;
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String query = "SELECT COUNT(1) AS BIT FROM Users WHERE CCN = '" + CCN + "' AND PIN = '"+ PIN+"'";
			ResultSet rs= stmt.executeQuery(query);
			if(rs.next())
				System.out.println(query + "\nreturned: "+ rs.getInt("BIT"));
			return rs.getInt("BIT");
	}

	/**
	 * The deposit function allows the user the ability to deposit money into a specified account.
	 * This function is not in use.
	 * 
	 * @param account the account which the money will be deposited to
	 * @param amount of money that will be deposited
	 * @return int for the response
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public int deposit(Integer account, double amount) throws SQLException, Exception {
		Connection conn = null;
		Statement stmt = null;
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
			String query1 = "UPDATE account SET account_bal = '" + newBal +"' Where account_num = '" +account+"'";
			stmt.executeUpdate(query1);
			
			//confirm & return new balance
			String query2 = "SELECT account_bal AS CURBAL FROM account WHERE account_num = '" + account + "'";
			ResultSet rs2= stmt.executeQuery(query2);
			if(rs2.next())
				System.out.println(query2 + "\nreturned: "+ rs2.getInt("CURBAL"));
				int finBal = rs2.getInt("CURBAL");
			return finBal;
	}

	/**
	 * The withdraw function allows a user to withdraw funds from a selected
	 * account.
	 * 
	 * @param account the account with they would like to withdraw from
	 * @param d the amount they would like to withdraw
	 * @return boolean on if the withdraw was successful
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public boolean withdrawl(Integer account, double d) throws SQLException, Exception {
		Connection conn = null;
		Statement stmt = null;
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
	}

	/**
	 * The transfer function allows a user to transfer money from one account to another.
	 * 
	 * @param fromAccountNum the from account
	 * @param toAccountNum the to account
	 * @param amount the amount in which to transfer
	 * @return boolean on if the transfer was successful
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public boolean transfer(Integer fromAccountNum, Integer toAccountNum, double amount) throws SQLException, Exception {
		boolean account1Bal = withdrawl(fromAccountNum, amount);
		if (account1Bal == true){
			deposit(toAccountNum, amount);
			return true;
		}
		
		//If transfer amount is not available to transfer			
		return false;
	}

	public String getConnectionString() {
		return connectionString;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}
}