package com.demo.cdh.cardcoordinatorlayout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.demo.cdh.cardcoordinatorlayout.R;
import com.demo.cdh.cardcoordinatorlayout.adapter.ListDataAdapter;
import com.demo.cdh.cardcoordinatorlayout.adapter.manager.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hang on 2017/4/29.
 */
@CoordinatorLayout.DefaultBehavior(SlidingCardBehavior.class)
public class SlidingCardLayout extends FrameLayout {

    private Context context;

    private TextView tvSection;
    private RecyclerView rvContent;

    private int headerViewHeight;

    private int itemCount;  //数据数量

    public SlidingCardLayout(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public SlidingCardLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public SlidingCardLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public void init(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        this.context = context;
        setBackgroundColor(Color.parseColor("#ebebeb"));
        View root = LayoutInflater.from(context).inflate(R.layout.layout_sliding_card, this);
        tvSection = (TextView) root.findViewById(R.id.tvCardSection);
        rvContent = (RecyclerView) root.findViewById(R.id.rvCardContent);
        initRecycViewData();

        if(attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlidingCardLayoiut);
            int bgColor = array.getColor(R.styleable.SlidingCardLayoiut_sectionBackgroundColor, Color.WHITE);
            String title = array.getString(R.styleable.SlidingCardLayoiut_sectionTitle);
            tvSection.setText(title);
            tvSection.setBackgroundColor(bgColor);
            array.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if(w!=oldw || h!=oldh)
            headerViewHeight = tvSection.getMeasuredHeightAndState();
    }

    public int getHeaderViewHeight() {
        return headerViewHeight;
    }

    public int getItemCount() {
        return itemCount;
    }

    /**
     * 模拟数据
     */
    private void initRecycViewData() {
        List<String> list = new ArrayList<>();
        list.add("数学");
        list.add("语文");
        list.add("英语");
        list.add("物理");
        list.add("化学");
        list.add("生物");
        list.add("政治");
        list.add("地理");
        list.add("历史");
        rvContent.setLayoutManager(new FullyLinearLayoutManager(context));
        rvContent.setAdapter(new ListDataAdapter(context, list));
        itemCount = list.size();
    }
}
