package rs.bg.ac.etf.kdp.lab2019Monitors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerConsumerBuffer<T> implements Buffer<T> {

	private static final int N = 2000;
	private T[] buffer;
	private int head, tail;
	private AtomicBoolean finished;
	private AtomicInteger cap;
	private int waitingConsumers;
	private boolean isProducerWaiting;

	public boolean getFinished() {
		return finished.get();
	}

	public void setFinished() {
		finished.set(true);
	}

	public int getCap() {
		return cap.get();
	}

	public void setCap(int cap) {
		this.cap.set(cap);
	}

	public ProducerConsumerBuffer() {
		buffer = (T[]) new Object[N];
		head = 0;
		tail = 0;
		finished = new AtomicBoolean(false);
		cap = new AtomicInteger(0);
		waitingConsumers = 0;
		isProducerWaiting = false;
	}

	public synchronized T get() {
		while (cap.get() == 0 && !finished.get()) {
			try {
				waitingConsumers++;
				wait();
				waitingConsumers--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(cap.get()==0) {
			notifyAll();
			return null;
		}
		T ret = buffer[tail];
		tail = (tail + 1) % N;
		cap.decrementAndGet();
		if(isProducerWaiting)
			notifyAll();
		return ret;
	}

	public void put(T t) {
		while (cap.get() == N) {
			try {
				isProducerWaiting = true;
				wait();
				isProducerWaiting = false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		buffer[head] = t;
		head = (head + 1) % N;
		cap.getAndIncrement();
		if (waitingConsumers > 0)
			notify();
	}

}
