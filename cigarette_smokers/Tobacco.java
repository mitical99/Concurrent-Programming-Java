package rs.ac.bg.etf.kdp.semaphorecigarettesmokers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class Tobacco extends Thread {
	private Semaphore table;
	private Semaphore waitForProducts;
	private Semaphore wakeUpAgent;
	private AtomicBoolean[] shared; //0-tobacco,1-paper,2-matches

	public Tobacco(Semaphore table, Semaphore waitForProducts, Semaphore wakeUpAgent, AtomicBoolean[] shared) {
		super();
		this.table = table;
		this.waitForProducts = waitForProducts;
		this.wakeUpAgent = wakeUpAgent;
		this.shared = shared;
	}

	public void run() {
		while(true) {
			waitForProducts.acquireUninterruptibly();
			table.acquireUninterruptibly();
			if(shared[1].get() && shared[2].get()) {
				shared[1].set(false);
				shared[2].set(false);
				System.out.println("Tobacco smoker took");
				table.release();
				wakeUpAgent.release();
				try {
					sleep((int)(Math.random()*2500));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				table.release();
				waitForProducts.release();
			}
		}
	}

}
