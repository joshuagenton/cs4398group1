package atm.model;

import atm.controller.*;
import atm.view.*;

public interface ModelListener {
	public void modelChanged(ModelEvent me);
}
