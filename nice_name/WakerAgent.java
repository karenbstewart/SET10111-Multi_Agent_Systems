package nice_name;

import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;

// 
public class WakerAgent extends Agent {
	protected void setup() {
		System.out.println("Adding waker behaviour");
		addBehaviour(new WakerBehaviour(this, 500) {
			protected void handleElapsedTimeout() {
				// operation here is performed 5 seconds after setup is launched
				System.out.println("WAKE!!");
			}
		});
	}

}
