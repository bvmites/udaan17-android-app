package in.udaan17.android.fragment;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeIntents;

import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.model.Manager;
import in.udaan17.android.util.Helper;

/**
 * Creator: pranshudave
 * Date: 2017-03-12
 * Project: udaan-17-android-app
 */

public class AboutUsFragment extends Fragment {

    private final String EMAIL_ADDRESS = "developer.team.udaan@gmail.com";
    private final String YOUTUBE_LINK = "UCnqRgS6O0MGF8sTYb_fHjWA";
    private final String FACEBOOK_LINK = "https://www.facebook.com/teamudaan17/";
    private final String PLAY_STORE = "";
    private final String WEB_LINK = "https://www.udaan17.in";
    private final String lat = "22.5525703";
    private final String lon = "72.9240181";
    private final String mapTitle = "BVM Engineering College";
    View rootView;
    private List<Manager> techHeadsList;

    private AppCompatImageButton mail;
    private AppCompatImageButton youtube;
    private AppCompatImageButton facebook;
    private AppCompatImageButton playStore;
    private AppCompatImageButton weblink;
    private AppCompatImageButton maps;
    private AppCompatImageButton windows;
    private AppCompatTextView attribution;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_contact_us, container, false);

        mail = (AppCompatImageButton) rootView.findViewById(R.id.mail);
        youtube = (AppCompatImageButton) rootView.findViewById(R.id.youtube);
        facebook = (AppCompatImageButton) rootView.findViewById(R.id.facebook);
        playStore = (AppCompatImageButton) rootView.findViewById(R.id.playstore);
        weblink = (AppCompatImageButton) rootView.findViewById(R.id.website);
        maps = (AppCompatImageButton) rootView.findViewById(R.id.map_view);
        windows = (AppCompatImageButton) rootView.findViewById(R.id.phone);
        attribution = (AppCompatTextView) rootView.findViewById(R.id.contact_us_attribution);

        final SpannableStringBuilder str = new SpannableStringBuilder("Attribution by Kode Logic");
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 15, 25, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        attribution.setText(str);

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.sendEmail(EMAIL_ADDRESS, AboutUsFragment.this.getContext());
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = YouTubeIntents.createChannelIntent(AboutUsFragment.this.getContext(), YOUTUBE_LINK);
                startActivity(intent);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getFacebookIntent(AboutUsFragment.this.getActivity().getPackageManager(), FACEBOOK_LINK);
                startActivity(intent);
            }
        });

        weblink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.openUrlInBrowser(WEB_LINK, AboutUsFragment.this.getContext());
            }
        });

        playStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appPackageName = AboutUsFragment.this.getActivity().getPackageName();
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

        windows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.microsoft.com/en-us/store/p/udaan-17/9p55q9j2bkq7"));
                startActivity(intent);
            }
        });

        attribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.behance.net/bosslogic")));
            }
        });
//        NestedScrollView nestedScrollView = (NestedScrollView) rootView.findViewById(R.id.contact_us_nestedScrollView);
//        nestedScrollView.setNestedScrollingEnabled(false);

        return rootView;

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
