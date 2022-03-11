package rs.ac.bg.etf.kdp.semaphorecigarettesmokers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class Test {

	public static void main(String[] args) {
		Semaphore wakeUpAgent = new Semaphore(1);
		Semaphore waitForProducts = new Semaphore(0,true);
		Semaphore table = new Semaphore(1);
		AtomicBoolean[] shared = new AtomicBoolean[3];
		for(int i=0;i<3;i++)
			shared[i] = new AtomicBoolean(false);
	Agent agent = new Agent(shared,table,wakeUpAgent,waitForProducts);
	agent.start();
	Tobacco tobacco = new Tobacco(table,waitForProducts,wakeUpAgent,shared);
	Matches matches= new Matches(table,waitForProducts,wakeUpAgent,shared);
	Paper paper = new Paper(table,waitForProducts,wakeUpAgent,shared);
	tobacco.start();
	matches.start();
	paper.start();
	}
}
