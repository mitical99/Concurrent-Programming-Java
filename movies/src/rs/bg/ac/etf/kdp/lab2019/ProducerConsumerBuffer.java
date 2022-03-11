package rs.bg.ac.etf.kdp.lab2019;

public interface ProducerConsumerBuffer<T> {
	public void put(T t);
	public T get();
}
