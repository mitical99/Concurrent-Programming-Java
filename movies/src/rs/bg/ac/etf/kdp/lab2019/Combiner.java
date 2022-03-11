package rs.bg.ac.etf.kdp.lab2019;

import java.util.ArrayList;

public class Combiner extends Thread {

	private SharedData data;
	private Printer printer;
	private static final int K = 1000;
	private int observedFilms = 0;
	private ArrayList<Movie> combinerMovies = new ArrayList<Movie>();
	private boolean waitFilms;

	public Combiner(String name, SharedData data, Printer printer) {
		super(name);
		this.data = data;
		this.printer=printer;
	}

	@Override
	public void run() {
		for (int i = 0; i < data.getConsumerCnt(); i++) {
			data.combinerSem.acquireUninterruptibly();
			data.setNotNeeded(data.getLocalMax() < data.getMaxDirectors());
			if (!data.isNotNeeded()) {
				waitFilms = true;
			}
			data.consumersSem[data.getConsumerId()].release();
			if (waitFilms) {
				data.waitFilms.acquireUninterruptibly();
				if (data.getLocalMax() > data.getMaxDirectors()) {
					combinerMovies = data.getMovies();
					data.setMaxDirectors(data.getLocalMax());
				} else if (data.getLocalMax() == data.getMaxDirectors()) {
					combinerMovies.addAll(data.getMovies());
				}
			}
			if (observedFilms > ((observedFilms + data.getObservedFilms() % K))) {
				observedFilms+=data.getObservedFilms();
				data.mutex.release();
				printer.num.set(observedFilms);
			}
			else {
				observedFilms+=data.getObservedFilms();
				data.mutex.release();
			}
		}
		printer.finished.set(true);
		printer.toPrint.acquireUninterruptibly();
		printer.setPrintingMovies(combinerMovies);
		printer.confirm.release();
	}
}
