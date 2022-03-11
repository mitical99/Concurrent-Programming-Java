package rs.ac.bg.etf.kdp.onelinebridge;

public class North extends Car implements Runnable {

	private volatile Thread thread=null;
	public North(Bridge bridge, int id) {
		super(bridge, id);
	}
	
	public void start() {
		if(thread==null)
			thread=new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		starting();
		synchronized(bridge) {
			while (bridge.south != 0) {
				try {
					bridge.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				bridge.north++;
			}
		crossing();
		synchronized(bridge) {
			bridge.north--;
			if(bridge.north==0)
				notifyAll();
			}
		}
	}


