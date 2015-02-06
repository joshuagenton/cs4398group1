package ATM;

public class Screen extends Controller {
	private int currentState;

	public int getCurrentState() {
		return currentState;
	}

	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}
}
