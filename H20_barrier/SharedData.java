package rs.ac.bg.etf.kdp.barrierh20;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SharedData {
	public Semaphore H=new Semaphore(0);
	public Semaphore O=new Semaphore(1);
	public Semaphore mutex = new Semaphore(1);
	public AtomicInteger hydrogen=new AtomicInteger(0),oxygen=new AtomicInteger(0);
	public SharedData() {
		
	}
}
