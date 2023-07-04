package co.smallacademy.myvideoslibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    RecyclerView videoList;
    VideoAdapter adapter;
    List<Video> all_videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        all_videos = new ArrayList<>();

        videoList = findViewById(R.id.videoList);
        videoList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new VideoAdapter(this,all_videos);
        videoList.setAdapter(adapter);
        getJsonData();

    }



    private void getJsonData() {
        String URL = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=true&language=en-US&page=1&sort_by=popularity.desc&api_key=2912592334eb5acc00b74cb91daa03ff";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        @SuppressLint("NotifyDataSetChanged") JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, response -> {
            //Log.d(TAG, "onResponse: "+ response);
            try {
                JSONArray categories = response.getJSONArray("results");
                JSONObject categoriesData = categories.getJSONObject(0);
                JSONArray videos = response.getJSONArray("results");


               Log.d(TAG, "onResponse: "+ videos);


                for (int i = 0; i< videos.length();i++){
                    JSONObject video = videos.getJSONObject(i);

                     String path = "https://image.tmdb.org/t/p/w500";
                     Video v = new Video();

                      v.setTitle(video.getString("title"));
                      v.setDescription(video.getString("overview"));
                      v.setAuthor(video.getString("release_date"));
                      v.setImageUrl(path + video.getString("poster_path"));
                      //JSONArray videoUrl = video.getJSONArray("poster_path");
                      v.setVideoUrl(video.getString("poster_path"));
//
                    Log.d(TAG, "onResponse5: "+ v.getImageUrl());
                      all_videos.add(v);
                      adapter.notifyDataSetChanged();


                }







            } catch (JSONException e) {
                e.printStackTrace();
            }


        }, error -> Log.d(TAG, "onErrorResponse: " + error.getMessage()));

        requestQueue.add(objectRequest);
    }
}