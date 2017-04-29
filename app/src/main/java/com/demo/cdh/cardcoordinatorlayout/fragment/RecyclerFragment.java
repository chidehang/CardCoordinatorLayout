package com.demo.cdh.cardcoordinatorlayout.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.cdh.cardcoordinatorlayout.R;
import com.demo.cdh.cardcoordinatorlayout.adapter.ListDataAdapter;
import com.demo.cdh.cardcoordinatorlayout.adapter.manager.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hang on 2017/4/26.
 */

public class RecyclerFragment extends Fragment {

    private RecyclerView recyclerView;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        View root = inflater.inflate(R.layout.fragment_recycler, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        setupView();
        return root;
    }

    private void setupView() {
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(context));
        List<String> data = new ArrayList<>();
        data.add("Eyes are raining for her,heart is holding umbrella for her,this is love.");
        data.add("Never frown, even when you are sad, because you never know who is falling in love with your smile.");
        data.add("Let life be beautiful like summer flowers and death like autumn leaves.");
        data.add("We read the world wrong and say that it deceives us.");
        data.add("Eyes are raining for her,heart is holding umbrella for her,this is love.");
        data.add("Never frown, even when you are sad, because you never know who is falling in love with your smile.");
        data.add("Let life be beautiful like summer flowers and death like autumn leaves.");
        data.add("We read the world wrong and say that it deceives us.");
        recyclerView.setAdapter(new ListDataAdapter(context, data));
    }
}
