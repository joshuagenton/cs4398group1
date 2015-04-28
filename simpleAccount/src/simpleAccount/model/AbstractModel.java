package simpleAccount.model;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * This is the standard AbstractModel for the MCV setup.
 * It contains the basics for the models.
 * 
 * @author Paul Bryson
 * @since 2015-04-05
 */
public abstract class AbstractModel implements Model {
	private ArrayList listeners = new ArrayList(5);
	
	/**
	 * notifyChanged handles what to do if an object that we are 
	 * listening to changes.
	 */
	public void notifyChanged(ModelEvent event){
		ArrayList list = (ArrayList)listeners.clone();
		Iterator it = list.iterator();
		while(it.hasNext()){
			ModelListener ml = (ModelListener)it.next();
			ml.modelChanged(event);
		}
	}
	
	/**
	 * addModelListener adds a listener to an object.
	 * @param l what we want to listen to
	 */
	public void addModelListener(ModelListener l){
		listeners.add(l);
	}
	
	/**
	 * removeModelListener removes a listener from an object.
	 * @param l what we want to remove from the listeners
	 */
	public void removeModelListener(ModelListener l){
		listeners.remove(l);
	}
}
