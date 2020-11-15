package nice_name;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class SimpleAgent extends Agent {
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
		System.out.println("Hello! Agent " + getAID().getName()+" is ready." + "Extra text" + getAID());
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


