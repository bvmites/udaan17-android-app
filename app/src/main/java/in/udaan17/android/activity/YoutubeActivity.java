package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import in.udaan17.android.BuildConfig;
import in.udaan17.android.R;

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final String TAG = "YoutubeActivity";
    static final String YOUTUBE_VIDEO_ID = "XkcP5Fc8QGY";
    static final String YOUTUBE_PLAYLIST = "RDJznXx1Ns374#t=0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(constraintLayout);

        YouTubePlayerView youTubePlayerView = new YouTubePlayerView(this);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_activity_player);
        youTubePlayerView.initialize(BuildConfig.GOOGLE_API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if (!b) {
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        final int REQUEST_CODE = 1;
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            String errorMessage = "There was an error initializing Youtube Player" + youTubeInitializationResult.toString();
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
