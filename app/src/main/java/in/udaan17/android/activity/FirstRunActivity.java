package in.udaan17.android.activity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import in.udaan17.android.R;

public class FirstRunActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 4000;
    private static int ANIMATE_IMAGE = 2000;

    AppCompatTextView textViewCompat;
    AppCompatImageView appCompatImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(FirstRunActivity.this.getString(R.string.prefs_file_name), Context.MODE_PRIVATE);

        boolean firstTime = sharedPreferences.getBoolean("first", true);
        if (!firstTime) {
            Intent intent = new Intent(FirstRunActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_first_run);
            sharedPreferences.edit().putBoolean("first", false).apply();


            textViewCompat = (AppCompatTextView) findViewById(R.id.first_run_textView);
            appCompatImageView = (AppCompatImageView) findViewById(R.id.first_run_imageView);

            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Cookie.ttf");
            textViewCompat.setTypeface(custom_font);

            final Animation animation = AnimationUtils.loadAnimation(this, R.anim.swing_up_right);
            textViewCompat.startAnimation(animation);
            appCompatImageView.startAnimation(animation);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(appCompatImageView,
                            PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                            PropertyValuesHolder.ofFloat("scaleY", 1.2f));
                    scaleDown.setDuration(100);

                    scaleDown.setRepeatCount(3);
                    scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

                    scaleDown.start();
                }
            }, ANIMATE_IMAGE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(appCompatImageView,
                            PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                            PropertyValuesHolder.ofFloat("scaleY", 1.2f));
                    scaleDown.setDuration(100);

                    scaleDown.setRepeatCount(3);
                    scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

                    scaleDown.start();
                }
            }, ANIMATE_IMAGE + 700);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(FirstRunActivity.this, SplashActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }, SPLASH_TIME_OUT);

        }
    }
}
