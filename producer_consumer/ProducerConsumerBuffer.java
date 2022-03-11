package rs.ac.bg.etf.kdp.monitorproducerconsumer;

public interface ProducerConsumerBuffer<T> {
	public void put(T item) throws InterruptedException;
	public T get() throws InterruptedException;
}
