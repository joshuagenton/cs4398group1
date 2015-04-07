package atm.model;

public class OverdrawException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	OverdrawException(double amt){
		super("Overdraw by " + amt);
	}
}
