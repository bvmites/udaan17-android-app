package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import in.udaan17.android.R;
import in.udaan17.android.adapter.TechEventsViewPagerAdapter;

public class TechEventsActivity extends AppCompatActivity {

    ViewPager techEventsViewPager;
    TechEventsViewPagerAdapter techEventsAdapter;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_events);

        position = this.getIntent().getExtras().getInt(getString(R.string.activity_key_position), 0);
        String activityTitle = getIntent().getExtras().getString(getString(R.string.activity_key_title_name), getString(R.string.activity_key_title_name));

        techEventsViewPager = (ViewPager) findViewById(R.id.tech_events_viewPager);
        techEventsAdapter = new TechEventsViewPagerAdapter(this.getSupportFragmentManager(), this, position);

        techEventsViewPager.setAdapter(techEventsAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tech_events_tabLayout);
        tabLayout.setupWithViewPager(techEventsViewPager);

        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(activityTitle);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
