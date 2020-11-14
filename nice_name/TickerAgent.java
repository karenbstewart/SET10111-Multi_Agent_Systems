package nice_name;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;

public class TickerAgent extends Agent{
	//Get the current time- this will be the time that the agent was launched at
	long t0 = System.currentTimeMillis();
	int w = 15;
	
	Behaviour loop;
	protected void setup() 
	{
		loop = new TickerBehaviour(this, 1000) {			
			
			protected void onTick() {
				// Print elapsed time since launch
				System.out.println( System.currentTimeMillis()-t0 + ": " + myAgent.getLocalName());		
			}
		};
		
		//Create a new TickerBehaviour
		addBehaviour(new TickerBehaviour(this, 1000) {
			
			//call onTick every 1000ms
			//will tick(run programme for 15 seconds then delete the agent)
			protected void onTick() {
				// Count down
				if (w > 0) 
				{					
					w--;
				} else {
					System.out.println("bye, bye" + getAID().getName());
					myAgent.doDelete(); // Delete this agent
				}
				
			}
		});
	addBehaviour(loop);	
	}
}
