package co.smallacademy.myvideoslibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Player extends AppCompatActivity {

    public static final String TAG = "TAG";
    ProgressBar spiiner;
    ImageView fullScreenOp;
    FrameLayout frameLayout;
    ImageView videoPlayer;

    TextView rating;

    TextView released_date;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        

        fullScreenOp = findViewById(R.id.fullScreenOp);
        frameLayout = findViewById(R.id.frameLayout);

        Intent i = getIntent();
        Bundle data = i.getExtras();
        Video v = (Video) data.getSerializable("videoData");

        getSupportActionBar().setTitle(v.getTitle());

        TextView title = findViewById(R.id.videoTitle);
        TextView desc = findViewById(R.id.videoDesc);
        videoPlayer = findViewById(R.id.imageView2);

        rating = findViewById(R.id.rating2);
        released_date = findViewById(R.id.released_date2);


        rating.setText(v.getRate());
        released_date.setText(v.getRelease_date());



        title.setText(v.getTitle());
        desc.setText(v.getDescription());
        Uri imageUrl = Uri.parse(v.getImageUrl());
        videoPlayer.setImageURI(imageUrl);
        String imageUrlDetail = v.getImageUrl();
        Picasso.get().load(imageUrl).into(videoPlayer);





        fullScreenOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                Objects.requireNonNull(getSupportActionBar()).hide();
                fullScreenOp.setVisibility(View.GONE);
                //frameLayout.setLayoutParams(new ConstraintLayout.LayoutParams(new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)));
                videoPlayer.setLayoutParams(new FrameLayout.LayoutParams(new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)));
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        fullScreenOp.setVisibility(View.VISIBLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        Objects.requireNonNull(getSupportActionBar()).show();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int heightValue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,220,getResources().getDisplayMetrics());
        //frameLayout.setLayoutParams(new ConstraintLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,heightValue)));
        videoPlayer.setLayoutParams(new FrameLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,heightValue)));
        int orientation = getResources().getConfiguration().orientation;
        
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            super.onBackPressed();
        }
    }
}