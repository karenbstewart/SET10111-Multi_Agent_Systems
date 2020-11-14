package nice_name;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class B2 extends Behaviour {
	private int timesCalled = 0;
	public B2(Agent a) {
		myAgent = a;
	}
	
	@Override
	public void action() {
		System.out.println(timesCalled);
		timesCalled++;

	}

	@Override
	public boolean done() {
		return timesCalled >= 20;
	}

}
