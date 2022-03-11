package rs.ac.bg.etf.kdp.lockdinningsavages;

public class Savage extends Thread {

	private SharedData data;

	public Savage(String name, SharedData data) {
		super(name);
		this.data = data;
	}

	private void doSth() {
		try {
			sleep((int)(Math.random()*1500));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void eat() {
		try {
			sleep((int)(Math.random()*2500));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			doSth();
			data.lock.lock();
			try {
				while(data.food == 0) {
					data.savager.awaitUninterruptibly();
				}
				data.food--;
				System.out.println(this.getName() + " took " + (data.food + 1));
				if(data.food == 0) data.cook.signal();
			} finally {
				data.lock.unlock();
				eat();
			}
		}
	}
}
