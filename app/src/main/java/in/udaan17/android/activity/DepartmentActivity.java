package in.udaan17.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONException;

import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.adapter.DepartmentAdapter;
import in.udaan17.android.model.Department;
import in.udaan17.android.util.DataSingleton;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

public class DepartmentActivity extends AppCompatActivity implements ListItemClickCallBack {

    private RecyclerView departmentRecyclerView;
    private DepartmentAdapter departmentAdapter;

    private List<Department> departmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        try {
            departmentList = DataSingleton.getInstance(this).getDepartmentsList();

            departmentRecyclerView = (RecyclerView) findViewById(R.id.department_recyclerView);
            departmentAdapter = new DepartmentAdapter(departmentList, this);

            departmentRecyclerView.setAdapter(departmentAdapter);
            departmentRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
            departmentAdapter.setItemClickCallBack(this);
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
