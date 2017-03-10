package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;

import in.udaan17.android.R;
import in.udaan17.android.adapter.TechEventsViewPagerAdapter;
import in.udaan17.android.model.Department;
import in.udaan17.android.util.DataSingleton;
import in.udaan17.android.util.Helper;

public class TechEventsActivity extends AppCompatActivity {
  
  private ViewPager techEventsViewPager;
  private TechEventsViewPagerAdapter techEventsAdapter;
  private Toolbar toolbar;
  
  private int position;
  private Department department;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tech_events);
    
    ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.tech_events_constraint_layout);
    toolbar = (Toolbar) findViewById(R.id.tech_events_toolbar);
    
    position = this.getIntent().getExtras().getInt(getString(R.string.activity_key_position), 0);
    try {
      department = DataSingleton.getInstance(this).getDepartmentsList().get(position);
      String activityTitle = department.getName();
      
      int drawableId = this.getResources().getIdentifier(
          Helper.getResourceNameFromTitle(department.getName()),
          "drawable",
          this.getPackageName()
      );
      
      int colorId = this.getResources().getIdentifier(
          "color_" + Helper.getResourceNameFromTitle(department.getName()),
          "color",
          this.getPackageName()
      );
      
      AppCompatImageView background = (AppCompatImageView) this.findViewById(R.id.tech_event_background);
      background.setImageResource(drawableId);
      background.setBackgroundColor(ContextCompat.getColor(this, colorId));
      
      techEventsViewPager = (ViewPager) findViewById(R.id.tech_events_viewPager);
      techEventsAdapter = new TechEventsViewPagerAdapter(this.getSupportFragmentManager(), this, department, position);
      
      techEventsViewPager.setAdapter(techEventsAdapter);
      
      TabLayout tabLayout = (TabLayout) findViewById(R.id.tech_events_tabLayout);
      tabLayout.setupWithViewPager(techEventsViewPager);
      tabLayout.setBackgroundColor(ContextCompat.getColor(this, colorId));
      
      toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite));
      toolbar.setBackgroundColor(ContextCompat.getColor(this, colorId));
      this.setSupportActionBar(toolbar);
      ActionBar actionBar = this.getSupportActionBar();
      
      if (actionBar != null) {
        actionBar.setTitle(activityTitle);
        actionBar.setDisplayHomeAsUpEnabled(true);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}
