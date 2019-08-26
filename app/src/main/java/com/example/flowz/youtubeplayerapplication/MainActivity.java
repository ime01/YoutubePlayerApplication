package com.example.flowz.youtubeplayerapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.w3c.dom.Text;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyDOU1UiUhJFn3hXDJsgafSpzk7fn1LOonI";
    public static final String YOUTUBE_VIDEO_CODE = "Ka4BxFizU7I";   //music key for my mentor J COLE's track LOVE YOURS!!
    public static final int RECOVERY_DIALOG_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        text = (TextView)findViewById(R.id.myText);
        text.setText("As u watch,just know i'm watching you 2");

        youTubeView = (YouTubePlayerView)findViewById(R.id.youtube_view);
        youTubeView.initialize(API_KEY, this);
        }

    private YouTubePlayer.Provider getYouTubePlayerProvider(){
        return (YouTubePlayerView)findViewById(R.id.youtube_view);
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if(!wasRestored){
            player.cueVideo(YOUTUBE_VIDEO_CODE);
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()){
           errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        }else {
            String errorMessage = String.format(
                    "Error playing video: %s", errorReason.toString());
            Toast.makeText(this, "errorMessage", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==RECOVERY_DIALOG_REQUEST){
            getYouTubePlayerProvider().initialize(API_KEY, this);
        }
    }
}
