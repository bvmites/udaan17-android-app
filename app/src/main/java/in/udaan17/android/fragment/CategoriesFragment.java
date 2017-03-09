package in.udaan17.android.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.udaan17.android.R;
import in.udaan17.android.activity.CulturalActivity;
import in.udaan17.android.activity.DepartmentActivity;
import in.udaan17.android.activity.NonTechActivity;

/**
 * Created by pranshu on 5/3/17.
 */

public class CategoriesFragment extends Fragment {

    private View rootView;
//    private RecyclerView categoriesRecyclerView;
//    private CategoriesAdapter categoriesAdapter;

    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;
    private AppCompatTextView textView1;
    private AppCompatTextView textView2;
    private AppCompatTextView textView3;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.categories_fragment, container, false);

        cardView1 = (CardView) rootView.findViewById(R.id.categories_fragment_cardView1);
        cardView2 = (CardView) rootView.findViewById(R.id.categories_fragment_cardView2);
        cardView3 = (CardView) rootView.findViewById(R.id.categories_fragment_cardView3);

        textView1 = (AppCompatTextView) rootView.findViewById(R.id.categories_fragment_textView1);
        textView2 = (AppCompatTextView) rootView.findViewById(R.id.categories_fragment_textView2);
        textView3 = (AppCompatTextView) rootView.findViewById(R.id.categories_fragment_textView3);

        Typeface custom_font = Typeface.createFromAsset(this.getActivity().getAssets(), "fonts/AVENGERS.ttf");

        textView1.setTypeface(custom_font);
        textView2.setTypeface(custom_font);
        textView3.setTypeface(custom_font);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoriesFragment.this.getActivity(), DepartmentActivity.class);
                startActivity(i);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoriesFragment.this.getActivity(), NonTechActivity.class);
                startActivity(i);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoriesFragment.this.getActivity(), CulturalActivity.class);
                startActivity(i);
            }
        });

//            categoriesRecyclerView = (RecyclerView) rootView.findViewById(R.id.categories_recylerView);
//        categoriesAdapter = new CategoriesAdapter(getContext());
//
//            categoriesRecyclerView.setAdapter(categoriesAdapter);
//        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this.rootView.getContext(), 1, LinearLayoutManager.VERTICAL, false));
//
//            categoriesAdapter.setItemClickCallBack(this);

        return rootView;
    }

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
