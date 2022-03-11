package rs.ac.bg.etf.kdp.barrierh20;

public class Hydrogen extends Thread {

	private SharedData data;

	public Hydrogen(String name, SharedData data) {
		super(name);
		this.data = data;
	}

	public void run() {
		data.mutex.acquireUninterruptibly();
		if (data.hydrogen.get() > 0 && data.oxygen.get() > 0) {
			data.hydrogen.decrementAndGet();
			data.oxygen.decrementAndGet();
			data.H.release();
			data.O.release();
			System.out.println(this.getName() + " bonding ");
			data.mutex.release();
		}
		else {
			data.hydrogen.getAndIncrement();
			data.mutex.release();
			System.out.println(this.getName() + " Wait to bond");
			data.H.acquireUninterruptibly();
			System.out.println(this.getName() + " bonding ");
		}
		bond();
	}

	private void bond() {
		try {
			sleep((int)(Math.random()*4000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
