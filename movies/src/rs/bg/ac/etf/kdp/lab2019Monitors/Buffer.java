package rs.bg.ac.etf.kdp.lab2019Monitors;

public interface Buffer<T> {
	public void put(T t);
	
	public T get();
}
