package in.udaan17.android.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;

import org.json.JSONException;

import in.udaan17.android.R;
import in.udaan17.android.adapter.TechEventsViewPagerAdapter;
import in.udaan17.android.databinding.ActivityTechEventsBinding;
import in.udaan17.android.model.Department;
import in.udaan17.android.util.DataSingleton;
import in.udaan17.android.util.Helper;

public class TechEventsActivity extends AppCompatActivity {
  private ActivityTechEventsBinding dataBinding;
  
  private TechEventsViewPagerAdapter techEventsAdapter;
  
  private int position;
  private Department department;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_tech_events);
    
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
  
      AppCompatImageView background = (AppCompatImageView) this.findViewById(R.id.background);
      background.setImageResource(drawableId);
      background.setBackgroundColor(ContextCompat.getColor(this, colorId));
      
      techEventsAdapter = new TechEventsViewPagerAdapter(this.getSupportFragmentManager(), this, department, position);
  
      this.dataBinding
          .viewPager
          .setAdapter(techEventsAdapter);
  
      this.dataBinding
          .tabLayout
          .setupWithViewPager(this.dataBinding.viewPager);
      this.dataBinding
          .tabLayout
          .setBackgroundColor(ContextCompat.getColor(this, colorId));
  
      this.dataBinding
          .toolbar
          .setTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite));
      this.dataBinding
          .toolbar
          .setBackgroundColor(ContextCompat.getColor(this, colorId));
      this.setSupportActionBar(this.dataBinding.toolbar);
      if (this.getSupportActionBar() != null) {
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle(activityTitle);
        actionBar.setDisplayHomeAsUpEnabled(true);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}