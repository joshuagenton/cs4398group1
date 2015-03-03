/**
 * 
 */
package ATMUML.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ATMUML.View.Login_Interface;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author CSWells
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class card_reader {
	private String card_holder = null;
	private String ccn = null;
	private DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private Date expDate = null;

	public card_reader(String cardRead) throws IOException {
		readcont(cardRead);
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
	public void read_card() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void read() throws IOException {
		BufferedReader track_data = new BufferedReader(new InputStreamReader(
				System.in));
		String s = track_data.readLine();
		readcont(s);
		//System.out.println(s);
	}

	public void read(String s) throws IOException {
		readcont(s);
	}

	public void readcont(String s) throws IOException {
		String track1 = "";
		String track2 = "";
		String track3 = "";
		String track1_cardholder = "";
		String track1_expmo = "";
		String track1_expyr = "";
		String track1_cvv = "";
		String track1_ccn = "";
		String track2_ccn = "";
		String track2_expmo = "";
		String track2_expyr = "";
		String track2_encpin = "";

		Boolean in_track_1 = true;
		Boolean in_track_2 = false;
		Boolean in_track_3 = false;
		Boolean track1_caret1_found = false;
		Boolean track1_caret2_found = false;
		Boolean track2_equals_found = false;

		int track1_leg3_count = 0;
		int track2_leg2_count = 0;
		for (char c : s.toCharArray()) {
			if (in_track_1) {
				//region Track1

				if (!track1_caret1_found) {
					//region Get-Track1-CCN

					if ((c != '%') && (c != 'B') && (c != '^') && (c != 'T')) {
						track1_ccn += c;
					}

					if (c == '^') {
						track1_caret1_found = true;
						track1 += c;
						continue;
					}

					//endregion
				}

				if (track1_caret1_found && !track1_caret2_found) {
					//region Get-Cardholder-Name

					if (c != '^') {
						track1_cardholder += c;
					} else {
						track1_caret2_found = true;
						track1 += c;
						continue;
					}

					//endregion
				}

				if (track1_caret1_found && track1_caret2_found) {
					//region Get-Expiration-and-CVV

					if (track1_leg3_count == 0)
						track1_expyr += c;
					if (track1_leg3_count == 1)
						track1_expyr += c;
					if (track1_leg3_count == 2)
						track1_expmo += c;
					if (track1_leg3_count == 3)
						track1_expmo += c;
					if (track1_leg3_count == 22)
						track1_cvv += c;
					if (track1_leg3_count == 23)
						track1_cvv += c;
					if (track1_leg3_count == 24)
						track1_cvv += c;
					track1_leg3_count++;

					//endregion
				}

				track1 += c;
				if (c == '?') {
					in_track_1 = false;
					in_track_2 = true;
					continue;
				}

				//endregion
			}

			if (in_track_2) {
				//region Track2

				if (!track2_equals_found) {
					//region Get-Track2-CCN

					if ((c != ';') && (c != '=')) {
						track2_ccn += c;
					}

					if (c == '=') {
						track2_equals_found = true;
						track2 += c;
						continue;
					}

					//endregion
				}

				if (track2_equals_found) {
					//region Get-Expiration-and-Encrypted-PIN

					if (track2_leg2_count == 0)
						track2_expyr += c;
					if (track2_leg2_count == 1)
						track2_expyr += c;
					if (track2_leg2_count == 2)
						track2_expmo += c;
					if (track2_leg2_count == 3)
						track2_expmo += c;
					if (track2_leg2_count == 8)
						track2_encpin += c;
					if (track2_leg2_count == 9)
						track2_encpin += c;
					if (track2_leg2_count == 10)
						track2_encpin += c;
					track2_leg2_count++;

					//endregion
				}

				track2 += c;
				if (c == '?') {
					in_track_2 = false;
					in_track_3 = true;
					continue;
				}

				//endregion
			}

			if (in_track_3) {
				//region Track3

				track3 += c;

				//endregion
			}
		}
		setCard_holder(track1_cardholder);
		setCcn(track1_ccn);
		setExpDate(track1_expmo + "/" + 1 + "/" + track1_expyr);
	}

	private void setCard_holder(String card_holder) {
		this.card_holder = card_holder;
	}

	public String getCard_holder() {
		return card_holder;
	}

	private void setCcn(String ccn) {
		this.ccn = ccn;
	}

	public String getCcn() {
		return ccn;
	}

	private void setExpDate(String expDate) {
		Date date = null;
		try {
			date = formatter.parse(expDate);
		} catch (ParseException e) {
			e.getCause();
		}
		this.expDate = date;
	}

	public Date getExpDate() {
		return expDate;
	}

	public String getExpDateString() {

		Calendar cdr = new GregorianCalendar();
		cdr.setTime(getExpDate());
		return ((cdr.get(Calendar.MONTH) + 1) + "/" + cdr.get(Calendar.YEAR));
	}
}