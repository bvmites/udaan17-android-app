package in.udaan17.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import in.udaan17.android.R;
import in.udaan17.android.adapter.MainActivityPagerAdapter;


public class MainActivity extends AppCompatActivity {

    private MainActivityPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewPager = (ViewPager) findViewById(R.id.viewpager_activity_main);
        this.viewPagerAdapter = new MainActivityPagerAdapter(this.getSupportFragmentManager());
        this.viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabs = (TabLayout) findViewById(R.id.tab_activity_main);
        tabs.setupWithViewPager(viewPager);

        NestedScrollView nestedScrollView = (NestedScrollView) findViewById(R.id.activity_main_nest_scrollView);
        nestedScrollView.setFillViewport(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_appbar_toolbar);
        this.setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Udaan-17");
        }
    }
}
