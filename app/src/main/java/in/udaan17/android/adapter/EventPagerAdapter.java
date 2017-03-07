package in.udaan17.android.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.udaan17.android.R;
import in.udaan17.android.fragment.EventDetailsFragment;
import in.udaan17.android.fragment.EventManagerFragment;
import in.udaan17.android.model.Event;

/**
 * Created by pranshu on 7/3/17.
 */

public class EventPagerAdapter extends FragmentStatePagerAdapter {
  
  private static final String title[] = new String[]{"Details", "Managers"};
  
  private Context context;
  private Event event;
  
  public EventPagerAdapter(FragmentManager fragmentManager, Context context, Event event) {
    super(fragmentManager);
    this.context = context;
    this.event = event;
  }
  
  @Override
  public Fragment getItem(int position) {
    Fragment fragment;
    Bundle bundle = new Bundle();
    bundle.putString(context.getString(R.string.activity_key_event_data), event.toString());
    switch (position) {
      case 0:
        fragment = new EventDetailsFragment();
        fragment.setArguments(bundle);
        break;
      case 1:
        fragment = new EventManagerFragment();
        fragment.setArguments(bundle);
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
