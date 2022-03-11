package rs.bg.ac.etf.kdp.lab2019;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Printer extends Thread {

	public Semaphore toPrint = new Semaphore(0);
	public Semaphore confirm = new Semaphore(0);
	private ArrayList<Movie> printingMovies = new ArrayList<Movie>();
	public AtomicInteger num = new AtomicInteger(0);
	public AtomicBoolean finished = new AtomicBoolean(false);
	private static final int M = 3;

	public Printer(String name) {
		super(name);
	}

	public ArrayList<Movie> getPrintingMovies() {
		return printingMovies;
	}

	public void setPrintingMovies(ArrayList<Movie> printingMovies) {
		this.printingMovies = printingMovies;
	}

	public void run() {
		while (true) {
			try {
				sleep(M * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!finished.get())
				System.out.println("Obradio " + num.get() + " filmova!");
			else {
				toPrint.release();
				confirm.acquireUninterruptibly();
				for (int i = 0; i < printingMovies.size(); i++)
					System.out.println(printingMovies.get(i).getMovieName()+ " " + printingMovies.get(i).getMovieDirectors());
			}
		}
	}
}
