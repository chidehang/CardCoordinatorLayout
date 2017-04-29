package com.demo.cdh.cardcoordinatorlayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewPager(View view) {
        startActivity(new Intent(this, ViewPagerActivity.class));
    }

    public void scrollView(View view) {
        startActivity(new Intent(this, ScrollViewActivity.class));
    }

    public void cardLayout(View view) {
        startActivity(new Intent(this, CardRecyclerActivity.class));
    }
}
