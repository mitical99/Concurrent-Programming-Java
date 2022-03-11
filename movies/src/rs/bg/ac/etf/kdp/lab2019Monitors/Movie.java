package rs.bg.ac.etf.kdp.lab2019Monitors;

public class Movie {
	
	private String movieName;
	private String[] MovieDirectors;
	
	public Movie(String[] movie) {
		movieName = movie[1];
		MovieDirectors = movie[10].split(",");
	}

	public String getMovieName() {
		return movieName;
	}

	public String[] getMovieDirectors() {
		return MovieDirectors;
	}
	
}
