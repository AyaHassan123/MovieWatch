package aya.movie.movieWatch.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import aya.ematop.movieWatch.R;
import aya.ematop.movieWatch.databinding.VhMovieCardBinding;
import aya.movie.movieWatch.network.Constants;
import aya.movie.movieWatch.pojo.MovieModel;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<MovieModel> movies = new ArrayList<>();
    private Context context;
    private OnMovieClickListener listener;

    public MovieAdapter(Context context ) {
        this.context = context;
    }

    public void onSetClickListner(OnMovieClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MovieViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vh_movie_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
    holder.textView.setText(movies.get(position).getTitle());
    holder.textView2.setText(movies.get(position).getReleaseDate());
    String imageUrl = Constants.IMAGE_BASE_URL + movies.get(position).getPosterPath();
        Glide.with(context)
                .load(imageUrl)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        if(movies!=null){
        return movies.size();}
        return 0;
    }
    public void setList(List<MovieModel> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }
    public MovieModel getMoviePosition(int position) {
        return movies.get(position);
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       private VhMovieCardBinding binding;
       TextView textView;
       TextView textView2;
       ImageView imageView;

        public MovieViewHolder(@NonNull View view) {
            super(view);
            //binding = DataBindingUtil.setContentView((Activity) context,R.layout.vh_movie_card);
            textView =view.findViewById(R.id.tv_movie_title);
            imageView = view.findViewById(R.id.iv_image_movie);
            textView2 = view.findViewById(R.id.tv_vote_number);
            view.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
           if(listener!=null)
               listener.onMovieClick(view, getAdapterPosition());
        }
    }

    public interface OnMovieClickListener {
        void onMovieClick(View view, int pos);
    }
}
