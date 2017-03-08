package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;

import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.adapter.EventAdapter;
import in.udaan17.android.model.Event;
import in.udaan17.android.util.DataSingleton;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

public class NonTechActivity extends AppCompatActivity implements ListItemClickCallBack {

    private RecyclerView nonTechRecyclerView;
    private EventAdapter nonTechAdapter;
    private Toolbar toolbar;
    private List<Event> nonTechEventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_fragment);
        try {

            nonTechEventList = DataSingleton.getInstance(this).getNonTechList();

            nonTechRecyclerView = (RecyclerView) findViewById(R.id.event_recycler_view);
            nonTechAdapter = new EventAdapter(nonTechEventList, this);

            nonTechRecyclerView.setAdapter(nonTechAdapter);
            nonTechRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            nonTechAdapter.setItemClickCallBack(this);

            ConstraintLayout linearLayoutCompat = (ConstraintLayout) findViewById(R.id.event_fragment_linear_layout);
            linearLayoutCompat.setBackground(ContextCompat.getDrawable(this, R.drawable.nontech));


            toolbar = (Toolbar) findViewById(R.id.event_fragment_toolbar);
          toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite));
            this.setSupportActionBar(toolbar);
            ActionBar actionBar = this.getSupportActionBar();

            if (actionBar != null) {
                actionBar.setTitle("Non-Tech Events");
                actionBar.setDisplayHomeAsUpEnabled(true);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int position, int viewId) {
        EventDetailsActivity.startActivity(this, "nontech", nonTechEventList.get(position));
//        Intent intent = new Intent(this, EventDetailsActivity.class);
//        intent.putExtra(getString(R.string.activity_key_position), position);
//        intent.putExtra(getString(R.string.activity_key_event_data), nonTechEventList.get(position).toString());
//        this.startActivity(intent);
    }
}
