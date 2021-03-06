package nice_name;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class DemoAgent extends Agent {
	
	protected void setup() {
		addBehaviour(new B1(this));
		addBehaviour(new B2(this));
		// Behaviour 3 cycles forever
		//addBehaviour(new ThreeStepBehaviour(this));		
		addBehaviour(new FourStepBehaviour(this));	
	}
}
