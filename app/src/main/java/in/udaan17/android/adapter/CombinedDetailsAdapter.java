package in.udaan17.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.udaan17.android.fragment.AboutUsFragment;
import in.udaan17.android.fragment.DeveloperFragment;
import in.udaan17.android.fragment.TeamUdaanFragment;

/**
 * Created by pranshu on 15/3/17.
 */

public class CombinedDetailsAdapter extends FragmentStatePagerAdapter {

    private String title[] = {"Developers", "About us", "Team Udaan"};

    public CombinedDetailsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new DeveloperFragment();
                break;
            case 1:
                fragment = new AboutUsFragment();
                break;
            case 2:
                fragment = new TeamUdaanFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

}
