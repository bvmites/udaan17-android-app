package udaan17.in.udaan17androidapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import udaan17.in.udaan17androidapp.R;
import udaan17.in.udaan17androidapp.adapter.MainActivityPagerAdapter;


public class MainActivity extends AppCompatActivity {

    private MainActivityPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewPager = (ViewPager) findViewById(R.id.viewpager_activity_main);
        this.viewPagerAdapter = new MainActivityPagerAdapter(this.getFragmentManager());
        this.viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabs = (TabLayout) findViewById(R.id.tab_activity_main);
        tabs.setupWithViewPager(viewPager);
    }
}
