package rs.bg.ac.etf.kdp.lab2019;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreProducerConsumerBuffer<T> implements ProducerConsumerBuffer<T> {

	private T[] buffer;
	private int head;
	private int tail;
	private AtomicInteger cap;
	private int waitingConsumers;
	private static final int N = 2000;
	private Semaphore spaceAv;
	private Semaphore itemAv;
	private Semaphore mutex;
	private boolean isProducerWaiting;
	private AtomicBoolean finished;

	public SemaphoreProducerConsumerBuffer() {

	}

	public int getCapacity() {
		return cap.get();
	}

	public void acquireMutex() {
		mutex.acquireUninterruptibly();
	}

	public void releaseMutex() {
		mutex.release();
	}

	public void setFinished() {
		finished.set(true);
	}

	public boolean getFinished() {
		return finished.get();
	}

	public T get() {
		mutex.acquireUninterruptibly();
		if (cap.get() == 0) {
			waitingConsumers++;
			mutex.release();
			itemAv.acquireUninterruptibly();
			mutex.acquireUninterruptibly();
			waitingConsumers--;
		}
		if(finished.get()) return null;
		T ret = buffer[head];
		head = (head + 1) % N;
		cap.decrementAndGet();
		if (isProducerWaiting)
			spaceAv.release();
		return ret;
	}

	public void put(T t) {
		mutex.acquireUninterruptibly();
		if (cap.get() == N) {
			isProducerWaiting = true;
			mutex.release();
			spaceAv.acquireUninterruptibly();
			mutex.acquireUninterruptibly();
			isProducerWaiting = false;
		}
		buffer[tail] = t;
		tail = (tail + 1) % N;
		cap.getAndIncrement();
		if (waitingConsumers > 0)
			itemAv.release();
		mutex.release();
	}

	public void wakeUpWaitingConsumers() {
		mutex.acquireUninterruptibly();
		if (waitingConsumers > 0) {
			itemAv.release(waitingConsumers);
		}
		mutex.release();
	}
}
