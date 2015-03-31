package atm.model;

import atm.controller.*;
import atm.view.*;

public interface Model {
	public void notifyChanged(ModelEvent me);
}
