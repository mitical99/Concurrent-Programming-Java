package rs.ac.bg.etf.kdp.lockdinningsavages;

public class Chef extends Thread {
	private SharedData data;

	public Chef(String name, SharedData data) {
		super(name);
		this.data = data;
	}
	
	public void run() {
		while(true) {
			data.lock.lock();
			try {
				if(data.food != 0)
					data.cook.awaitUninterruptibly();
				data.food = SharedData.M;
				System.out.println("Chef cooked 10 meals");
				data.savager.signalAll();
			} finally {
				data.lock.unlock();
				try {
					sleep((int)(Math.random()*1000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
