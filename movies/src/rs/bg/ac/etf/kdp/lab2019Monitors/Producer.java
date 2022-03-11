package rs.bg.ac.etf.kdp.lab2019Monitors;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Producer extends Thread {

	private ProducerConsumerBuffer<Movie> buffer;

	public Producer(String name, ProducerConsumerBuffer<Movie> buffer) {
		super(name);
		this.buffer = buffer;
	}

	@Override
	public void run() {
		try (Scanner scanner = new Scanner(new File("IMDb movies.csv"))) {
			scanner.nextLine();
			String line = null;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				String[] movie = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				buffer.put(new Movie(movie));
			}
			buffer.setFinished();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
