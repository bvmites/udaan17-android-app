package in.udaan17.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
  
  @Nullable
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    
    super.onCreate(savedInstanceState);
    setContentView(R.layout.developer_fragment);
    
    try {
      this.developersArrayList = DataSingleton.getInstance(this).getDevelopersList();
      
      this.developerRecyclerView = (RecyclerView) findViewById(R.id.developer_recyclerView);
      developerAdapter = new DeveloperAdapter(this.developersArrayList, this);
      
      this.developerRecyclerView.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
      this.developerRecyclerView.setAdapter(developerAdapter);
      
      developerAdapter.setItemClickCallBack(this);
      
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
