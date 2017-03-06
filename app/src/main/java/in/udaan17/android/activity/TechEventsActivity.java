package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import in.udaan17.android.R;
import in.udaan17.android.adapter.TechEventsViewPagerAdapter;

public class TechEventsActivity extends AppCompatActivity {

    ViewPager techEventsViewPager;
    TechEventsViewPagerAdapter techEventsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_events);


    }
}
