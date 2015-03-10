/**
 * 
 */
package ATMUML.Model;

import ATMUML.View.Login_Interface;
import ATMUML.Controller.account_operation;
import ATMUML.Controller.data_basecontroller;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author CSWells
 */
public class atm_core {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String account_number;
	private String PIN;
	/** 
	 * @return the account_number
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getAccount_number() {
		// begin-user-code
		return account_number;
		// end-user-code
	}

	/** 
	 * @param account_number the account_number to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAccount_number(String account_number) {
		// begin-user-code
		this.account_number = account_number;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String account_validated;

	/** 
	 * @return the account_validated
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getAccount_validated() {
		// begin-user-code
		return account_validated;
		// end-user-code
	}

	/** 
	 * @param account_validated the account_validated to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAccount_validated(String account_validated) {
		// begin-user-code
		this.account_validated = account_validated;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Login_Interface login_Interface;

	/** 
	 * @return the login_Interface
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Login_Interface getLogin_Interface() {
		// begin-user-code
		return login_Interface;
		// end-user-code
	}

	/** 
	 * @param login_Interface the login_Interface to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setLogin_Interface(Login_Interface login_Interface) {
		// begin-user-code
		this.login_Interface = login_Interface;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private account_operation account_interface;

	/** 
	 * @return the account_interface
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public account_operation getAccount_interface() {
		// begin-user-code
		return account_interface;
		// end-user-code
	}

	/** 
	 * @param account_interface the account_interface to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAccount_interface(account_operation account_interface) {
		// begin-user-code
		this.account_interface = account_interface;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Session session;

	/** 
	 * @return the session
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Session getSession() {
		// begin-user-code
		return session;
		// end-user-code
	}

	/** 
	 * @param session the session to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setSession(Session session) {
		// begin-user-code
		this.session = session;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private data_basecontroller data_baseinterface;

	/** 
	 * @return the data_baseinterface
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public data_basecontroller getData_baseinterface() {
		return data_baseinterface;
	}

	/** 
	 * @param data_baseinterface the data_baseinterface to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setData_baseinterface(data_basecontroller data_baseinterface) {
		this.data_baseinterface = data_baseinterface;
		data_baseinterface.validate_user(account_number, PIN);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void start_login() {
		setLogin_Interface(new Login_Interface()); 
		account_number = getLogin_Interface().getAccount_number();
		PIN = getLogin_Interface().get_PIN();
		
		setData_baseinterface(new data_basecontroller());
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void start_session() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}