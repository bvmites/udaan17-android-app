package in.udaan17.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.udaan17.android.fragment.EventDetailsFragment;
import in.udaan17.android.fragment.EventManagerFragment;

/**
 * Created by pranshu on 7/3/17.
 */

public class EventPagerAdapter extends FragmentStatePagerAdapter {

    final String title[] = new String[]{"Categories", "Developers"};

    public EventPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new EventDetailsFragment();
                break;
            case 1:
                fragment = new EventManagerFragment();
                break;
            default:
                fragment = null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
