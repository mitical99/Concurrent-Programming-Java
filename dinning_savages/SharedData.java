package rs.ac.bg.etf.kdp.lockdinningsavages;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedData {
	public Lock lock = new ReentrantLock(true);
	Condition cook = lock.newCondition();
	Condition savager = lock.newCondition();
	public static final int M=10;
	public int food=0;
	public SharedData() {
	}
}
