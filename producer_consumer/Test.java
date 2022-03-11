package rs.ac.bg.etf.kdp.monitorproducerconsumer;

public class Test {

	public static void main(String[] args) {
		// Integer t=null;
		MonitorProducerConsumerBuffer<Integer> buffer = new MonitorProducerConsumerBuffer<Integer>(5);
		Producer p = new Producer("P1", buffer);
		p.start();
		for (int i = 0; i < 3; i++) {
			Consumer c = new Consumer("C" + i,buffer);
			c.start();
		}
	}

}
