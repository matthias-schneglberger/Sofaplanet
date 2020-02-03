package mschneglberger.htlgkr.sofaplanet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadMoviesFromAsset("movies.json");

        for(Movie mo : movieList){
            mo.setupImage();
        }


        setupMovieView();
    }

    private void clickedOnMovie(AdapterView<?> adapterView, View view, int i, long l){
        Intent intent = new Intent(this, MovieActivity.class);

        Movie tempMovie = movieList.get(i);
        Movie_withoutRequestCreator serializeableMovie = new Movie_withoutRequestCreator(tempMovie.getTitle(), tempMovie.getStars(), tempMovie.getCover(), tempMovie.getRealeaseDate(), tempMovie.getReview());

        intent.putExtra("movie", (Serializable) serializeableMovie);
        startActivity(intent);
    }

    private void setupMovieView(){
        GridView movieView = findViewById(R.id.gridView_movies);

//        List<Movie> movieListT = new ArrayList<>();
//        for(int i = 0; i < 100; i++){
//            Movie movie = new Movie("Film #" + i,5.0, "http://image.tmdb.org/t/p/w154/svIDTNUoajS8dLEo7EosxvyAsgJ.jpg", null, null);
//            movie.setupImage();
//            movieListT.add(movie);
//        }


        movieView.setAdapter(new MovieAdapter(getApplicationContext(),movieList, R.layout.movie));

        movieView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clickedOnMovie(adapterView,view,i,l);
            }
        });
    }

    private void loadMoviesFromAsset(String filename){
        String movieStr = "";

        InputStream in = getInputStreamForAsset(filename);
        BufferedReader bin = new BufferedReader(new InputStreamReader(in));
        String line;


        try {
            while ((line = bin.readLine()) != null) {
                Log.d(TAG, "line: " + line);
                movieStr += line;
            }
        }
        catch (IOException e) {
            Log.e(TAG, e.toString());
        }


        try {
            loadMoviesJSON(movieStr);
        } catch (JSONException e) {
            Log.e(TAG, "loadMoviesFromAsset: " + e.getStackTrace().toString());
        }


    }

    private void loadMoviesJSON(String movieStr) throws JSONException {
        JSONObject jsonObject = null;

        jsonObject = new JSONObject(movieStr);


        JSONArray results = jsonObject.getJSONArray("results");


        for(int i = 0; i < results.length()-1; i++){
            JSONObject tempMovie = results.getJSONObject(i);

            String title = tempMovie.getString("title");
            double vote_average = tempMovie.getDouble("vote_average");
            String cover = "http://image.tmdb.org/t/p/w154/" + tempMovie.getString("poster_path");
            String releaseDate = tempMovie.getString("release_date");
            String review = tempMovie.getString("overview");

            try {
                movieList.add(new Movie(title, vote_average, cover, new SimpleDateFormat("yyyy-MM-dd").parse(releaseDate), review));
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }


    }

    private InputStream getInputStreamForAsset(String filename) {
        // tries to open Stream on Assets. If fails, returns null
        Log.d(TAG, "getInputStreamForAsset: " + filename);
        AssetManager assets = getAssets();
        try {
            return assets.open(filename);
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
