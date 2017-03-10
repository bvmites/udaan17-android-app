package in.udaan17.android.activity;

import android.os.Bundle;
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

public class CulturalActivity extends AppCompatActivity implements ListItemClickCallBack {
  
  private RecyclerView culturalRecyclerView;
  private EventAdapter culturalAdapter;
  private Toolbar toolbar;
  private List<Event> culturalList;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cultural);
    try {
      
      culturalList = DataSingleton.getInstance(this).getCulturalList();
      
      culturalRecyclerView = (RecyclerView) findViewById(R.id.event_recycler_view);
      culturalAdapter = new EventAdapter(culturalList, this);
      
      culturalRecyclerView.setAdapter(culturalAdapter);
      culturalRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
      
      culturalAdapter.setItemClickCallBack(this);
      
      toolbar = (Toolbar) findViewById(R.id.toolbar);
      toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite));
      toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.color_cultural));
      this.setSupportActionBar(toolbar);
      ActionBar actionBar = this.getSupportActionBar();
      
      if (actionBar != null) {
        actionBar.setTitle("Cultural Events");
        actionBar.setDisplayHomeAsUpEnabled(true);
      }
      
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void onItemClick(int position, int viewId) {
    EventDetailsActivity.startActivity(this, "cultural", culturalList.get(position));
  }
}
