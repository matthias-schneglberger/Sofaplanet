package mschneglberger.htlgkr.sofaplanet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Movie_withoutRequestCreator movie_serializeable = (Movie_withoutRequestCreator) bundle.get("movie");

        Movie  movie = new Movie(movie_serializeable.getTitle(), movie_serializeable.getStars(), movie_serializeable.getCover(), movie_serializeable.getRealeaseDate(), movie_serializeable.getReview());
        movie.setupImage();


        TextView name = findViewById(R.id.textView_movie_movieName);
        name.setText(movie.getTitle());
        TextView stars = findViewById(R.id.textView_movie_movieStars);
        stars.setText(String.valueOf(movie.getStars()) + " / 10");
        ImageView image = findViewById(R.id.imageView0_movie_movieImage);
        movie.getImage().into(image);
//        image.setMinimumHeight(1000);

        SimpleDateFormat sFormatter = new SimpleDateFormat("dd.MM.yyyy");
        TextView releaseDate = findViewById(R.id.textView_movie_releaseDate);
        releaseDate.setText(sFormatter.format(movie.getRealeaseDate()));

        TextView review = findViewById(R.id.textView_movie_review);
        review.setText(movie.getReview());
    }
}
