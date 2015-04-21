package atm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the standard AbstractModel for the MCV setup.
 * It contains the basics for the models.
 * 
 * @author Stacie.Christensen
 * @since 2015-03-05
 */
public abstract class AbstractModel implements Model {
	
	private List<ModelListener> listeners = new ArrayList<ModelListener>(5);
	
	public void notifyChanged(ModelEvent event) {
		for(ModelListener ml : listeners){
			ml.modelChanged(event);
		}
	}
	
	public void addModelListener(ModelListener l){
		listeners.add(l);
	}
	public void removeModelListener(ModelListener l){
		listeners.remove(l);
	}

}
