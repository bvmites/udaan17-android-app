package in.udaan17.android.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import org.json.JSONException;

import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.adapter.EventAdapter;
import in.udaan17.android.databinding.ActivityCulturalBinding;
import in.udaan17.android.model.Event;
import in.udaan17.android.util.DataSingleton;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

public class CulturalActivity extends AppCompatActivity implements ListItemClickCallBack {
  private ActivityCulturalBinding dataBinding;
  
  private EventAdapter culturalAdapter;
  private List<Event> culturalList;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_cultural);
    
    try {
      culturalList = DataSingleton.getInstance(this).getCulturalList();
      
      culturalAdapter = new EventAdapter(culturalList, this);
  
      this.dataBinding
          .recyclerView
          .setAdapter(culturalAdapter);
      this.dataBinding
          .recyclerView
          .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
      
      culturalAdapter.setItemClickCallBack(this);
  
      this.dataBinding
          .toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite));
      this.dataBinding
          .toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.color_cultural));
      this.setSupportActionBar(this.dataBinding.toolbar);
  
      if (this.getSupportActionBar() != null) {
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Cultural Events");
        actionBar.setDisplayHomeAsUpEnabled(true);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void onItemClick(int position, int viewId) {
    EventDetailsActivity.startActivity(
        this,
        "cultural",
        culturalList.get(position)
    );
  }
}
