package in.udaan17.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import in.udaan17.android.R;
import in.udaan17.android.adapter.ManagerSection;
import in.udaan17.android.model.Department;
import in.udaan17.android.util.DataSingleton;
import in.udaan17.android.util.Helper;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * Created by pranshu on 12/3/17.
 */

public class ManagerFragment extends Fragment implements ManagerSection.ManagerCallItemClick {

    View rootView;

    private Department department;
    private RecyclerView managerRecyclerView;
    private SectionedRecyclerViewAdapter managerAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.managers_fragment, container, false);

        int position = this.getArguments().getInt(getString(R.string.activity_key_position), 0);
        try {
            department = DataSingleton.getInstance(this.getActivity()).getDepartmentsList().get(position);

            this.managerRecyclerView = (RecyclerView) rootView.findViewById(R.id.new_manager_recycler_view);
            this.managerAdapter = new SectionedRecyclerViewAdapter();
            for (int index = 1; index <= 2; index++) {
                this.managerAdapter.addSection(new ManagerSection(department, index, this));
            }
            this.managerRecyclerView.setAdapter(managerAdapter);
            this.managerRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    @Override
    public void onItemClick(int position, int sectionId) {
        Log.d("ManagerFragment", "onItemClick: " + position);
        if (sectionId == 1) {
            Helper.makeCall(ManagerFragment.this.department.getBranchHeads().get(position - 1).getContactInfo(),
                    ManagerFragment.this.getContext());
        } else {
            int subtract = this.department.getBranchHeads().size() + 2;
            Helper.makeCall(ManagerFragment.this.department.getCoHeads().get(position - subtract).getContactInfo(),
                    ManagerFragment.this.getContext());
        }
    }
}
