package in.udaan17.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.udaan17.android.fragment.EventFragment;
import in.udaan17.android.fragment.ManagerFragment;

/**
 * Created by pranshu on 6/3/17.
 */

public class TechEventsViewPagerAdapter extends FragmentStatePagerAdapter {

    private String tabName[] = {"Events", "Managers"};

    public TechEventsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new EventFragment();
                break;
            case 1:
                fragment = new ManagerFragment();
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
