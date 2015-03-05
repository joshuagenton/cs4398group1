/**
 * 
 */
package ATMUML.View;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import ATMUML.Controller.card_reader;
import ATMUML.Model.atm_core;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author CSWells
 */
public class Login_Interface {
	
	public Login_Interface() {
		read_card();
		set_pin();
	}	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 */
	private atm_core atm_core;

	/** 
	 * @return the atm_core
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public atm_core getAtm_core() {
		// begin-user-code
		return atm_core;
		// end-user-code
	}

	/** 
	 * @param atm_core the atm_core to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAtm_core(atm_core atm_core) {
		// begin-user-code
		this.atm_core = atm_core;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 */
	private card_reader card_reader;

	/** 
	 * @return the card_reader
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public card_reader getCard_reader() {
		// begin-user-code
		return card_reader;
		// end-user-code
	}

	/** 
	 * @param card_reader the card_reader to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setCard_reader(card_reader card_reader) {
		// begin-user-code
		this.card_reader = card_reader;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 */
	private Object login_start_time;

	/** 
	 * @return the login_start_time
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Object getLogin_start_time() {
		// begin-user-code
		return login_start_time;
		// end-user-code
	}

	/** 
	 * @param login_start_time the login_start_time to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setLogin_start_time(Object login_start_time) {
		// begin-user-code
		this.login_start_time = login_start_time;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 */
	private Object login_length;

	/** 
	 * @return the login_length
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Object getLogin_length() {
		// begin-user-code
		return login_length;
		// end-user-code
	}

	/** 
	 * @param login_length the login_length to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setLogin_length(Object login_length) {
		// begin-user-code
		this.login_length = login_length;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 */
	private Object account_number;

	/** 
	 * @return the account_number
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Object getAccount_number() {
		return account_number;
	}

	/** 
	 * @param account_number the account_number to set
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAccount_number(Object account_number) {
		this.account_number = account_number;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void read_card() {
		Scanner cardin = new Scanner(System.in);
		card_reader cardRead = null;
		//String test = "%T6391480001052388  ^WELLS/CHRISTOPHER         ^4912160?;6391480001052388=4912160?+202=411558900=00?";
		System.out.println("Scan your Card: ");
		try {
			cardRead = new card_reader(cardin.nextLine());
			setLogin_start_time(new Date());
		} catch (IOException e) {
			System.out.println("Card Read Error: " + e.getMessage());
		}
		System.out.println("Cardholder: " + cardRead.getCard_holder());
		System.out.println("CCN: " + cardRead.getCcn());
		System.out.println("EXP: " + cardRead.getExpDateString());
		System.out.println(getLogin_start_time().toString());
		
		setAccount_number(cardRead.getCcn());
	}
	private Integer PIN;
	
	public void set_pin() {
		Scanner pinIn = new Scanner(System.in);
		System.out.println("Enter PIN: ");
		this.PIN = pinIn.nextInt();
	}
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 */
	public Integer get_PIN() {
		return this.PIN;
	}


	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void login_timer() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}