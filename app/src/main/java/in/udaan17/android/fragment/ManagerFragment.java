package in.udaan17.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.udaan17.android.R;

/**
 * Created by pranshu on 6/3/17.
 */

public class ManagerFragment extends Fragment {

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.from(getContext()).inflate(R.layout.managers_fragment, container, false);
        return rootView;
    }
}
