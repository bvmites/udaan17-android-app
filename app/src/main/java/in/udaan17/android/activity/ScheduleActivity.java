package in.udaan17.android.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import in.udaan17.android.R;
import in.udaan17.android.databinding.ActivityScheduleBinding;

public class ScheduleActivity extends AppCompatActivity {
  private ActivityScheduleBinding dataBinding;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_schedule);
    
    this.setSupportActionBar(this.dataBinding.toolbar);
    if (this.getSupportActionBar() != null) {
      ActionBar actionBar = this.getSupportActionBar();
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle("Udaan-17 Schedule");
    }
    
    this.dataBinding
        .nonTechCard
        .setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(ScheduleActivity.this, ImageActivity.class);
            intent.putExtra("Schedule", 2);
            startActivity(intent);
          }
        });
    
    this.dataBinding
        .techCard
        .setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent intent = new Intent(ScheduleActivity.this, ImageActivity.class);
            intent.putExtra("Schedule", 1);
            startActivity(intent);
          }
        });
  }
}
