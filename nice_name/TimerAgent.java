package nice_name;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

// A countdown ticker agent
public class TimerAgent extends Agent{
	int w = 15;
	public void setup()  
	{
		// Registering the countdown ticker agent with the yellow pages directory
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Countdown-Agent");sd.setName(getLocalName() + "-Countdown-Agent");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch(FIPAException e){
			e.printStackTrace();
		}
		
		
		//Create a new TickerBehaviour
		addBehaviour(new TickerBehaviour(this, 1000) {
			
			//call onTick every 1000ms (1 second)
			protected void onTick() {
				// Count down w = 15 therefore it last 15 seconds
				if (w > 0) 
				{
					System.out.println(w + " seconds left.");;
					w--;
				} else {
					System.out.println("bye, bye" + getAID().getName());
					myAgent.doDelete(); // Delete this agent
				}
				
			}
		});
	}

}

/// just adding  a line of code for test

