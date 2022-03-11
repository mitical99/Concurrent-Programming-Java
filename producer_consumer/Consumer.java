package rs.ac.bg.etf.kdp.monitorproducerconsumer;

public class Consumer extends Thread {
	MonitorProducerConsumerBuffer<Integer> buffer;

	public Consumer(String name, MonitorProducerConsumerBuffer<Integer> buffer) {
		super(name);
		this.buffer = buffer;
	}

	@Override
	public void run() {
		Integer item=null;
		while(true) {
			try {
				 item = buffer.get();
				 System.out.println(this.getName() + " consumed " + item);
				 sleep((int)(Math.random() * 1500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
