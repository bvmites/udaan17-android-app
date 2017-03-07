package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import in.udaan17.android.R;
import in.udaan17.android.adapter.EventPagerAdapter;

public class EventActivity extends AppCompatActivity {

    ViewPager eventViewPager;
    TabLayout eventTabLayout;
    EventPagerAdapter eventPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventViewPager = (ViewPager) findViewById(R.id.event_activity_viewPager);
        eventTabLayout = (TabLayout) findViewById(R.id.event_activity_tabLayout);


    }
}
