package nice_name;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.introspection.ACLMessage;


private class OfferRequestsServer extends CyclicBehaviour {

	public OfferRequestsServer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		if(msg != null) {
			// Message received process it
			String title = msg.getContent();
			ACLMessage reply = msg.createReply();
			
			Integer price = (Integer) catalogue.get(title);
			if(price != null) {
				// The requested book is available for sale. Reply with the price
				reply.setPerformative(ACLMessage.PROPOSE);
				reply.setContent(String.valueOf(price.intValue()));
			}
			else {
				// The requested book is NOT available for sale.
				reply.setPerformative(ACLMessage.REFUSE);
				reply.setContent("not-availble");		
			}
			myAgent.sent(reply);
		}
	} //End of inner class OfferRequestsServer

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
