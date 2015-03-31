package atm.controller;

import atm.model.*;
import atm.view.*;


public interface Controller {
	void setModel(Model model);
	void setView(View view);
	
	Model getModel();
	View getView();
	
}
