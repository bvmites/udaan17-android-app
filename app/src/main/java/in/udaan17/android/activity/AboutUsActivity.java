package in.udaan17.android.activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.youtube.player.YouTubeIntents;

import in.udaan17.android.R;
import in.udaan17.android.util.Helper;

/**
 * Creator: pranshudave
 * Date: 2017-03-12
 * Project: udaan-17-android-app
 */

public class AboutUsActivity extends AppCompatActivity {

    private final String EMAIL_ADDRESS = "";
    private final String YOUTUBE_LINK = "UCnqRgS6O0MGF8sTYb_fHjWA";
    private final String FACEBOOK_LINK = "https://www.facebook.com/teamudaan17/";
    private final String PLAY_STORE = "";
    private final String WEB_LINK = "https://www.udaan17.in";

    private final String lat = "22.5525703";
    private final String lon = "72.9240181";
    private final String mapTitle = "BVM Engineering College";

    private AppCompatImageButton mail;
    private AppCompatImageButton youtube;
    private AppCompatImageButton facebook;
    private AppCompatImageButton playStore;
    private AppCompatImageButton weblink;
    private Toolbar toolbar;
    private AppCompatImageButton maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        mail = (AppCompatImageButton) findViewById(R.id.mail);
        youtube = (AppCompatImageButton) findViewById(R.id.youtube);
        facebook = (AppCompatImageButton) findViewById(R.id.facebook);
        playStore = (AppCompatImageButton) findViewById(R.id.playstore);
        weblink = (AppCompatImageButton) findViewById(R.id.website);
        toolbar = (Toolbar) findViewById(R.id.about_us_toolbar);
        maps = (AppCompatImageButton) findViewById(R.id.map_view);


        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.sendEmail(EMAIL_ADDRESS, AboutUsActivity.this);
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = YouTubeIntents.createChannelIntent(AboutUsActivity.this, YOUTUBE_LINK);
                startActivity(intent);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getFacebookIntent(AboutUsActivity.this.getPackageManager(), FACEBOOK_LINK);
                startActivity(intent);
            }
        });

        weblink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.openUrlInBrowser(WEB_LINK, AboutUsActivity.this);
            }
        });

        playStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appPackageName = getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lon + " (" + mapTitle + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                startActivity(intent);
            }
        });
        this.setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("About Us");
        }

    }

    private Intent getFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);

            if (applicationInfo.enabled) {
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }


}
