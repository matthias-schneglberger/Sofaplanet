package mschneglberger.htlgkr.sofaplanet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class MovieAdapter extends BaseAdapter {
    List<Movie> movieList = new ArrayList<>();
    private int layoutId;
    private LayoutInflater inflater;

    public MovieAdapter(Context context, List<Movie> movieList, int layoutId) {
        this.movieList = movieList;
        this.layoutId = layoutId;
        this.inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int i) {
        return movieList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Movie movie = movieList.get(i);
        View listItem = (view == null) ? inflater.inflate(this.layoutId, null) : view;

        ImageView imgView =  listItem.findViewById(R.id.imageView_movie);

        movie.getImage().into(imgView);

        imgView.setAdjustViewBounds(true);

        return listItem;
    }
}
