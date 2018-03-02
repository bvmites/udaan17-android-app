package in.udaan17.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import in.udaan17.android.R;
import in.udaan17.android.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
  private ActivityMainBinding dataBinding;
  
  public static void startActivity(Activity activity) {
    Intent intent = new Intent(activity, MainActivity.class);
    activity.startActivity(intent);
  }
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    
    Typeface customFont = Typeface.createFromAsset(this.getAssets(), "fonts/AVENGERS.ttf");
    
    this.dataBinding
        .techTitle
        .setTypeface(customFont);
    this.dataBinding
        .nonTechTitle
        .setTypeface(customFont);
    this.dataBinding
        .culturalTitle
        .setTypeface(customFont);
    
    this.dataBinding
        .techCard
        .setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, DepartmentActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
          }
        });
    
    this.dataBinding
        .nonTechCard
        .setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, NonTechActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
          }
        });
    
    this.dataBinding
        .culturalCard
        .setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, CulturalActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
          }
        });
    
    this.setSupportActionBar(this.dataBinding.toolbar);
    if (this.getSupportActionBar() != null) {
      ActionBar actionBar = this.getSupportActionBar();
      actionBar.setTitle("Udaan-17");
    }
    
    this.dataBinding
        .actionViewTrailer
        .setOnClickListener(new View.OnClickListener() {
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
      case R.id.schedule:
        startActivity(new Intent(MainActivity.this, ScheduleActivity.class));
        break;
    }
    
    return super.onOptionsItemSelected(item);
  }
}
