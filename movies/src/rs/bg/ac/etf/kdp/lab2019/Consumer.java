package rs.bg.ac.etf.kdp.lab2019;

import java.util.ArrayList;

public class Consumer extends Thread {
	private static int _id = 0;
	private int id = _id++;
	private ArrayList<Movie> films = new ArrayList<Movie>();
	private int maxDirectors = 1;
	private SemaphoreProducerConsumerBuffer<Movie> buffer;
	private SharedData data;

	public Consumer(String name, SemaphoreProducerConsumerBuffer<Movie> buffer, SharedData data) {
		super(name);
		this.buffer = buffer;
		this.data = data;
	}

	@Override
	public void run() {
		while (!buffer.getFinished() && buffer.getCapacity() != 0) {
			Movie movie = buffer.get();
			if (movie == null)
				continue;
			if (movie.getMovieDirectors().length > maxDirectors) {
				films.clear();
				films.add(movie);
				maxDirectors = movie.getMovieDirectors().length;
			} else if (movie.getMovieDirectors().length == maxDirectors) {
				films.add(movie);
			}
		}

		data.mutex.acquireUninterruptibly();
		data.setLocalMax(maxDirectors);
		data.setObservedFilms(this.films.size());
		data.setConsumerId(id);
		data.consumersSem[id].acquireUninterruptibly();
		if (!data.isNotNeeded()) {
			data.setMovies(films);
			data.waitFilms.release();
		} 
	}
};
