package in.udaan17.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import in.udaan17.android.R;


public class MainActivity extends AppCompatActivity {

    AppCompatImageButton imageButton;
//    private MainActivityPagerAdapter viewPagerAdapter;
//    private ViewPager viewPager;
    
    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;
    private AppCompatTextView textView1;
    private AppCompatTextView textView2;
    private AppCompatTextView textView3;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        this.viewPager = (ViewPager) findViewById(R.id.viewpager_activity_main);
//        this.viewPagerAdapter = new MainActivityPagerAdapter(this.getSupportFragmentManager());
//        this.viewPager.setAdapter(viewPagerAdapter);
        this.imageButton = (AppCompatImageButton) findViewById(R.id.activity_main_image_button);
//
////        TabLayout tabs = (TabLayout) findViewById(R.id.tab_activity_main);
////        tabs.setupWithViewPager(viewPager);
//
//        NestedScrollView nestedScrollView = (NestedScrollView) findViewById(R.id.activity_main_nest_scrollView);
//        nestedScrollView.setFillViewport(true);
    
        cardView1 = (CardView) findViewById(R.id.categories_fragment_cardView1);
        cardView2 = (CardView) findViewById(R.id.categories_fragment_cardView2);
        cardView3 = (CardView) findViewById(R.id.categories_fragment_cardView3);
    
        textView1 = (AppCompatTextView) findViewById(R.id.categories_fragment_textView1);
        textView2 = (AppCompatTextView) findViewById(R.id.categories_fragment_textView2);
        textView3 = (AppCompatTextView) findViewById(R.id.categories_fragment_textView3);
    
        Typeface custom_font = Typeface.createFromAsset(this.getAssets(), "fonts/AVENGERS.ttf");
    
        textView1.setTypeface(custom_font);
        textView2.setTypeface(custom_font);
        textView3.setTypeface(custom_font);
    
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DepartmentActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CulturalActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NonTechActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_appbar_toolbar);
        this.setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Udaan-17");
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, YoutubeActivity.class);
                startActivity(i);
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_activity_options_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
    
            case R.id.aboutUs:
                startActivity(new Intent(this, CombinedDetailsActivity.class));
                break;

            case R.id.rateUs:
                String appPackageName = getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                break;
        }
        return super.onOptionsItemSelected(item);

    }
    
    
}
