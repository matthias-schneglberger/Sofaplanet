package mschneglberger.htlgkr.sofaplanet;


import java.util.concurrent.Callable;

public class MovieImageDownloader implements Callable<Movie> {
    Movie movie;

    public MovieImageDownloader(Movie movie) {
        this.movie = movie;
    }

    @Override
    public Movie call() throws Exception {
        movie.setupImage();
        return movie;
    }
}
