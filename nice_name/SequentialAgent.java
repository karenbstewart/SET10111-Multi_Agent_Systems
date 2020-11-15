package nice_name;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;

public class SequentialAgent extends Agent{
	private int result = 0;
	
	protected void setup() {
		SequentialBehaviour s1 = new SequentialBehaviour(this);
		s1.addSubBehaviour(new B4());
		s1.addSubBehaviour(new B5());
		s1.addSubBehaviour(new B6());
		addBehaviour(s1);
	}
	public class B4 extends OneShotBehaviour {

		@Override
		public void action() {
			result += 10;
			System.out.println(result + " SequentialAgent");
		}
	}
	public class B5 extends OneShotBehaviour {

		@Override
		public void action() {
			result += 100;
			System.out.println(result + " SequentialAgent");
		}
	}
	public class B6 extends OneShotBehaviour {

		@Override
		public void action() {
			result += 210;
			System.out.println(result + " SequentialAgent");
		}
	}

}
