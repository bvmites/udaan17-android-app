package in.udaan17.android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.udaan17.android.fragment.CategoriesFragment;
import in.udaan17.android.fragment.DeveloperFragment;

/**
 * Created by pranshu on 5/3/17.
 */

public class MainActivityPagerAdapter extends FragmentPagerAdapter {

    final String title[] = new String[]{"Categories", "Developers"};

    public MainActivityPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new CategoriesFragment();
                break;
            case 1:
                fragment = new DeveloperFragment();
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
