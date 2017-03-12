package in.udaan17.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import in.udaan17.android.R;
import in.udaan17.android.model.Manager;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by pranshu on 12/3/17.
 */

public class ManagerSection extends StatelessSection {


    private Manager manager;

    public ManagerSection(Manager manager) {
        super(R.layout.header_section_team, R.layout.item_section_team);
        this.manager = manager;
    }

    @Override
    public int getContentItemsTotal() {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return null;
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
