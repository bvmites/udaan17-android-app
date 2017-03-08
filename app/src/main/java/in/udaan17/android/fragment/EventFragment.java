package in.udaan17.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.List;

import in.udaan17.android.R;
import in.udaan17.android.activity.EventDetailsActivity;
import in.udaan17.android.adapter.EventAdapter;
import in.udaan17.android.model.Event;
import in.udaan17.android.util.DataSingleton;
import in.udaan17.android.util.listeners.ListItemClickCallBack;

/**
 * Created by pranshu on 6/3/17.
 */

public class EventFragment extends Fragment implements ListItemClickCallBack {

    View rootView;
    RecyclerView eventRecyclerView;
    EventAdapter eventAdapter;

    List<Event> eventList;

    String activityTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.from(getContext()).inflate(R.layout.tech_event_fragment, container, false);

        int position = this.getArguments().getInt(getString(R.string.activity_key_position), 0);
        activityTitle = this.getArguments().getString(getString(R.string.activity_key_title_name), "");
        try {

            eventList = DataSingleton.getInstance(getActivity()).getDepartmentsList().get(position).getEvents();

            eventRecyclerView = (RecyclerView) rootView.findViewById(R.id.tech_event_recycler_view);
            this.eventAdapter = new EventAdapter(eventList, this.getContext());

            eventRecyclerView.setAdapter(eventAdapter);
            eventRecyclerView.setLayoutManager(new LinearLayoutManager(this.rootView.getContext(), LinearLayoutManager.VERTICAL, false));

            eventAdapter.setItemClickCallBack(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootView;
    }

    @Override
    public void onItemClick(int position, int viewId) {
        EventDetailsActivity.startActivity(
                this.getActivity(),
                activityTitle,
                eventList.get(position)
        );
//        Intent intent = new Intent(this.getActivity(), EventDetailsActivity.class);
//        intent.putExtra(getString(R.string.activity_key_position), position);
//        intent.putExtra(getString(R.string.activity_key_event_data), eventList.get(position).toString());
//        intent.putExtra(getString(R.string.activity_key_title_name), activityTitle);    //pass activityTitle to EventDetailsActivity
//        // to help recognise from where exactly the fragment has been called.
//        this.startActivity(intent);
    }
}
