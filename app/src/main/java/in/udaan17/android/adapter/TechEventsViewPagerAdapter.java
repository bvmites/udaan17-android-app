package in.udaan17.android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.udaan17.android.R;
import in.udaan17.android.fragment.EventFragment;
import in.udaan17.android.fragment.ManagerFragment;
import in.udaan17.android.model.Department;

/**
 * Created by pranshu on 6/3/17.
 */

public class TechEventsViewPagerAdapter extends FragmentStatePagerAdapter {

    Department department;
    private String tabName[] = {"Events", "Managers"};
    private Context context;
    private int position;

    public TechEventsViewPagerAdapter(FragmentManager fm, Context context, Department department, int position) {
        super(fm);
        this.context = context;
        this.department = department;
        this.position = position;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        Bundle bundle = new Bundle();
        bundle.putInt(this.context.getString(R.string.activity_key_position), this.position);
        bundle.putString("title", department.getName());
        switch (position) {
            case 0:
                fragment = new EventFragment();
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new ManagerFragment();
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabName[position];
    }
}
