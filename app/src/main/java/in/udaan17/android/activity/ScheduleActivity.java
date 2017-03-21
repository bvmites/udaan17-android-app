package in.udaan17.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.udaan17.android.R;

public class ScheduleActivity extends AppCompatActivity {

    private CardView nonTechSchedule;
    private CardView techSchedule;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_activty);

        nonTechSchedule = (CardView) findViewById(R.id.schedule_categories_fragment_cardView2);
        techSchedule = (CardView) findViewById(R.id.schedule_categories_fragment_cardView1);
        toolbar = (Toolbar) findViewById(R.id.activity_schedule_toolbar);

        this.setSupportActionBar(toolbar);
        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Udaan-17 Schedule");
        }

        nonTechSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScheduleActivity.this, ImageActivity.class);
                intent.putExtra("Schedule", 2);
                startActivity(intent);
            }
        });

        techSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScheduleActivity.this, ImageActivity.class);
                intent.putExtra("Schedule", 1);
                startActivity(intent);
            }
        });
    }
}
