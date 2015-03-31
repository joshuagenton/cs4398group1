/**
 * 
 */
package atm.model;

import javax.swing.SwingUtilities;

import atm.controller.data_basecontroller;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author CSWells
 */
public class atm_core extends AbstractModel{
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String account_number;
	private String PIN;
	private String name;
	
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
	 * @return the name
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getName() {
		// begin-user-code
		return name;
		// end-user-code
	}
	/** 
	 * @param account_number the account_number to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public synchronized void setAccount_info(String account_number, String name) {
		// begin-user-code
		this.account_number = account_number;
		this.name = name;
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.Login, 1, AgentStatus.NA);
		SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
		notifyAll();
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
	public void start_session() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}