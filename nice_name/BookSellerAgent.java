package nice_name;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.introspection.ACLMessage;

import java.util.*;

public class BookSellerAgent extends Agent {
	// The catalogue of books for sale (maps the title of a book to it's price)
	private Hashtable catalogue;
	// The GUI by means of which the user can add books in the catalogue
	private BookSellerGui myGui;
	
	// Put agent initialisation here
	protected void setup() {
		catalogue = new Hashtable();
		
		// Create and show the GUI
		myGui = new BookSellerGui(this);
		myGui.show();
		
		// Add the behaviour serving requests for offer for buyer agents
		addBehaviour(new OfferRequestsServer());
		
		// Add the behaviour serving purchase orders from buyer agents
		addBehaviour(new PurchaseOrderServer());
	}
	
	// Put agent clean-up operations here
	protected void takeDown() {
		// Close the GUI
		myGui.dispose();
		// Printout a dismissal message
		System.out.println("Seller-agent " + getAID().getName() + " terminating.");		
	}
	public void updateCatalogue(final String title, final int price) {
		addBehaviour(new OneShotBehaviour(){
			public void action() {
				catalogue.put(title, new Integer(price));
			}
		});
	}
}
