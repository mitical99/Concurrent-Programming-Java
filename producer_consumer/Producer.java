package rs.ac.bg.etf.kdp.monitorproducerconsumer;

public class Producer extends Thread {

	private MonitorProducerConsumerBuffer<Integer> buffer;

	public Producer(String name, MonitorProducerConsumerBuffer<Integer> buffer) {
		super(name);
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (true) {
			Integer item = produce();
			try {
				buffer.put(item);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(this.getName() + " put " + item);
			try {
				sleep((int)(Math.random()*1500) + 1);
			} catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	private Integer produce() {
		Integer item = (int)(Math.random()*100) + 1;
		System.out.println(this.getName() + " produced " + item);
		return item;
	}
}
