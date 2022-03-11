package rs.bg.ac.etf.kdp.lab2019;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SharedData {
	
	private ArrayList<Movie> movies = new ArrayList<Movie>();
	private int observedFilms=0;
	
	public int getObservedFilms() {
		return observedFilms;
	}
	public void setObservedFilms(int observedFilms) {
		this.observedFilms = observedFilms;
	}
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

	private int maxDirectors=0;
	private int localMax=0;
	private int ConsumerId;
	private boolean notNeeded = false;
	
	
	public int getConsumerId() {
		return ConsumerId;
	}
	public void setConsumerId(int consumerId) {
		ConsumerId = consumerId;
	}
	public boolean isNotNeeded() {
		return notNeeded;
	}
	public void setNotNeeded(boolean notNeeded) {
		this.notNeeded = notNeeded;
	}
	public int getLocalMax() {
		return localMax;
	}
	public void setLocalMax(int localMax) {
		this.localMax = localMax;
	}
	
	public int getMaxDirectors() {
		return maxDirectors;
	}
	public void setMaxDirectors(int maxDirectors) {
		this.maxDirectors = maxDirectors;
	}
	
	public Semaphore mutex = new Semaphore(1);
	public Semaphore combinerSem = new Semaphore(0);
	public Semaphore waitFilms = new Semaphore(0);
	public Semaphore[] consumersSem;
	private AtomicInteger consumerCnt=new AtomicInteger(0);
	
	public SharedData(int consumerNum) {
		consumersSem = new Semaphore[consumerNum];
		for(int i=0;i<consumerNum;i++) {
			consumersSem[i] = new Semaphore(0);
		}
		consumerCnt = new AtomicInteger(consumerNum);
	}
	public int getConsumerCnt() {
		return consumerCnt.get();
	}
}
