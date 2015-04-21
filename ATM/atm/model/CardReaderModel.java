/**
 * 
 */
package atm.model;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This is the CardReaderModel.  This is where the card information parsing happens
 * and reading the data from the card.
 * 
 * @author Chris Wells
 * @since 2015-03-05
 */
public class CardReaderModel extends AbstractModel {
	private String card_holder = null;
	private String ccn = null;
	private DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	private Date expDate = null;


	/**
	 * Constructor.
	 * 
	 * @param cardRead card information that is being read
	 * @throws IOException for the card read
	 */
	public CardReaderModel(String cardRead) throws IOException {
		readcont(cardRead);
	}

	/**
	 * Default constructor.
	 */
	public CardReaderModel() {
	}

	/**
	 * The readcont function reads the card information from the card that is swiped.
	 * 
	 * @param s string of the information read
	 * @throws IOException for the card read
	 */
	public void readcont(String s) throws IOException {
		String track1_cardholder = "";
		String track1_expmo = "";
		String track1_expyr = "";
		String track1_ccn = "";
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
						continue;
					}
				}

				if (track1_caret1_found && !track1_caret2_found) {
					//region Get-Cardholder-Name

					if (c != '^') {
						track1_cardholder += c;
					} else {
						track1_caret2_found = true;
						continue;
					}
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
					if (track1_leg3_count == 22) {
					}
					if (track1_leg3_count == 23) {
					}
					if (track1_leg3_count == 24) {
					}
					track1_leg3_count++;
				}

				if (c == '?') {
					in_track_1 = false;
					in_track_2 = true;
					continue;
				}
			}

			if (in_track_2) {
				//region Track2

				if (!track2_equals_found) {
					//region Get-Track2-CCN

					if ((c != ';') && (c != '=')) {
					}

					if (c == '=') {
						track2_equals_found = true;
						continue;
					}
				}

				if (track2_equals_found) {
					//region Get-Expiration-and-Encrypted-PIN

					if (track2_leg2_count == 0) {
					}
					if (track2_leg2_count == 1) {
					}
					if (track2_leg2_count == 2) {
					}
					if (track2_leg2_count == 3) {
					}
					if (track2_leg2_count == 8) {
					}
					if (track2_leg2_count == 9) {
					}
					if (track2_leg2_count == 10) {
					}
					track2_leg2_count++;
				}

				if (c == '?') {
					in_track_2 = false;
					in_track_3 = true;
					continue;
				}
			}

			if (in_track_3) {
				//region Track3
				
			}
		}
		setCard_holder(track1_cardholder.trim());
		setCcn(track1_ccn.trim());
		setExpDate(track1_expmo + "/" + 1 + "/" + track1_expyr);
	}

	// Getters/Setters
	
	public DateFormat getFormatter() {
		return formatter;
	}

	public void setFormatter(DateFormat formatter) {
		this.formatter = formatter;
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

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getExpDateString() {
		Calendar cdr = new GregorianCalendar();
		cdr.setTime(getExpDate());
		return ((cdr.get(Calendar.MONTH) + 1) + "/" + cdr.get(Calendar.YEAR));
	}

}