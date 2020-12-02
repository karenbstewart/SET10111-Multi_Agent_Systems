package nice_name;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class SimpleAgent extends Agent {
	int w = 20;
	
	//This method is called when the agent is launched
	protected void setup() {
		//Registering the agent with the yellow pages Directory with the DF Agent 
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Simple-agent");
		sd.setName(getLocalName() + "-Simple-agent");
		dfd.addServices(sd);
		try {
			DFService.register(this,  dfd);
		}
		catch(FIPAException e){
			e.printStackTrace();
		}
		// Print out a welcome message
		System.out.println("Hello! Agent " + getAID().getName()+" is ready." + "Extra text");
		
		addBehaviour(new WakerBehaviour(this, 5000) {
			protected void handleElapsedTimeout() {
				// operation here is performed 5 seconds after setup is launched
				System.out.println("WAKE!!");
			}
		});
		
		//Simple agent runs for 20 seconds then deletes and sends a bye bye message right before agent deletes with doDelete() method
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
	}
	
	
	
	protected void takeDown() {
		// Deregister the agent from the yellow pages with the DF Agent
		try {
			DFService.deregister(this);
		}
		catch(FIPAException e) {
			e.printStackTrace();
		}

	}

}


