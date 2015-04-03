/**
 * 
 */
package atm.controller;

import java.sql.*;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author CSWells
 */
public class DatabaseController {
	

	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://db4free.net/cs4398atm";

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
	private Object session_timeout_length = 60;

	/** 
	 * @return the session_timeout_length
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Object getSession_timeout_length() {
		// begin-user-code
		return session_timeout_length;
		// end-user-code
	}


	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String connectionString;
	private String CCN;
	
	public Set<Results> getAccounts(){
		Set<Results> accounts = new HashSet<Results>();
		String query = "Select a.name,a.account_bal,a.account_num from account a, Users u, User_Accounts ua Where ua.CCN = u.CCN AND ua.user_accounts_key = a.account_key AND u.CCN = '" + CCN +"'";
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
			String query = "SELECT COUNT(1) AS BIT FROM Users WHERE CCN = '" + account + "' AND PIN = '"+ PIN+"'";
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
	public int deposit(Integer account, Integer amount) {
		// begin-user-code
		// TODO Auto-generated method stub
		//SELECT account_bal FROM account WHERE account_num = 1112
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
			int newBal = amount + curBal;
			String query1 = "UPDATE account SET account_bal " + newBal +"'";
			ResultSet rs1 = stmt.executeQuery(query1);
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
	public void withdrawl() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void transfer() {
		// begin-user-code
		// TODO Auto-generated method stub

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

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void renew_session() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}