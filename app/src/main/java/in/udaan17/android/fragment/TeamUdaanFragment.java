package in.udaan17.android.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.adapter.TeamSection;
import in.udaan17.android.model.Category;
import in.udaan17.android.util.DataSingleton;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class TeamUdaanFragment extends Fragment {

  private View rootView;
  private List<Category> teamUdaan;
  
  private RecyclerView recyclerView;
  private SectionedRecyclerViewAdapter teamUdaanAdapter;
  
  public static void startActivity(Context context) {
    Intent intent = new Intent(context, TeamUdaanFragment.class);
    context.startActivity(intent);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    this.rootView = inflater.inflate(R.layout.activity_team_udaan, container, false);

    this.initializeElements(rootView);
    return rootView;
  }

  private void initializeElements(View rootView) {
    try {
      this.teamUdaan = DataSingleton.getInstance(this.getActivity()).getTeamUdaan();
    } catch (JSONException e) {
      e.printStackTrace();
      this.teamUdaan = new ArrayList<>();
    }

    this.recyclerView = (RecyclerView) this.rootView.findViewById(R.id.team_recycler_view);
    this.teamUdaanAdapter = new SectionedRecyclerViewAdapter();
    for (int index = 0; index < this.teamUdaan.size(); index++) {
      this.teamUdaanAdapter.addSection(String.valueOf(index), new TeamSection(this.teamUdaan.get(index)));
    }
    this.recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
    this.recyclerView.setAdapter(this.teamUdaanAdapter);
//    this.recyclerView.setNestedScrollingEnabled(false);
  }
}
