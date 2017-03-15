package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;

import in.udaan17.android.R;
import in.udaan17.android.adapter.CombinedDetailsAdapter;

public class CombinedDetailsActivity extends AppCompatActivity {

    private ViewPager detailsViewPager;
    private CombinedDetailsAdapter combinedDetailsAdapter;
    private TabLayout tabs;
    private Toolbar toolbar;
    private NestedScrollView nestedScrollView;
    private AppCompatImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined_details);

        detailsViewPager = (ViewPager) findViewById(R.id.combined_details_viewPager);
        combinedDetailsAdapter = new CombinedDetailsAdapter(this.getSupportFragmentManager());
        toolbar = (Toolbar) findViewById(R.id.combined_details_appbar_toolbar);
        tabs = (TabLayout) findViewById(R.id.combined_details_tabLayout);
        nestedScrollView = (NestedScrollView) findViewById(R.id.combined_details_nest_scrollView);
        image = (AppCompatImageView) findViewById(R.id.combined_details_appbar_image_view_main);

        detailsViewPager.setAdapter(combinedDetailsAdapter);
        tabs.setupWithViewPager(detailsViewPager);
        nestedScrollView.setFillViewport(true);


        this.setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Details Page");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        detailsViewPager.setCurrentItem(1);//sets the default page

        detailsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        CombinedDetailsActivity.this.image.setImageResource(R.drawable.developers);
                        image.setAnimation(AnimationUtils.loadAnimation(CombinedDetailsActivity.this, R.anim.swing_up_left));
                        break;
                    case 1:
                        CombinedDetailsActivity.this.image.setImageResource(R.drawable.feature_graphic);
                        image.setAnimation(AnimationUtils.loadAnimation(CombinedDetailsActivity.this, R.anim.swing_up_left));
                        break;
                    case 2:
                        CombinedDetailsActivity.this.image.setImageResource(R.drawable.team_udaan);
                        image.setAnimation(AnimationUtils.loadAnimation(CombinedDetailsActivity.this, R.anim.swing_up_left));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
