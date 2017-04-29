package com.demo.cdh.cardcoordinatorlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;

public class ScrollViewActivity extends Activity {

    private CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolBar);
        toolbarLayout.setExpandedTitleColor(Color.BLACK);
        toolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }
}
