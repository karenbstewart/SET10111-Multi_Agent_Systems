package nice_name;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.core.Runtime;

public class Application {
	
public static void main (String[] args) {
	
	// Setup the JADE environment 
	// - Launch JADE from within a java program accessed via the object MyContainer 
	Profile myProfile = new ProfileImpl();
	Runtime myRuntime = Runtime.instance();
	ContainerController myContainer = myRuntime.createMainContainer(myProfile);
	try {
		//Start the Agent controller, which is itself an agent (rma)
		AgentController rma = myContainer.createNewAgent("rma", "jade.tools.rma.rma", null);
		rma.start();
		
		//Now start our own SimpleAgent, called Fred
		//telling JADE to make new agent with definition in SimpleAgent with local name Fred
		AgentController myAgent = myContainer.createNewAgent("Fred", SimpleAgent.class.getCanonicalName(), null);
		myAgent.start();
		}catch(Exception e) {
			System.out.println("Exception starting agent Fred: " + e.toString());
		}
	
/*	try {
		//Now start our own TimerAgent, called DonnieD
		AgentController myTimerAgent = myContainer.createNewAgent("DonnieD", TimerAgent.class.getCanonicalName(), null);
		myTimerAgent.start();
		}catch(Exception e) {
			System.out.println("Exception starting agent DonnieD: " + e.toString());
		}
 */	
	try {
		//Now start our own TickerAgent, called Flash
		AgentController myTickerAgent = myContainer.createNewAgent("Flash", TickerAgent.class.getCanonicalName(), null);
		myTickerAgent.start();
		}catch(Exception e) {
			System.out.println("Exception starting agent Flash: " + e.toString());
		}

	}			
}	
	
