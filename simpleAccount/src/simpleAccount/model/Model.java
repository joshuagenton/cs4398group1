package simpleAccount.model;


/**
 * This is the standard Model interface for the MVC setup.
 * 
 * @author Paul Bryson
 * @since 2015-04-05
 */
public interface Model {
	void notifyChanged(ModelEvent e);
}
