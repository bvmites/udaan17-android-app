package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;

import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.adapter.DeveloperAdapter;
import in.udaan17.android.model.Developer;
import in.udaan17.android.util.DataSingleton;
import in.udaan17.android.util.Helper;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

public class DeveloperActivity extends AppCompatActivity implements ListItemClickCallBack {
  
  private RecyclerView developerRecyclerView;
  private DeveloperAdapter developerAdapter;
  
  private List<Developer> developersArrayList;
  private Toolbar toolbar;
  @Nullable
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_developer);
    
    try {
      this.developersArrayList = DataSingleton.getInstance(this).getDevelopersList();
      
      this.developerRecyclerView = (RecyclerView) findViewById(R.id.developer_recyclerView);
      developerAdapter = new DeveloperAdapter(this.developersArrayList, this);
      
      this.developerRecyclerView.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
      this.developerRecyclerView.setAdapter(developerAdapter);
      
      developerAdapter.setItemClickCallBack(this);
  
      toolbar = (Toolbar) findViewById(R.id.activity_developer_appbar_toolbar);
      this.setSupportActionBar(toolbar);
      ActionBar actionBar = getSupportActionBar();
  
      if (actionBar != null) {
        actionBar.setTitle("Developers");
        actionBar.setDisplayHomeAsUpEnabled(true);
      }
  
      developerRecyclerView.setNestedScrollingEnabled(false);
      
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void onItemClick(int position, int viewId) {
    switch (viewId) {
      case R.id.developer_mobile:
        Helper.makeCall(this.developersArrayList.get(position).getMobile(), this);
        break;
      case R.id.developer_email:
        Helper.sendEmail(this.developersArrayList.get(position).getEmail(), this);
        break;
      case R.id.developer_github:
        Helper.openUrlInBrowser(this.developersArrayList.get(position).getGithub(), this);
        break;
    }
  }
}
