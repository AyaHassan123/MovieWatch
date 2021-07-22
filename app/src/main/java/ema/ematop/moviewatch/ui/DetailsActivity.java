package ema.ematop.moviewatch.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;

import ema.ematop.moviewatch.R;
import ema.ematop.moviewatch.databinding.ActivityDetailsBinding;
import ema.ematop.moviewatch.network.Constants;
import ema.ematop.moviewatch.pojo.MovieModel;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_details);
        if(getIntent() != null){
            MovieModel movieModel= (MovieModel)getIntent().getSerializableExtra("Movie");

            getSupportActionBar().setTitle(movieModel.getTitle());
            String imageUrl = Constants.IMAGE_BASE_URL + movieModel.getPosterPath();
            Glide.with(getApplicationContext())
                    .load(imageUrl)
                    .into(binding.ivImagePoster);
            binding.tvTitle.setText(movieModel.getTitle());
            binding.tvOverview.setText(movieModel.getOverview());
            binding.ratingBar.setRating((movieModel.getVoteAverage()));
        }
    }
}