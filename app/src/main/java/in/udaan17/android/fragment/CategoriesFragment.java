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

import org.json.JSONException;

import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.activity.CulturalActivity;
import in.udaan17.android.activity.DepartmentActivity;
import in.udaan17.android.activity.NonTechActivity;
import in.udaan17.android.adapter.CategoriesAdapter;
import in.udaan17.android.model.Category;
import in.udaan17.android.util.DataSingleton;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

/**
 * Created by pranshu on 5/3/17.
 */

public class CategoriesFragment extends Fragment implements ListItemClickCallBack {

    private View rootView;
    private RecyclerView categoriesRecyclerView;
    private CategoriesAdapter categoriesAdapter;
    private List<Category> categoriesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.categories_fragment, container, false);
        try {

            categoriesList = DataSingleton.getInstance(getActivity()).getCategoryList();

            categoriesRecyclerView = (RecyclerView) rootView.findViewById(R.id.categories_recylerView);
            categoriesAdapter = new CategoriesAdapter(categoriesList, getContext());

            categoriesRecyclerView.setAdapter(categoriesAdapter);
            categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this.rootView.getContext(), 2, LinearLayoutManager.VERTICAL, false));

            categoriesAdapter.setItemClickCallBack(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    @Override
    public void onItemClick(int position, int viewId) {
        String alias = categoriesList.get(position).getAlias();
        Intent i = null;
        switch (alias) {
            case "tech":
                i = new Intent(this.getActivity(), DepartmentActivity.class);
                break;
            case "non-tech":
                i = new Intent(this.getActivity(), NonTechActivity.class);
                break;
            case "cultural":
                i = new Intent(this.getActivity(), CulturalActivity.class);
                break;
        }
        startActivity(i);

    }
}
