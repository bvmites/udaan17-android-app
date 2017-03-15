package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import in.udaan17.android.R;
import in.udaan17.android.adapter.CombinedDetailsAdapter;

public class CombinedDetailsActivity extends AppCompatActivity {

    private ViewPager detailsViewPager;
    private CombinedDetailsAdapter combinedDetailsAdapter;
    private TabLayout tabs;
    private Toolbar toolbar;
    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined_details);

        detailsViewPager = (ViewPager) findViewById(R.id.combined_details_viewPager);
        combinedDetailsAdapter = new CombinedDetailsAdapter(this.getSupportFragmentManager());
        toolbar = (Toolbar) findViewById(R.id.combined_details_appbar_toolbar);
        tabs = (TabLayout) findViewById(R.id.combined_details_tabLayout);
        nestedScrollView = (NestedScrollView) findViewById(R.id.combined_details_nest_scrollView);

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

    }
}
