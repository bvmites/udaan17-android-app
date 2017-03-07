package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
            nonTechRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));

            nonTechAdapter.setItemClickCallBack(this);

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

    }
}
