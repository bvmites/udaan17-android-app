package in.udaan17.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.adapter.TeamSection;
import in.udaan17.android.model.Category;
import in.udaan17.android.util.DataSingleton;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class TeamUdaanActivity extends AppCompatActivity {
  private List<Category> teamUdaan;
  
  private RecyclerView recyclerView;
  private SectionedRecyclerViewAdapter teamUdaanAdapter;
  
  public static void startActivity(Context context) {
    Intent intent = new Intent(context, TeamUdaanActivity.class);
    context.startActivity(intent);
  }
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.activity_team_udaan);
    
    this.initializeElements();
  }
  
  private void initializeElements() {
    try {
      this.teamUdaan = DataSingleton.getInstance(this).getTeamUdaan();
    } catch (JSONException e) {
      e.printStackTrace();
      this.teamUdaan = new ArrayList<>();
    }
    
    Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
    toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorWhite));
    toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
    this.setSupportActionBar(toolbar);
    if (this.getSupportActionBar() != null) {
      ActionBar actionBar = this.getSupportActionBar();
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle("Team-Udaan");
    }
    
    this.recyclerView = (RecyclerView) this.findViewById(R.id.team_recycler_view);
    this.teamUdaanAdapter = new SectionedRecyclerViewAdapter();
    for (int index = 0; index < this.teamUdaan.size(); index++) {
      this.teamUdaanAdapter.addSection(String.valueOf(index), new TeamSection(this.teamUdaan.get(index)));
    }
    this.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    this.recyclerView.setAdapter(this.teamUdaanAdapter);
  }
}
