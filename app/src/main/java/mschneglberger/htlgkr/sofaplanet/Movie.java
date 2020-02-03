package mschneglberger.htlgkr.sofaplanet;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {
    private String title;
    private double stars;
    private String cover;
    private Date realeaseDate;
    private String review;
    private RequestCreator image;


    public Movie(String title, double stars, String cover, Date realeaseDate, String review) {
        this.title = title;
        this.stars = stars;
        this.cover = cover;
        this.realeaseDate = realeaseDate;
        this.review = review;
    }

    public String getTitle() {
        return title;
    }

    public double getStars() {
        return stars;
    }

    public String getCover() {
        return cover;
    }

    public Date getRealeaseDate() {
        return realeaseDate;
    }

    public String getReview() {
        return review;
    }

    public void setupImage(){
        image = Picasso.get().load(cover);
    }

    public RequestCreator getImage(){
        return image;
    }
}
