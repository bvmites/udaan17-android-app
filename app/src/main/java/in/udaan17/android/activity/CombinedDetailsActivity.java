package in.udaan17.android.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;

import in.udaan17.android.R;
import in.udaan17.android.adapter.CombinedDetailsAdapter;
import in.udaan17.android.databinding.ActivityCombinedDetailsBinding;

public class CombinedDetailsActivity extends AppCompatActivity {
  private ActivityCombinedDetailsBinding dataBinding;
  
  private CombinedDetailsAdapter combinedDetailsAdapter;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_combined_details);
    
    combinedDetailsAdapter = new CombinedDetailsAdapter(this.getSupportFragmentManager());
    
    this.dataBinding
        .viewPager
        .setAdapter(combinedDetailsAdapter);
    this.dataBinding
        .tabLayout
        .setupWithViewPager(this.dataBinding.viewPager);
    this.dataBinding
        .scrollView
        .setFillViewport(true);
    
    this.dataBinding
        .tabLayout
        .setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
    
    this.setSupportActionBar(this.dataBinding.toolbar);
    ActionBar actionBar = this.getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle("About Us");
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
    
    this.dataBinding.viewPager.setCurrentItem(1);//sets the default page
    
    this.dataBinding
        .viewPager
        .addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
          @Override
          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
          
          }
          
          @Override
          public void onPageSelected(int position) {
            switch (position) {
              case 0:
                CombinedDetailsActivity
                    .this
                    .dataBinding
                    .appbarImageView
                    .setImageResource(R.drawable.developers);
                CombinedDetailsActivity
                    .this
                    .dataBinding
                    .appbarImageView
                    .setAnimation(AnimationUtils.loadAnimation(
                        CombinedDetailsActivity.this,
                        R.anim.swing_up_left
                    ));
                break;
              case 1:
                CombinedDetailsActivity
                    .this
                    .dataBinding
                    .appbarImageView
                    .setImageResource(R.drawable.feature_graphic);
                CombinedDetailsActivity
                    .this
                    .dataBinding
                    .appbarImageView
                    .setAnimation(AnimationUtils.loadAnimation(
                        CombinedDetailsActivity.this,
                        R.anim.swing_up_left
                    ));
                break;
              case 2:
                CombinedDetailsActivity
                    .this
                    .dataBinding
                    .appbarImageView
                    .setImageResource(R.drawable.team_udaan);
                CombinedDetailsActivity
                    .this
                    .dataBinding
                    .appbarImageView
                    .setAnimation(AnimationUtils.loadAnimation(
                        CombinedDetailsActivity.this,
                        R.anim.swing_up_left
                    ));
                break;
            }
          }
          
          @Override
          public void onPageScrollStateChanged(int state) {
          
          }
        });
    
  }
}
