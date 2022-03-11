package rs.ac.bg.etf.kdp.monitorproducerconsumer;

import java.lang.reflect.Array;

import rs.ac.bg.etf.kdp.betterway.AtomicBroadcastBuffer;

public class MonitorProducerConsumerBuffer<T> implements ProducerConsumerBuffer<T> {

	private int B; //velicina buffera
	//private int N; //broj consumera
	private T[] buffer;
	private int head,tail;
	private int cap;
	
	
	
	public MonitorProducerConsumerBuffer(int b) {
		super();
		B = b;
		//N = n;
		head=tail=0;
		cap=0;
		buffer = (T[]) new Object[B];
	}

	@Override
	public synchronized T get() throws InterruptedException {
		while (cap==0) {
			wait();
		}
		T item = buffer[head];
		head=(head+1) % B;
		cap--;
		notifyAll();
		return item;
	}

	@Override
	public synchronized void put(T item) throws InterruptedException {
		while(cap==B) {
			wait();
		}
		buffer[tail] = item;
		tail = (tail+1) % B;
		cap++;
		notifyAll();
	}
	
}
