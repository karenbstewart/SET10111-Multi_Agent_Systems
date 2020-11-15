package nice_name;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class FourStepBehaviour extends CyclicBehaviour {
	int step4 = 1;
	int result4 =0;
	
	public FourStepBehaviour (Agent a) {
		// Call the parent constructor,
		// to set the myAgent reference
		super(a);
	}
	
	@Override
	public void action() {
		switch(step4) {
		case 1:
			result4 += 10;
			System.out.println(result4 + " behaviour 4");
			step4++;
			break;
		case 2:
			result4 += 100;
			System.out.println(result4 + " behaviour 4");
			step4++;
			break;
		case 3:
			result4 += 200;
			System.out.println(result4 + " behaviour 4");
			step4++;
			break;
		case 4:
			result4 += 333;
			System.out.println(result4);
			reset();
			break;
		}
		
	}
	public void reset() {
		step4 = 1;
		result4 = 0;
	}

}
