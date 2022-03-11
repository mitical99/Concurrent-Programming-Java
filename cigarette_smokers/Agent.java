package rs.ac.bg.etf.kdp.semaphorecigarettesmokers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class Agent extends Thread {

	private AtomicBoolean[] shared;
	private Semaphore table;
	private Semaphore wakeUpAgent;
	private Semaphore waitForProducts;

	public Agent(AtomicBoolean[] shared, Semaphore table, Semaphore wakeUpAgent,
			Semaphore waitForProducts) {
		this.shared = shared;
		this.table = table;
		this.wakeUpAgent = wakeUpAgent;
		this.waitForProducts = waitForProducts;
		for (int i = 0; i < 3; i++)
			this.shared[i].set(false);
	}

	@Override
	public void run() {
		while (true) {
			wakeUpAgent.acquireUninterruptibly();
			produce();
			waitForProducts.release();
		}
	}

	private void produce() {
		int k = (int) (Math.random() * 3);
		try {
			sleep((int)(Math.random() * 1500));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.acquireUninterruptibly();
		for (int i = 0; i < 3; i++) {
			if(k==i) continue;
			System.out.println("Agent put " + i);
			shared[i].set(true);
		}
		table.release();
	}
}
