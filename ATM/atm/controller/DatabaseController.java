/**
 * 
 */
package atm.controller;

import java.sql.*;

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


	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @!generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int validate_user(Object account, String PIN) {
		// begin-user-code
		Connection conn = null;
		Statement stmt = null;
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
	public void balance(Object account, Integer session_id) {
		// begin-user-code
		boolean account_found = false;
		session_id = 12345;
		int error_code = 0; 
		int test = 0;
//		if (account_found == true) { 
//			return new Quartet<int, boolean, int, int>(error_code, account_found, session_id, test);
//		} else {
//			
//		}

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void deposit() {
		// begin-user-code
		// TODO Auto-generated method stub

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