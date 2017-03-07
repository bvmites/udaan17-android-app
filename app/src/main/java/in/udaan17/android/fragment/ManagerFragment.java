package in.udaan17.android.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.adapter.HeadsAdapter;
import in.udaan17.android.model.Manager;
import in.udaan17.android.util.DataSingleton;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

/**
 * Created by pranshu on 6/3/17.
 */

public class ManagerFragment extends Fragment implements ListItemClickCallBack {

    View rootView;
    RecyclerView headsRecyclerView;
    HeadsAdapter headsAdapter;

    RecyclerView coHeadsRecyclerView;
    HeadsAdapter coHeadsAdapter;

    List<Manager> headsList;
    List<Manager> coHeadsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.from(getContext()).inflate(R.layout.managers_fragment, container, false);

        int position = this.getArguments().getInt(getString(R.string.activity_key_position), 0);
        try {

            headsList = DataSingleton.getInstance(this.getActivity()).getDepartmentsList().get(position).getBranchHeads();

            coHeadsList = DataSingleton.getInstance(this.getActivity()).getDepartmentsList().get(position).getCoheads();

            headsRecyclerView = (RecyclerView) rootView.findViewById(R.id.manager_heads_recycler_view);
            headsAdapter = new HeadsAdapter(headsList, this.getContext());
            coHeadsRecyclerView = (RecyclerView) rootView.findViewById(R.id.manager_coheads_recycler_view);
            coHeadsAdapter = new HeadsAdapter(coHeadsList, this.getContext());

            headsRecyclerView.setAdapter(headsAdapter);
            coHeadsRecyclerView.setAdapter(coHeadsAdapter);

            headsRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 1, LinearLayoutManager.VERTICAL, false));
            coHeadsRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 1, LinearLayoutManager.VERTICAL, false));

            headsAdapter.setItemClickCallBack(new ListItemClickCallBack() {
                @Override
                public void onItemClick(int position, int viewId) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + ManagerFragment.this.headsList.get(position).getContactInfo()));
                    ManagerFragment.this.startActivity(callIntent);
                }
            });
            coHeadsAdapter.setItemClickCallBack(new ListItemClickCallBack() {
                @Override
                public void onItemClick(int position, int viewId) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + ManagerFragment.this.coHeadsList.get(position).getContactInfo()));
                    ManagerFragment.this.startActivity(callIntent);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootView;
    }

    @Override
    public void onItemClick(int position, int viewId) {

    }
}
