package atm.view;

import atm.controller.*;
import atm.model.*;

public interface View {
	Controller getController();
	public Model getModel();
	public void setController(Controller aController);
	public void setModel(Model aModel);
}

