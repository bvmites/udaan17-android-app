package in.udaan17.android.fragment;

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
import in.udaan17.android.adapter.DeveloperAdapter;
import in.udaan17.android.model.Developer;
import in.udaan17.android.util.DataSingleton;
import in.udaan17.android.util.Helper;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

/**
 * Created by pranshu on 5/3/17.
 */

public class DeveloperFragment extends Fragment implements ListItemClickCallBack {

    private View rootView;

    private RecyclerView developerRecyclerView;
    private DeveloperAdapter developerAdapter;

    private List<Developer> developersArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.rootView = inflater.inflate(R.layout.developer_fragment, container, false);

        try {
            this.developersArrayList = DataSingleton.getInstance(this.getActivity()).getDevelopersList();

            this.developerRecyclerView = (RecyclerView) rootView.findViewById(R.id.developer_recyclerView);
            developerAdapter = new DeveloperAdapter(this.developersArrayList, getContext());

            this.developerRecyclerView.setLayoutManager(new GridLayoutManager(this.rootView.getContext(), 1, LinearLayoutManager.VERTICAL, false));
            this.developerRecyclerView.setAdapter(developerAdapter);

            developerAdapter.setItemClickCallBack(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootView;
    }

    @Override
    public void onItemClick(int position, int viewId) {
        switch (viewId) {
            case R.id.developer_mobile:
                Helper.makeCall(this.developersArrayList.get(position).getMobile(), this.getContext());
                break;
            case R.id.developer_email:
                Helper.sendEmail(this.developersArrayList.get(position).getEmail(), this.getContext());
                break;
            case R.id.developer_github:
                Helper.openUrlInBrowser(this.developersArrayList.get(position).getGithub(), this.getContext());
                break;
        }
    }
}
