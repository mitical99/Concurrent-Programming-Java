package rs.ac.bg.etf.kdp.barrierh20;

public class Oxygen extends Thread {
	
	private SharedData data;
	
	public Oxygen(String name,SharedData data) {
		super(name);
		this.data=data;
	}
	
	public void run() {
		data.mutex.acquireUninterruptibly();
		if(data.hydrogen.get() > 1) {
			data.hydrogen.set(data.hydrogen.get() - 2);
			data.H.release(2);
			System.out.println(this.getName() + " bonding ");
			data.mutex.release();
		}
		else {
			data.oxygen.getAndIncrement();
			data.mutex.release();
			System.out.println(this.getName() + " Wait to bond ");
			data.O.acquireUninterruptibly();
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
