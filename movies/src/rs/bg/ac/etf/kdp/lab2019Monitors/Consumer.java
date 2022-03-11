package rs.bg.ac.etf.kdp.lab2019Monitors;

import java.util.ArrayList;
import java.util.List;

public class Consumer extends Thread {

	private ProducerConsumerBuffer<Movie> buffer;
	private List<Movie> list = new ArrayList<Movie>();
	private int maxDirectors = 1;

	public Consumer(String name, ProducerConsumerBuffer<Movie> buffer) {
		super(name);
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (!buffer.getFinished()) {
			Movie movie = buffer.get();
			if (movie == null) {
				break;
			}
			if (movie.getMovieDirectors().length == maxDirectors) {
				list.add(movie);
			} else if (movie.getMovieDirectors().length > maxDirectors) {
				list.clear();
				list.add(movie);
				maxDirectors = movie.getMovieDirectors().length;
			}
		}
	}
}
