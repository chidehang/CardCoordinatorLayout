package com.demo.cdh.cardcoordinatorlayout;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.demo.cdh.cardcoordinatorlayout.adapter.MyViewPageAdapter;
import com.demo.cdh.cardcoordinatorlayout.fragment.RecyclerFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends FragmentActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setupViewPager();
        setupTabLayout();
    }

    public void setupViewPager() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new RecyclerFragment());
        fragmentList.add(new RecyclerFragment());
        fragmentList.add(new RecyclerFragment());
        viewPager.setAdapter(new MyViewPageAdapter(getSupportFragmentManager(), fragmentList));
    }

    public void setupTabLayout() {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("TAB 1");
        tabLayout.getTabAt(1).setText("TAB 2");
        tabLayout.getTabAt(2).setText("TAB 3");
    }

    public void save(View view) {
        Snackbar.make(view, "save succeed", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ViewPagerActivity.this, "undo succeed", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }
}
