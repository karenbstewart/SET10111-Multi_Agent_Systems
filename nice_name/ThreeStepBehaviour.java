package nice_name;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class ThreeStepBehaviour extends Behaviour{
	private int step = 1;
	private int result = 0;
	private boolean finished = false;
	
	public ThreeStepBehaviour (Agent a) {
		// Call the parent constructor,
		// to set the myAgent reference
		super(a);
	}

	@Override
	public void action() {
		switch(step) {
		case 1:
			result += 10;
			System.out.println(result + " behaviour 3");
			step++;
			break;
		case 2:
			result += 100;
			System.out.println(result + " behaviour 3");
			step++;
			break;
		case 3:
			result += 200;
			System.out.println(result + " behaviour 3");
			finished = true;
			break;
		}
		
	}

	@Override
	public boolean done() {
		System.out.println("behaviour 3 done()");
		return finished;
	}
	
	@Override
	public int onEnd() {
		System.out.println("Terminating Behaviour");
		return 0;
	}
		
	

}
