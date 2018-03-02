package in.udaan17.android.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;

import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.adapter.DepartmentAdapter;
import in.udaan17.android.databinding.ActivityDepartmentBinding;
import in.udaan17.android.model.Department;
import in.udaan17.android.util.DataSingleton;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

public class DepartmentActivity extends AppCompatActivity implements ListItemClickCallBack {
  private ActivityDepartmentBinding dataBinding;

    private DepartmentAdapter departmentAdapter;

    private List<Department> departmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_department);

        try {
            departmentList = DataSingleton.getInstance(this).getDepartmentsList();

            departmentAdapter = new DepartmentAdapter(departmentList, this);
  
          this.dataBinding
              .recyclerView
              .setAdapter(departmentAdapter);
          this.dataBinding
              .recyclerView
              .setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
            departmentAdapter.setItemClickCallBack(this);
    
            Toolbar toolbar = (Toolbar) this.findViewById(R.id.appbar_toolbar);
            this.setSupportActionBar(toolbar);
            if (this.getSupportActionBar() != null) {
                ActionBar actionBar = this.getSupportActionBar();
                actionBar.setTitle("Tech Events: Departments");
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(int position, int viewId) {
        Intent eventIntent = new Intent(this, TechEventsActivity.class);

        eventIntent.putExtra(getString(R.string.activity_key_position), position);
        eventIntent.putExtra(getString(R.string.activity_key_title_name), departmentList.get(position).getAlias());

        startActivity(eventIntent);
    }
}
