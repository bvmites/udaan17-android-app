package in.udaan17.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.udaan17.android.R;
import in.udaan17.android.activity.CulturalActivity;
import in.udaan17.android.activity.DepartmentActivity;
import in.udaan17.android.activity.NonTechActivity;
import in.udaan17.android.adapter.CategoriesAdapter;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

/**
 * Created by pranshu on 5/3/17.
 */

public class CategoriesFragment extends Fragment implements ListItemClickCallBack {

    private View rootView;
    private RecyclerView categoriesRecyclerView;
    private CategoriesAdapter categoriesAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.categories_fragment, container, false);

            categoriesRecyclerView = (RecyclerView) rootView.findViewById(R.id.categories_recylerView);
        categoriesAdapter = new CategoriesAdapter(getContext());

            categoriesRecyclerView.setAdapter(categoriesAdapter);
            categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this.rootView.getContext(), 2, LinearLayoutManager.VERTICAL, false));

            categoriesAdapter.setItemClickCallBack(this);


        return rootView;
    }

    @Override
    public void onItemClick(int position, int viewId) {
        Intent i = null;
        switch (position) {
            case 0:
                i = new Intent(this.getActivity(), DepartmentActivity.class);
                break;
        }
        switch (position) {
            case 0:
                i = new Intent(this.getActivity(), DepartmentActivity.class);
                break;
            case 1:
                i = new Intent(this.getActivity(), NonTechActivity.class);
                break;
            case 2:
                i = new Intent(this.getActivity(), CulturalActivity.class);
                break;
        }
        startActivity(i);
    }
}
