package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;

import in.udaan17.android.R;
import in.udaan17.android.adapter.TechEventsViewPagerAdapter;
import in.udaan17.android.model.Department;
import in.udaan17.android.util.DataSingleton;

public class TechEventsActivity extends AppCompatActivity {

    ViewPager techEventsViewPager;
    TechEventsViewPagerAdapter techEventsAdapter;
    Toolbar toolbar;

    int position;
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
            String activityTitle = getIntent().getExtras().getString(getString(R.string.activity_key_title_name), "");

            int drawableId = -1;
            switch (activityTitle) {
                case "Mech":
                    drawableId = R.drawable.machinists;
                    activityTitle = "Machinists";
                    break;
                case "Civil":
                    drawableId = R.drawable.skyscrapers;
                    activityTitle = "Skyscrapers";
                    break;
                case "CPIT":
                    drawableId = R.drawable.keycoders;
                    activityTitle = "KeyCoders";
                    break;
                case "ETEL":
                    drawableId = R.drawable.embeddrones;
                    activityTitle = "Embeddrones";
                    break;
                case "Prod":
                    drawableId = R.drawable.fabfacturers;
                    activityTitle = "FabFacturers";
                    break;
                case "EE":
                    drawableId = R.drawable.resonizers;
                    activityTitle = "Resonizers";
                    break;
            }
            constraintLayout.setBackground(ContextCompat.getDrawable(this, drawableId));

            techEventsViewPager = (ViewPager) findViewById(R.id.tech_events_viewPager);
            techEventsAdapter = new TechEventsViewPagerAdapter(this.getSupportFragmentManager(), this, department, position);

            techEventsViewPager.setAdapter(techEventsAdapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tech_events_tabLayout);
            tabLayout.setupWithViewPager(techEventsViewPager);
  
          toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite));
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
