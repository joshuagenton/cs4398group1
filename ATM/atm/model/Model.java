package atm.model;

/**
 * This is the standard Model interface for the MVC setup.
 * 
 * @author Stacie.Christensen
 * @since 2015-03-05
 */
public interface Model {
	public void notifyChanged(ModelEvent me);
}
