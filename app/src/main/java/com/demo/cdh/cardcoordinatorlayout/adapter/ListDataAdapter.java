package com.demo.cdh.cardcoordinatorlayout.adapter;

import android.content.Context;

import com.demo.cdh.cardcoordinatorlayout.R;
import com.demo.cdh.cardcoordinatorlayout.adapter.base.BaseRecyclerAdapter;
import com.demo.cdh.cardcoordinatorlayout.adapter.base.ViewHolder;

import java.util.List;

/**
 * Created by hang on 2017/4/26.
 */

public class ListDataAdapter extends BaseRecyclerAdapter<String> {

    public ListDataAdapter(Context context, List<String> data) {
        super(context, data);
        putItemLayoutId(VIEW_TYPE_DEFAULT, R.layout.item_list_data);
    }

    @Override
    public void onBind(ViewHolder holder, String item, int position) {
        holder.setText(R.id.itemText, item);
    }
}
