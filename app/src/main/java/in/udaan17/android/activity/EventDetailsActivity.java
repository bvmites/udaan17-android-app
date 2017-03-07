package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import in.udaan17.android.R;
import in.udaan17.android.adapter.EventPagerAdapter;
import in.udaan17.android.model.Event;

public class EventDetailsActivity extends AppCompatActivity {
  private ViewPager eventDetailsViewPager;
  private EventPagerAdapter eventPagerAdapter;
  
  private Event event;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_event_details);
    
    event = Event.parseJson(this.getIntent().getStringExtra(this.getString(R.string.activity_key_event_data)));
    
    this.initializeElements();
  }
  
  private void initializeElements() {
    Toolbar toolbar = (Toolbar) this.findViewById(R.id.appbar_toolbar);
    this.setSupportActionBar(toolbar);
    if (this.getSupportActionBar() != null) {
      this.getSupportActionBar().setTitle(R.string.app_name);
    }
    
    eventDetailsViewPager = (ViewPager) this.findViewById(R.id.event_details_view_pager);
    eventPagerAdapter = new EventPagerAdapter(this.getSupportFragmentManager(), this, this.event);
    
    eventDetailsViewPager.setAdapter(eventPagerAdapter);
    
    TabLayout tabLayout = (TabLayout) this.findViewById(R.id.event_details_tabs);
    tabLayout.setupWithViewPager(eventDetailsViewPager);
  }
}
