package rs.bg.ac.etf.kdp.lab2019;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Producer extends Thread {
	
	private SemaphoreProducerConsumerBuffer<Movie> buffer;
	
	public Producer(String name, SemaphoreProducerConsumerBuffer<Movie> buffer) {
		super(name);
		this.buffer=buffer;
	}
	
	private void scan() {
		try (Scanner scanner = new Scanner(new File("IMDb movies.csv"));) {
			String line = scanner.nextLine(); //ignore header
			while (scanner.hasNextLine()) {
		    	line = scanner.nextLine();
		    	String[] tmpMovie = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); //split ignoring commas inside quotes
		    	//System.out.println(new Movie(tmpMovie));
		    	this.buffer.put(new Movie(tmpMovie));
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		scan();
		buffer.setFinished();
		buffer.wakeUpWaitingConsumers();
	}
}
