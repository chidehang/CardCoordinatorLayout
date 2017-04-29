package com.demo.cdh.cardcoordinatorlayout.view;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * Created by hang on 2017/4/29.
 */

public class SlidingCardBehavior extends CoordinatorLayout.Behavior<SlidingCardLayout> {

    private int initialOffset;  //卡片初始偏移量

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, SlidingCardLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        //监听垂直滚动
        int axes = nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL;
        if(axes!=0 && child==directTargetChild)
            return true;
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, SlidingCardLayout child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        int height = View.MeasureSpec.getSize(parentHeightMeasureSpec);
        int total = getChildHeaderViewTotal(parent, child);
        //child高度设置成父布局高度-子view头部高度和
        int size = height - total;
        child.measure(parentWidthMeasureSpec, View.MeasureSpec.makeMeasureSpec(size, View.MeasureSpec.EXACTLY));
        return true;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, SlidingCardLayout child, int layoutDirection) {
        //先调用layout确定child位置，再进行偏移
        parent.onLayoutChild(child, layoutDirection);
        //设置child向下偏移，偏移量为前面子view之和
        SlidingCardLayout card = getPreviousChild(parent, child);
        if(card != null) {
            int offset = card.getTop() + card.getHeaderViewHeight();
            child.offsetTopAndBottom(offset);
        }
        initialOffset = child.getTop();
        return true;
    }

    /** 获取headerview高度之和（排除自己） */
    private int getChildHeaderViewTotal(CoordinatorLayout parent, SlidingCardLayout child) {
        int total = 0;
        int count = parent.getChildCount();
        for (int i=0; i<count; i++) {
            View view = parent.getChildAt(i);
            if(view instanceof SlidingCardLayout && view != child) {
                total += ((SlidingCardLayout) view).getHeaderViewHeight();
            }
        }
        return total;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout parent, SlidingCardLayout child, View target, int dx, int dy, int[] consumed) {
        //监听垂直滑动,设置偏移量
        // dy>0 向上滑   dy<0向下滑
        if(dy == 0)
            return;

        int upOffset = child.getTop() - initialOffset;
        int downOffset = child.getTop() -
                (initialOffset + child.getHeight() - child.getHeaderViewHeight());
        //保证偏移不超出范围
        if(dy > upOffset)
            dy = upOffset;
        else if(dy < downOffset)
            dy = downOffset;
        child.offsetTopAndBottom(-dy);
        consumed[1] = dy;

        if(dy > 0)
            coorSlidingUp(parent, child);
        else if(dy < 0)
            coorSlidingDown(parent, child);
    }

    /** 向上联动 */
    public void coorSlidingUp(CoordinatorLayout parent, SlidingCardLayout child) {
        //遍历child前面的子view，依次计算偏移
        SlidingCardLayout current = child;
        SlidingCardLayout previous = getPreviousChild(parent, current);
        while (previous != null) {
            previous.offsetTopAndBottom(-getHeaderOverlap(previous, current));
            current = previous;
            previous = getPreviousChild(parent, current);
        }
    }

    /** 向下联动 */
    public void coorSlidingDown(CoordinatorLayout parent, SlidingCardLayout child) {
        SlidingCardLayout current = child;
        SlidingCardLayout next = getNextChild(parent, current);
        while (next != null) {
            next.offsetTopAndBottom(getHeaderOverlap(current, next));
            current = next;
            next = getNextChild(parent, current);
        }
    }

    /**
     * 计算header重叠的高度
     */
    public int getHeaderOverlap(SlidingCardLayout previos, SlidingCardLayout next) {
        int h = previos.getTop()+previos.getHeaderViewHeight() - next.getTop();
        if(h > 0)
            return h;
        else
            return 0;
    }

    /** 获取当前child前一个子view */
    public SlidingCardLayout getPreviousChild(CoordinatorLayout parent, SlidingCardLayout child) {
        int index = parent.indexOfChild(child);
        for (int i=index-1; i>=0; i--) {
            View v = parent.getChildAt(i);
            if(v instanceof SlidingCardLayout)
                return (SlidingCardLayout) v;
        }
        return null;
    }

    /** 获取当前child下一个子view */
    public SlidingCardLayout getNextChild(CoordinatorLayout parent, SlidingCardLayout child) {
        int index = parent.indexOfChild(child);
        for (int i=index+1; i<parent.getChildCount(); i++) {
            View v = parent.getChildAt(i);
            if(v instanceof SlidingCardLayout)
                return (SlidingCardLayout) v;
        }
        return null;
    }
}
