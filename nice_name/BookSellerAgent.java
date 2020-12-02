package nice_name;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
//import jade.domain.introspection.ACLMessage;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import nice_name.BookSellerGui;

import java.util.*;

public class BookSellerAgent extends Agent {
	// The catalogue of books for sale (maps the title of a book to it's price)
	private Hashtable catalogue;
	// The GUI by means of which the user can add books in the catalogue
	private BookSellerGui myGui;
	
	// Put agent initialisation here
	protected void setup() {
		//Registering the agent with the yellow pages Directory with the DF Agent 
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("book-selling");
		sd.setName(getLocalName() + "JADE-book-trading");
		dfd.addServices(sd);
		try {
			DFService.register(this,  dfd);
		}
		catch(FIPAException e){
			e.printStackTrace();
		}
		
		catalogue = new Hashtable();
		
		// Create and show the GUI
		myGui = new BookSellerGui(this);
		myGui.showGui();
		
		// Add the behaviour serving requests for offer for buyer agents
		addBehaviour(new OfferRequestsServer());
		
		// Add the behaviour serving purchase orders from buyer agents
		addBehaviour(new PurchaseOrderServer());
	}
	
	// Put agent clean-up operations here
	protected void takeDown() {
		// Deregister the agent from the yellow pages with the DF Agent
		try {
			DFService.deregister(this);
		}
		catch(FIPAException fe) {
			fe.printStackTrace();
		}
		// Close the GUI
		myGui.dispose();
		// Printout a dismissal message
		System.out.println("Seller-agent " + getAID().getName() + " terminating.");		
	}
	public void updateCatalogue(final String title, final int price) {
		addBehaviour(new OneShotBehaviour(){
			public void action() {
				catalogue.put(title, new Integer(price));
				System.out.println(catalogue);
			}
		});
			
	}
	private class OfferRequestsServer extends CyclicBehaviour {
		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
			ACLMessage msg = myAgent.receive(); 										
			if(msg != null) {
				// Message received process it
				String title = msg.getContent();
				ACLMessage reply = msg.createReply();
				
				Integer price = (Integer) catalogue.get(title);
				if(price != null) {
					System.out.println("Seller has " + title + " for price " + price );
					// The requested book is available for sale. Reply with the price
					reply.setPerformative(ACLMessage.PROPOSE);
					reply.setContent(String.valueOf(price.intValue()));
				}
				else {
					// The requested book is NOT available for sale.
					reply.setPerformative(ACLMessage.REFUSE);
					reply.setContent("not-availble");		
				}
				myAgent.send(reply);
			}
			else {
				block();
			}
		}
	}//End of inner class OfferRequestsServer
		
		private class PurchaseOrderServer extends CyclicBehaviour {
			public void action() {
				MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL);
				ACLMessage msg = myAgent.receive(mt);
				if (msg != null) {
					// ACCEPT_PROPOSAL Message received. Process it
					String title = msg.getContent();
					ACLMessage reply = msg.createReply();
					Integer price = (Integer) catalogue.remove(title);
					if (price != null) {
						reply.setPerformative(ACLMessage.INFORM);
						System.out.println(title + " sold to agent " + msg.getSender().getName());
					}
					else {
						// The requested book has been sold to another buyer in the meanwhile.
						reply.setPerformative(ACLMessage.FAILURE);
						reply.setContent("not-available");
					}
					myAgent.send(reply);			
				}
				else {
					block();
				}
			}
		}// End of inner class PurchaseOrderServer
		
	}